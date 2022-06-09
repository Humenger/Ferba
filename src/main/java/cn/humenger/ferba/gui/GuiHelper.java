package cn.humenger.ferba.gui;

import java.awt.*;

public class GuiHelper {
    public static int width = Toolkit.getDefaultToolkit().getScreenSize().width;

    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static void main(String[] args) {
        System.out.println("width:"+width);
        System.out.println("height:"+height);
    }
    public static Rectangle getSizeRatio(float radio){
        float aspectRatio=GuiHelper.width*1.0f/GuiHelper.height;
        int windowsWidth= (int) (GuiHelper.width*radio);
        int windowsHeight= (int) (windowsWidth/ aspectRatio);
        return new Rectangle((GuiHelper.width-windowsWidth)/2,(GuiHelper.height- windowsHeight)/2,windowsWidth,windowsHeight);
    }
}
