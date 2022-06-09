package cn.humenger.ferba.gui;

import cn.humenger.ferba.RuntimeEnv;

import javax.swing.*;
import java.awt.*;

public abstract class BaseJPanel extends JPanel {
    protected final RuntimeEnv runtimeEnv;
    public BaseJPanel(RuntimeEnv runtimeEnv){
        this.runtimeEnv=runtimeEnv;
    }
}
