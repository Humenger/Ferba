package cn.humenger.ferba.action;

import cn.humenger.ferba.CommandUtils;
import cn.humenger.ferba.Jars;
import cn.humenger.ferba.Menus;

import java.util.Scanner;

public class ApkSignAction extends Menus.MenuAction {
    @Override
    public void doAction() {
        System.out.println("----------start sign apk---------");
        System.out.print("please input apk path>");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String path = scanner.next();
            CommandUtils.Result result = CommandUtils.run("java -jar " + Jars.getFilePath("/tool/uber-apk-signer-1.2.1.jar") + " --allowResign  -a " + path);
            System.out.println("----------[Apk Sign]-----------\n" + result.data + "\n------------[Apk Sign]------------");
        }
    }
}
