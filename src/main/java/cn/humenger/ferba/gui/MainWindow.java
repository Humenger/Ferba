package cn.humenger.ferba.gui;

import cn.humenger.ferba.RuntimeEnv;
import cn.humenger.ferba.resources.Strings;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainWindow extends BaseJPanel {


    //tab apksign file menu

    static final private String OPEN="OPEN";
    static final private String SAVE="SAVE";
    static final private String NEW="NEW";

    public MainWindow(RuntimeEnv runtimeEnv){
        super(runtimeEnv);
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane=new JTabbedPane();
        addTab(tabbedPane);
        setPreferredSize(runtimeEnv.guiEnv.dimension);
        add(tabbedPane,BorderLayout.PAGE_START);
    }
    protected void addTab(JTabbedPane tabbedPane)
    {
        tabbedPane.addTab("Home",new HomeTab(runtimeEnv));
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.addTab("Tool",new ToolTab(runtimeEnv));
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        tabbedPane.addTab("Android",new AndroidTab(runtimeEnv));
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
    }
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        RuntimeEnv runtimeEnv=RuntimeEnv.createDefaultEnv();
        JFrame.setDefaultLookAndFeelDecorated(true);
        UIManager.setLookAndFeel(new FlatLightLaf());
        JFrame frame=new JFrame("中文");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel=new MainWindow(runtimeEnv);
        panel.setOpaque(true);
        frame.setContentPane(panel);
        frame.setBounds(runtimeEnv.guiEnv.rectangle);
        frame.pack();
        frame.setVisible(true);
    }
}
