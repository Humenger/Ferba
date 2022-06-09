package cn.humenger.ferba.resources;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Strings {
    private static final Locale DEF_LOCALE=new Locale("zh","CN");
    private static final Map<String,JSONObject> sLocaleMap=new HashMap<>();
    public static String get(Locale locale, String key) {
        String localeKey=locale.getLanguage()+"-"+locale.getCountry();
        JSONObject jsonObject;
        if(!sLocaleMap.containsKey(localeKey)){
            InputStream inputStream = Strings.class.getResourceAsStream("/strings/" + localeKey + ".json");
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            copy(inputStream,outputStream,true);
            jsonObject=JSONObject.parseObject(outputStream.toString());
            sLocaleMap.put(localeKey,jsonObject);
        }else {
            jsonObject=sLocaleMap.get(localeKey);
        }
        if(jsonObject.containsKey(key)){
            return (String) jsonObject.get(key);
        }
        return get(DEF_LOCALE,key);
    }
    public static void copy(InputStream inputStream, OutputStream outputStream, boolean close){
        byte[] buffer=new byte[1024];
        int n=0;
        try {
            while ((n = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, n);
                outputStream.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(close){
            try {
                inputStream.close();
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
