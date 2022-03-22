package cn.humenger.ferba.action;

import cn.humenger.ferba.CommandUtils;
import cn.humenger.ferba.Consoles;
import cn.humenger.ferba.Jars;
import cn.humenger.ferba.Menus;

public class HypervisorMenu extends Menus.Menu {
    public HypervisorMenu(Menus.Menu parent) {
        super(parent);
        addOptions(
                new Menus.MenuOption("off","for android emulator & virtual machine",new VTxOffAction()),
                new Menus.MenuOption("auto","for WSL",new VTxAutoAction()),
                new Menus.MenuOption("query","query current state",new QueryAction())
        );
    }

    @Override
    public void show() {
        super.show();
    }
    public static class VTxAction extends Menus.MenuAction{
        private final Menus.Menu menu;
        public VTxAction(Menus.Menu menu){
            this.menu=menu;

        }
        @Override
        public void doAction() throws Exception {
            if(menu!=null){
                menu.show();
            }
        }
    }
    public static class VTxOffAction extends Menus.MenuAction{
        @Override
        public void doAction() throws Exception {
//            String batPath= Jars.getFilePath("/tool/changeHypervisorState.bat","{launch_type}","off");
            Consoles.println(CommandUtils.run("bcdedit /set hypervisorlaunchtype off","GBK").data);
            Consoles.println("[result] take effect after restarting the computer");
        }
    }
    public static class VTxAutoAction extends Menus.MenuAction{
        @Override
        public void doAction() throws Exception {
//            String batPath= Jars.getFilePath("/tool/changeHypervisorState.bat","{launch_type}","auto");
            Consoles.println(CommandUtils.run("bcdedit /set hypervisorlaunchtype auto","GBK").data);
            Consoles.println("[result] take effect after restarting the computer");
        }
    }
    public static class QueryAction extends Menus.MenuAction{
        @Override
        public void doAction() throws Exception {
            Consoles.println("[result] ",CommandUtils.run("cmd /c bcdedit | findstr hypervisorlaunchtype","GBK").data);
        }
    }
}
