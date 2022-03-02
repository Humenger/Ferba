package cn.humenger.ferba;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storages {
    //kv
    //group
    //
    public static class Storage{
        Path rootPath;
        public Storage(String rootPath){
            this(Paths.get(rootPath));
        }
        public Storage(Path rootPath){
            this.rootPath=rootPath;
            try {
                Files.createDirectories(this.rootPath,new FileAttribute[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public Group group(String name){
            return new Group(this,name);
        }
    }
    public static class Group{
        Storage storage;
        String name;
        public Group(Storage storage,String name){
            this.storage=storage;
            this.name=name;
        }
        public void store(String key, Value value){
            if(key.contains("="))throw new IllegalArgumentException("key is not contains '='");
            try {
                Path groupPath=storage.rootPath.resolve(name);
                if(!groupPath.toFile().exists()){
                    Files.createFile(groupPath);
                }
                List<String> lines=Files.readAllLines(groupPath);
                Map<String,String> lineMap=new HashMap<>();
                Pattern pattern=Pattern.compile("([a-zA-Z0-9_-]+)=(.*)");
                for (String line:lines){
                    line=line.trim();
                    Matcher matcher= pattern.matcher(line);
                    if(matcher.find()){
                        lineMap.put(matcher.group(1),matcher.group(2));
                    }
                }
                lineMap.put(key,value.toString());
                FileOutputStream outputStream=new FileOutputStream(groupPath.toFile());
                for (String k:lineMap.keySet()){
                    outputStream.write((k+"="+lineMap.get(k)+Windows.CRLF).getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                }
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public Value load(String key, Value defValue){
            try {
                Path groupPath=storage.rootPath.resolve(name);
                if(groupPath.toFile().exists()){
                    List<String> lines=Files.readAllLines(groupPath);
                    Pattern pattern=Pattern.compile("([a-zA-Z0-9_-]+)=(.*)");
                    for (String line:lines){
                        line=line.trim();
                        Matcher matcher= pattern.matcher(line);
                        if(matcher.find()&&matcher.group(1).equalsIgnoreCase(key)){
                            return new Value(matcher.group(2));
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return defValue;
        }
        public Map<String,Value> loads(){
            try {
                Path groupPath=storage.rootPath.resolve(name);
                if(groupPath.toFile().exists()){
                    List<String> lines=Files.readAllLines(groupPath);
                    Pattern pattern=Pattern.compile("([a-zA-Z0-9_-]+)=(.*)");
                    Map<String,Value> kvMap=new HashMap<>();
                    for (String line:lines){
                        line=line.trim();
                        Matcher matcher= pattern.matcher(line);
                        if(matcher.find()){
                            kvMap.put(matcher.group(1),new Value(matcher.group(2)));
                        }
                    }
                    return kvMap;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return new HashMap<>();
        }
    }
    public static class Value {
        String value;
        public Value(Object value){
            this.value=String.format("%s",value);
        }
        public String toString(){
            return value;
        }
        public int toInt(){
            return Integer.parseInt(value);
        }
        public boolean toBoolean(){
            return Boolean.parseBoolean(value);
        }
    }


    public static void main(String[] args) {
        System.out.println("root:"+Ferba.ROOT_PATH);
        Group group=new Storage(Ferba.ROOT_PATH).group("test");
        //Windows.printSystemProperties();
        group.store("test",new Value(false));
        group.store("test",new Value(""));
        group.store("test12",new Value(1429351));
        System.out.println("test12->"+group.load("test12",new Value(0)).toInt());
    }
}
