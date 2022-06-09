package cn.humenger.ferba.gui;

import cn.humenger.ferba.RuntimeEnv;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ApkSignWindow extends BaseJFrame {
    public ApkSignWindow(RuntimeEnv runtimeEnv){
        super(runtimeEnv);
        setTitle("APK SIGN");
        JPanel panel=new ApkSignPanel(runtimeEnv);
        Rectangle rectangle = GuiHelper.getSizeRatio(0.5f);
        panel.setPreferredSize(new Dimension(rectangle.width, rectangle.height));
        add(panel);
        setBounds(rectangle);
        pack();
        setVisible(true);
    }
    static class ApkSignPanel extends BaseJPanel{
        ApkSignPanel(RuntimeEnv runtimeEnv){
            super(runtimeEnv);
            setLayout(new BorderLayout());
            JPanel textBoxPanel = new JPanel();
            textBoxPanel.add(new JLabel("APK File:"));
            textBoxPanel.add(new JTextField(20));
            textBoxPanel.add(new JButton("SEC"));
            add(textBoxPanel,BorderLayout.PAGE_START);
        }
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        RuntimeEnv runtimeEnv=RuntimeEnv.createDefaultEnv();
        UIManager.setLookAndFeel(runtimeEnv.guiEnv.lookAndFeel);
        ApkSignWindow apkSignWindow=new ApkSignWindow(runtimeEnv);
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setCurrentDirectory(new File("D:/tool/"));
        fileChooser.showOpenDialog(apkSignWindow);
    }
}
