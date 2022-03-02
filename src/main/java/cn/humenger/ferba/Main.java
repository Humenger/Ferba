package cn.humenger.ferba;

import cn.humenger.ferba.action.*;

public class Main {
    public static void main(String[] args) {
        printInfo();
        if (!checkEnv()) return;
        printMenu();
    }

    private static void printMenu() {
        Menus.Menu rootMenu = new Menus.Menu(null);
        Menus.Menu switchEnvMenu = new SwitchEnvMenu(rootMenu);
        Menus.Menu fridaMenu = new FridaMenu(rootMenu);

        rootMenu.addOptions(
                new Menus.MenuOption("(re)sign apk", "", new ApkSignAction()),
                new Menus.MenuOption("push file","pc file -> mobile file",new PushFileAction()),
                new Menus.MenuOption("pull file","mobile file -> pc file",new PullFileAction()),
                new Menus.MenuOption("switch environment", "switch environment for ndk-build,frida,python,go,...", new SwitchEnvMenu.SwitchEnvAction(switchEnvMenu)),
                new Menus.MenuOption("pip install", "python pip install module", new PipInstallAction()),
                new Menus.MenuOption("frida", "frida menu", new FridaMenu.FridaAction(fridaMenu))
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
