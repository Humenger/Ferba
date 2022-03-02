package cn.humenger.ferba.action;

import cn.humenger.ferba.CommandUtils;
import cn.humenger.ferba.Jars;
import cn.humenger.ferba.Menus;
import cn.humenger.ferba.Windows;

public class FridaMenu extends Menus.Menu {
    public FridaMenu(Menus.Menu parent) {
        super(parent);
        addOptions(
                new Menus.MenuOption("setup frida-server arm64", "", new SetupFridaServerAction())
        );
    }

    @Override
    protected int getMaxTitleLength() {
        return super.getMaxTitleLength()+10;
    }

    @Override
    protected String buildOptionText(int i, Menus.MenuOption option) {
        return String.format("%d) %-34s  %s", i, option.name, option.summary);
    }

    public static class FridaAction extends Menus.MenuAction {
        private final Menus.Menu menu;

        public FridaAction(Menus.Menu menu) {
            this.menu = menu;

        }

        @Override
        public void doAction() {
            if (menu != null) {
                menu.show();
            }
        }
    }

    static class SetupFridaServerAction extends Menus.MenuAction {
        @Override
        public void doAction() {
            //set /p src=please input frida server source file path:
            //set /p fn=please input file name in /data/local/tmp/:
            //call adb forward tcp:27042 tcp:27042
            //call adb forward tcp:27043 tcp:27043
            //call adb push %src% /data/local/tmp/%fn%
            //call adb shell su -c "setenforce 0"
            //call adb shell su -c "chmod 777 /data/local/tmp/%fn%"
            //call adb shell su -c "./data/local/tmp/%fn%"
            String fridaServerPath = Jars.getFilePath("/tool/frida-server-15.1.2-android-arm64");
            String adbPath=Windows.getAdbPath();
            CommandUtils.Result result = CommandUtils.run(Windows.getAdbPath(), "push", fridaServerPath, "/data/local/tmp/fs1512_64");
            System.out.println("------------[setup frida server]-----------");
            result = CommandUtils.run(adbPath, "forward", "tcp:27042", "tcp:27042");
            System.out.println(result.data);
            result = CommandUtils.run(adbPath, "forward", "tcp:27043", "tcp:27043");
            System.out.println(result.data);
            result = CommandUtils.run(adbPath, "shell", "su", "-c", "\"setenforce 0\"");
            System.out.println(result.data);
            result = CommandUtils.run(adbPath, "shell", "su", "-c", "\"chmod 777 /data/local/tmp/fs1512_64\"");
            System.out.println(result.data);
            result = CommandUtils.run(adbPath, "shell", "su", "-c", "\"./data/local/tmp/fs1512_64\"");
            System.out.println(result.data);
            System.out.println("------------[setup frida server]-----------");

        }
    }
}