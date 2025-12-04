import java.io.*;
import java.util.*;

public class Main {

    private static String[] validCommands = {"echo", "exit", "type"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            String line = sc.nextLine();
            
            if (line.equals("exit")) {
                break;
            }

            String[] parts = line.trim().split(" ");
            
            if (parts.length == 0 || parts[0].isEmpty()) {
                continue;
            }

            if(parts[0].equals("echo")){
                for(int i=1; i<parts.length; i++){
                    if (i > 1) System.out.print(" ");
                    System.out.print(parts[i]);
                }
                System.out.print("\n");
            }

            else if(parts[0].equals("type")){
                if (parts.length > 1) {
                    typeFunction(parts);
                } else {
                    System.out.println("type: missing argument");
                }
            }

            else if(validFunction(parts)){
                executeFunction(parts);
            }

            else{
                System.out.println(line + ": command not found");
            }
        }
        sc.close();
    }

    public static void typeFunction(String[] s){
        String target = s[1]; 

        for(String command : validCommands){
            if(command.equals(target)){
                System.out.println(target + " is a shell builtin");
                return;
            }
        }

        String path = System.getenv("PATH");
        if (path != null) {
            String[] directories = path.split(File.pathSeparator);
            for (String dir : directories) {
                File file = new File(dir, target);
                if (file.isFile() && file.canExecute()) {
                    System.out.println(target + " is " + file.getAbsolutePath());
                    return;
                }
            }
        }
        System.out.println(target + ": not found");
    }

    private static boolean validFunction(String[] s){
        String cmd = s[0];

        for(String command : validCommands){
            if(command.equals(cmd)){
                return true;
            }
        }
        
        String path = System.getenv("PATH");
        if (path != null) {
            String[] directories = path.split(File.pathSeparator);
            for (String dir : directories) {
                File file = new File(dir, cmd);
                if (file.isFile() && file.canExecute()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void executeFunction(String[] s){
        try {
            ProcessBuilder p = new ProcessBuilder(s);
            p.inheritIO();
            
           
            Process process = p.start();
            process.waitFor();


            
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}