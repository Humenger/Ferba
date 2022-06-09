package cn.humenger.ferba;

import cn.humenger.ferba.action.*;

public class Main {
    public static void main(String[] args) {
        pre_main();
        try {
            do_main(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            post_main();
        }
    }

    private static void pre_main() {
//        AnsiConsole.systemInstall();
//        System.out.println( ansi().eraseScreen().fg(Ansi.Color.RED).a("Hello").fg(GREEN).a(" World").reset() );
    }

    private static void do_main(String[] args) {
        printInfo();
        if (!checkEnv()) return;
        if(Ferba.MODE_MENU) System.out.println("args.length:"+args.length);
        if(args.length==0){
            printMenu();
        }else {
            switch (args[0]){
                case "-adb":
                    System.out.println(args.length==2?Windows.getAdbPath(args[1]):Windows.getAdbPath());
            }
        }

    }

    private static void printMenu() {
        Ferba.MODE_MENU=true;
        Menus.Menu rootMenu = new Menus.Menu(null);
        Menus.Menu switchEnvMenu = new SwitchEnvMenu(rootMenu);
        Menus.Menu fridaMenu = new FridaMenu(rootMenu);
        Menus.Menu vTxMenu = new HypervisorMenu(rootMenu);

        rootMenu.addOptions(
                new Menus.MenuOption("sign apk", "", new ApkSignAction()),
                new Menus.MenuOption("push file", "pc file -> mobile file", new PushFileAction()),
                new Menus.MenuOption("pull file", "mobile file -> pc file", new PullFileAction()),
                new Menus.MenuOption("switch env", "switch environment for ndk-build,frida,python,go,...", new SwitchEnvMenu.SwitchEnvAction(switchEnvMenu)),
                new Menus.MenuOption("pip install", "python pip install module", new PipInstallAction()),
                new Menus.MenuOption("frida", "frida menu", new FridaMenu.FridaAction(fridaMenu)),
                new Menus.MenuOption("dump so", "dump android so & fix", new SoDumpAction()),
                new Menus.MenuOption("case sensitive", "enable case sensitive", new EnableCaseSensitiveAction()),
                new Menus.MenuOption("hypervisor", "change hypervisor state", new HypervisorMenu.VTxAction(vTxMenu))
        );
        rootMenu.show();
    }

    private static boolean checkEnv() {
        if (!Windows.isWindows()) {
            if(Ferba.MODE_MENU) System.out.println("Error: only support window OS");
            return false;
        }
        return true;
    }

    private static void printInfo() {
        if(Ferba.MODE_MENU) System.out.println("Ferba " + Ferba.VERSION_NAME);
    }

    private static void post_main() {
//        AnsiConsole.systemUninstall();
    }


}
