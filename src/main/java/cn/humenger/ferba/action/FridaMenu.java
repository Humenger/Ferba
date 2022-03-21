package cn.humenger.ferba.action;

import cn.humenger.ferba.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class FridaMenu extends Menus.Menu {
    public FridaMenu(Menus.Menu parent) {
        super(parent);
        addOptions(
                new Menus.MenuOption("setup frida-server arm64", "", new SetupFridaServerAction()),
                new Menus.MenuOption("jni trace", "", new JNITraceAction()),
                new Menus.MenuOption("r0tracer", "", new R0TracerAction())
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
            String adbPath=String.format("\"%s\"",Windows.getAdbPath());
            System.out.println("------------[setup frida server]-----------");
            CommandUtils.Result result = CommandUtils.run(Windows.getAdbPath(), "push", fridaServerPath, "/data/local/tmp/fs1512_64");
            System.out.println(result.data);
            result = CommandUtils.run(adbPath, "forward", "tcp:27042", "tcp:27042");
            System.out.println(result.data);
            result = CommandUtils.run(adbPath, "forward", "tcp:27043", "tcp:27043");
            System.out.println(result.data);
//            result = CommandUtils.run(adbPath, "shell", "su", "-c", "\"setenforce 0\"");
            result = CommandUtils.run(adbPath, "shell", "su", "root", "setenforce 0");
            System.out.println(result.data);
//            result = CommandUtils.run(adbPath, "shell", "su", "-c", "\"chmod 777 /data/local/tmp/fs1512_64\"");
            result = CommandUtils.run(adbPath, "shell", "su", "root", "chmod 777 /data/local/tmp/fs1512_64");
            System.out.println(result.data);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CommandUtils.Result result = CommandUtils.run("cmd /c start cmd /k "+adbPath+" shell su root ./data/local/tmp/fs1512_64");
                    System.out.println(result.data);
                }
            }).start();

            System.out.println("------------[setup frida server]-----------");

        }
    }
    static class JNITraceAction extends Menus.MenuAction{
        @Override
        public void doAction() {
            if(!CommandUtils.run("where jnitrace").data.contains("jnitrace")){
                System.out.println("please install jnitrace(https://github.com/chame1eon/jnitrace) first");
            }else {
//                System.out.println("-----------[app process list]----------");
//                System.out.println(CommandUtils.run("adb shell su -c \"pm list packages -3\"").data);
//                System.out.println("-----------[app process list]----------");
                String packageName=Consoles.readString("please input app package name>");
//                System.out.println("------------[app so list]-------------");
//                System.out.println(CommandUtils.run("adb shell su -c \"ls -l /data/user/0/"+packageName+"/lib/\"").data);
//                System.out.println("------------[app so list]-------------");
                String soName= Consoles.readString("please input target so name(eg.libxxxx.so)>");
                System.out.println("-------------[Jni trace]------------");
                System.out.println(CommandUtils.run("cmd /c start cmd /k jnitrace -l "+soName+" "+packageName));
                System.out.println("-------------[Jni trace]------------");
            }
        }
    }
    static class R0TracerAction extends Menus.MenuAction{
        @Override
        public void doAction() throws IOException {
            //{target.package.name}
            String packageName=Consoles.readString("please input target package>");
            String clazz=Consoles.readString("please input target class>");
            Path r0tracerPath=Paths.get(Jars.getFilePath("/tool/r0tracer.js"));
            byte[] jsData=Files.readAllBytes(r0tracerPath);
            Files.write(r0tracerPath,new String(jsData).replace("{target.package.name}",clazz).getBytes(StandardCharsets.UTF_8));
            System.out.println("------------[r0tracer]------------");
            System.out.println(CommandUtils.run(String.format("frida -U -f %s -l %s  --no-pause",packageName,r0tracerPath)).data);
            System.out.println("------------[r0tracer]------------");
        }
    }
}
