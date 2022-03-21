package cn.humenger.ferba;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.util.Scanner;

public class Consoles {

    private static LineReader lineReader;

    public static void printf(String... messages){
        for (String msg:messages){
            System.out.print(msg);
        }
        System.out.println();
    }
    public static String readString(String tips) {
        if(Ferba.MODE_MENU) System.out.print(tips);
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            return scanner.next();
        }
        return "";
    }

    public static String readLine(String tips) {
        try {
            if (lineReader == null) {
                Terminal terminal = TerminalBuilder.builder()
                        .system(true)
                        .build();

                lineReader = LineReaderBuilder.builder()
                        .terminal(terminal)
                        .build();
            }
            return lineReader.readLine(tips);
        } catch (Exception e) {
            if(Ferba.MODE_MENU) e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println("readLine: " + readLine("Ferba> "));
        System.out.println("readLine: " + readLine("Ferba> "));
        System.out.println("readLine: " + readLine("Ferba> "));
    }
}
