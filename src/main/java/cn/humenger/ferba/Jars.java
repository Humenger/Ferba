package cn.humenger.ferba;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public final class Jars {
    public static String getFilePath(String name,String keyword,String replaceText) {
        try {
            File rootDir = Files.createTempDirectory("AppTool_").toFile();
            rootDir.deleteOnExit();
            File nameFile = new File(rootDir.getPath() + "/" + name);
            if (!nameFile.getParentFile().exists()) {
                nameFile.getParentFile().mkdirs();
            }
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            StreamUtils.copy(Jars.class.getResourceAsStream(name),outputStream,true);
            String template=new String(outputStream.toByteArray(),StandardCharsets.UTF_8);
            template=template.replace(keyword,replaceText);
            StreamUtils.copy(new ByteArrayInputStream(template.getBytes(StandardCharsets.UTF_8)), new FileOutputStream(nameFile), true);
            return nameFile.getAbsolutePath();
        }catch (Exception e){
            if(Ferba.MODE_MENU) e.printStackTrace();
            return "";
        }
    }
    public static String getFilePath(String name) {
        try {
            File rootDir = Files.createTempDirectory("AppTool_").toFile();
            rootDir.deleteOnExit();
            File nameFile = new File(rootDir.getPath() + "/" + name);
            if (!nameFile.getParentFile().exists()) {
                nameFile.getParentFile().mkdirs();
            }
            StreamUtils.copy(Jars.class.getResourceAsStream(name), new FileOutputStream(nameFile), true);
            return nameFile.getAbsolutePath();
        }catch (Exception e){
            if(Ferba.MODE_MENU) e.printStackTrace();
            return "";
        }
    }
}
