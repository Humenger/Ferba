package cn.humenger.ferba.gui;

import cn.humenger.ferba.RuntimeEnv;

import javax.swing.*;

public abstract class BaseJFrame extends JFrame {
    protected final RuntimeEnv runtimeEnv;
    public BaseJFrame(RuntimeEnv runtimeEnv){
        this.runtimeEnv=runtimeEnv;
    }
}
