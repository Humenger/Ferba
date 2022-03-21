package cn.humenger.ferba.action;

import cn.humenger.ferba.*;

import java.io.File;
import java.io.IOException;

public class SoDumpAction extends Menus.MenuAction {
    @Override
    public void doAction() throws Exception {
        String adbPath= Windows.getAdbPath();
        String soPath= Consoles.readLine("please input so name> ");
//        String soFixer="";
//        String soName=new File(soPath).getName();
//        if(Androids.isARM64(adbPath)){
//            soFixer="SoFixer64";
//        }else if(Androids.isARM32(adbPath)){
//            soFixer="SoFixer32";
//        }else {
//            System.out.println("error: only support arm32 & arm64");
//            return;
//        }
//        CommandUtils.run(adbPath,"push",Jars.getFilePath("/tool/"+soFixer),"/data/local/tmp/SoFixer");
//        CommandUtils.run(adbPath,"shell","chmod","+x","/data/local/tmp/SoFixer");
//        CommandUtils.run(adbPath,"push",soPath,"/data/local/tmp/");
//        CommandUtils.run(adbPath,"shell","/data/local/tmp/SoFixer","-m",soBaseAddress,"-s","/data/local/tmp/"+soName,"-o","/data/local/tmp/"+soName+".fix.so");
//        CommandUtils.run(adbPath,"pull","/data/local/tmp/"+soName+".fix.so",String.format("%s_%s_fix.so",soPath,soBaseAddress));
//        System.out.println("fix so path: "+String.format("%s_%s_fix.so",soPath,soBaseAddress));

    }
}
