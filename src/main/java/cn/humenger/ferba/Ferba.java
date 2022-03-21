package cn.humenger.ferba;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Ferba {
    public static final String VERSION_NAME="1.0.0";
    public static final int VERSION_CODE=1;
    public static final Path ROOT_PATH = Paths.get(Windows.userHome(),".Ferba","storages");
    public static final Storages.Storage STORAGE=new Storages.Storage(ROOT_PATH);
    public static boolean MODE_MENU=false;
}
