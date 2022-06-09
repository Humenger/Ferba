package cn.humenger.ferba.gui;

import cn.humenger.ferba.RuntimeEnv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AndroidTab extends BaseJPanel implements ActionListener {
    private final JToolBar toolBar = new JToolBar();
    private final static String ACTION_APK_SIGN = "APK_SIGN";

    public AndroidTab(RuntimeEnv runtimeEnv) {
        super(runtimeEnv);
        setLayout(new BorderLayout());
        //init toolbar

        //aok sign
        JButton apkSignBtn = new JButton("apk sign");
        apkSignBtn.setActionCommand(ACTION_APK_SIGN);
        apkSignBtn.addActionListener(this);
        toolBar.add(apkSignBtn);
        add(toolBar, BorderLayout.PAGE_START);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("action:" + e.getActionCommand());
        switch (e.getActionCommand()) {
            case ACTION_APK_SIGN:

                new ApkSignWindow(runtimeEnv);

        }
    }

}
