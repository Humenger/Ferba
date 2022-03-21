package cn.humenger.ferba;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author lixk
 * @description process utils
 * @date 2019/5/5 13:40
 */
public class CommandUtils {


    private final static String DEFAULT_CHARSET_NAME = "UTF-8";


    public static Result run(String commend) {

        StringTokenizer st = new StringTokenizer(commend);
        String[] commendArray = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++) {
            commendArray[i] = st.nextToken();
        }

        return run(Arrays.asList(commendArray), DEFAULT_CHARSET_NAME);
    }

    public static Result run(String... params) {
        return run(Arrays.asList(params), DEFAULT_CHARSET_NAME);
    }


    public static Result run(List<String> commend) {
        return run(commend, DEFAULT_CHARSET_NAME);
    }


    public static Result run(List<String> commend, String charsetName) {
        System.out.println("run:" + commend);
        Result result = new Result();
        InputStream is = null;
        try {

            Process process = new ProcessBuilder(commend).redirectErrorStream(true).start();

            is = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, charsetName));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
                //System.out.println( line);
            }

            result.code = process.waitFor();

            result.data = data.toString().trim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

            closeStreamQuietly(is);
        }
        System.out.println("{result} "+result.data);
        return result;
    }


    private static void closeStreamQuietly(Closeable stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (IOException e) {
            // ignore
        }
    }


    public static class Result {

        public int code;

        public String data;
    }


    public static void main(String[] args) {

        Result r = CommandUtils.run("cmd /C ipconfig /all", "GBK");
        System.out.println("code:" + r.code + "\ndata:" + r.data);

/*		List<String> commend = new ArrayList<>();
		commend.add("cmd");
		commend.add("/C ipconfig /all");
		r = ProcessUtils.run(commend, "GBK");
		System.out.println("code:" + r.code + "\ndata:" + r.data);*/
    }
}

