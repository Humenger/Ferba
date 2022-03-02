package cn.humenger.ferba;

import java.util.Scanner;

public class Consoles {
    public static String readString(String tips){
        System.out.print(tips);
        Scanner scanner=new Scanner(System.in);
        if(scanner.hasNext()){
            return scanner.next();
        }
        return "";
    }
}
