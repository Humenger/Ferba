package cn.humenger.ferba;

import cn.humenger.ferba.gui.GuiEnv;

import java.util.Locale;

public class RuntimeEnv {
    public GuiEnv guiEnv;
    public Locale locale;
    public static RuntimeEnv createDefaultEnv(){
        RuntimeEnv env=new RuntimeEnv();
        env.guiEnv=GuiEnv.createDefaultEnv();
        env.locale=new Locale("zh","CN");
        return env;
    }
}
