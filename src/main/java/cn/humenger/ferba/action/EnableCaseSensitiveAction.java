package cn.humenger.ferba.action;

import cn.humenger.ferba.CommandUtils;
import cn.humenger.ferba.Consoles;
import cn.humenger.ferba.Jars;
import cn.humenger.ferba.Menus;

public class EnableCaseSensitiveAction extends Menus.MenuAction {
    @Override
    public void doAction() throws Exception {
        String path= Consoles.readString("please input path> ");
        String batPath= Jars.getFilePath("/tool/enableCaseSensitive.bat");
        Consoles.printf("[result] ",CommandUtils.run("cmd","/c",batPath,path).data);
    }
}
