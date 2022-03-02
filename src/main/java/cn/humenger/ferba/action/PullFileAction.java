package cn.humenger.ferba.action;

import cn.humenger.ferba.CommandUtils;
import cn.humenger.ferba.Consoles;
import cn.humenger.ferba.Menus;
import cn.humenger.ferba.Windows;

public class PullFileAction extends Menus.MenuAction {
    @Override
    public void doAction() {
        String remoteFilePath= Consoles.readString("please input remote file path>");
        String localFilePath= Consoles.readString("please input local file path>");
        String adbPath= Windows.getAdbPath();
        System.out.printf("----------[Pull file]----------%n%s%n----------[Pull file]----------%n", CommandUtils.run(String.format("%s pull %s %s",adbPath,remoteFilePath,localFilePath)).data);
    }
}
