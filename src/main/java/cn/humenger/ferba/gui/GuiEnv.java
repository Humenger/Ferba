package cn.humenger.ferba.gui;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;

public final class GuiEnv {
    public int windowsWidth;

    public int windowsHeight;
    public Rectangle rectangle;
    public Dimension dimension;
    public LookAndFeel lookAndFeel;

    private float aspectRatio;
    public static GuiEnv createDefaultEnv(){
        GuiEnv env=new GuiEnv();
        env.lookAndFeel=new FlatIntelliJLaf();
        env.aspectRatio=GuiHelper.width*1.0f/GuiHelper.height;
        env.windowsWidth= (int) (GuiHelper.width*0.75f);
        env.windowsHeight= (int) (env.windowsWidth/ env.aspectRatio);
        env.dimension=new Dimension(env.windowsWidth, env.windowsHeight);
        env.rectangle=new Rectangle((GuiHelper.width-env.windowsWidth)/2,(GuiHelper.height- env.windowsHeight)/2,env.windowsWidth,env.windowsHeight);
        return env;
    }
}
