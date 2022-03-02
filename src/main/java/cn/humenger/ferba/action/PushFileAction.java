package cn.humenger.ferba.action;

import cn.humenger.ferba.CommandUtils;
import cn.humenger.ferba.Consoles;
import cn.humenger.ferba.Menus;
import cn.humenger.ferba.Windows;

import java.util.Scanner;

public class PushFileAction extends Menus.MenuAction {
    @Override
    public void doAction() {
        String pcPath= Consoles.readString("please input file path>");
        String devicePath= Consoles.readString("please input target path>");
        String adbPath=Windows.getAdbPath();
        System.out.printf("----------[Push file]----------%n%s%n----------[Push file]----------%n",CommandUtils.run(String.format("%s push %s %s",adbPath,pcPath,devicePath)).data);
    }
}
