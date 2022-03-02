package cn.humenger.ferba;

import cn.humenger.ferba.action.ApkSignAction;
import cn.humenger.ferba.action.PullFileAction;
import cn.humenger.ferba.action.PushFileAction;
import cn.humenger.ferba.action.SwitchEnvMenu;

public class Main {
    public static void main(String[] args) {
        printInfo();
        if (!checkEnv()) return;
        printMenu();
    }

    private static void printMenu() {
        Menus.Menu rootMenu = new Menus.Menu(null);
        Menus.Menu switchEnvMenu = new SwitchEnvMenu(rootMenu);

        rootMenu.addOptions(
                new Menus.MenuOption("(re)sign apk", "", new ApkSignAction()),
                new Menus.MenuOption("push file","pc file -> mobile file",new PushFileAction()),
                new Menus.MenuOption("pull file","mobile file -> pc file",new PullFileAction()),
                new Menus.MenuOption("switch environment", "switch environment for ndk-build,frida,python,go,...", new SwitchEnvMenu.SwitchEnvAction(switchEnvMenu))
        );
        rootMenu.show();
    }

    private static boolean checkEnv() {
        if (!Windows.isWindows()) {
            System.out.println("Error: only support window OS");
            return false;
        }
        return true;
    }

    private static void printInfo() {
        System.out.println("Ferba " + Ferba.VERSION_NAME);
    }
}
