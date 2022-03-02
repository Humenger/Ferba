package cn.humenger.ferba.action;

import cn.humenger.ferba.*;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class SwitchEnvMenu extends Menus.Menu{
    private static final Storages.Group group= Ferba.STORAGE.group("switch_env");
    private final Menus.MenuAction createEnvAction;
    private final Menus.MenuAction deleteEnvAction;
    public SwitchEnvMenu(Menus.Menu parent) {
        super(parent);
        createEnvAction=new SwitchEnvMenu.CreateEnvAction();
        deleteEnvAction=new SwitchEnvMenu.DeleteEnvAction();

    }

    @Override
    public void show() {
        clearOptions();
        addOptions(
                new Menus.MenuOption("+", "create new env", createEnvAction,-1),
                new Menus.MenuOption("-", "delete env", deleteEnvAction,-1)
        );
        Map<String, Storages.Value> valueMap = group.loads();
        for (String k:valueMap.keySet()){
            if(valueMap.get(k).toString().isEmpty())continue;
            addOptions(new Menus.MenuOption(k,"",new Menus.MenuAction(){
                @Override
                public void doAction() {
                    System.out.printf("%s=%s%n",k,valueMap.get(k));
                    CommandUtils.run("cmd","/c","start", "set","PATH="+valueMap.get(k));
                }
            }));
        }
        super.show();
    }

    public static class SwitchEnvAction extends Menus.MenuAction {
        private final Menus.Menu menu;
        public SwitchEnvAction(Menus.Menu menu){
            this.menu=menu;
        }
        @Override
        public void doAction() {

            if(this.menu!=null){
                this.menu.show();
            }
        }

    }
    public static class CreateEnvAction extends Menus.MenuAction{

        @Override
        public void doAction() {
            Pattern pattern=Pattern.compile("([a-zA-Z0-9_-]+)=(.*)");
            String env= Consoles.readString("please input env(eg.PythonEnv=/to/path/python;/xx/x;)>");
            Matcher matcher=pattern.matcher(env);
            if(matcher.find()){
                group.store(matcher.group(1),new Storages.Value(matcher.group(2)));
                System.out.println("success add env!:"+env);
            }
        }
    }
    public static class DeleteEnvAction extends Menus.MenuAction{

        @Override
        public void doAction() {
            String key= Consoles.readString("please input env key(eg.PythonEnv)>");
            group.store(key,new Storages.Value(""));
            System.out.println("success delete env!:"+key);
        }
    }

}
