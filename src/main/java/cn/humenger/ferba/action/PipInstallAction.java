package cn.humenger.ferba.action;

import cn.humenger.ferba.CommandUtils;
import cn.humenger.ferba.Consoles;
import cn.humenger.ferba.Menus;

public class PipInstallAction extends Menus.MenuAction {
    @Override
    public void doAction() {
        //todo pip install
        String libName= Consoles.readString("please input python module name>");
        System.out.println("start install "+libName);
        CommandUtils.Result result=CommandUtils.run("cmd","/c","pip","install",libName,"-i","http://pypi.douban.com/simple", "--trusted-host", "pypi.douban.com");
        System.out.println("--------[pip install]--------");
        System.out.println(result.data);
        System.out.println("--------[pip install]--------");
    }
}
