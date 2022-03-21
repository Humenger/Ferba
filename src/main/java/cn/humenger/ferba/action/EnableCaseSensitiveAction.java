package cn.humenger.ferba.action;

import cn.humenger.ferba.CommandUtils;
import cn.humenger.ferba.Consoles;
import cn.humenger.ferba.Jars;
import cn.humenger.ferba.Menus;

public class EnableCaseSensitiveAction extends Menus.MenuAction {
    @Override
    public void doAction() throws Exception {
        String path= Consoles.readString("please input path> ");
        String batPath= Jars.getFilePath("/tool/enableCaseSensitive.bat","{target_path}",path);// because %1 cannot read input param
        Consoles.println("[result] ",CommandUtils.run(batPath).data);
        Thread.sleep(100);//delay 100 ms wait previous exec finish
        Consoles.println("[result] ",CommandUtils.run("fsutil.exe file queryCaseSensitiveInfo "+path,"GBK").data);//why GBK? because it's out is contains chinese character
    }
}
