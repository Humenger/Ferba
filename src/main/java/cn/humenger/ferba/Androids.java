package cn.humenger.ferba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Androids {
    public static boolean isARM32(String adbPath){
        String result=execCmd(String.format("%s shell getprop | grep abi",adbPath)).data;
        System.out.println("result:"+result);
        return result.contains("armeabi");
    }
    public static boolean isARM64(String adbPath){
        String result=execCmd(String.format("%s shell getprop | grep abi",adbPath)).data;
        return result.contains("arm64-v8a");
    }
    private  static Result execCmd(List<String> command, String charsetName) {
        if(Ferba.MODE_MENU) System.out.println("run:"+ command);
        Result result = new Result();
        InputStream is = null;
        try {
            Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
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
            //close stream
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    private static Result execCmd(String command) {

        return execCmd(command, "UTF-8");
    }
    private static Result execCmd(String command, String charsetName) {
        StringTokenizer st = new StringTokenizer(command);
        String[] commandArray = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++) {
            commandArray[i] = st.nextToken();
        }

        return execCmd(Arrays.asList(commandArray), charsetName);
    }
    private final static class Result {
        public int code;
        public String data;
    }

}
