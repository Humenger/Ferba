package cn.humenger.ferba;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

public final class Jars {
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
            e.printStackTrace();
            return "";
        }
    }
}
