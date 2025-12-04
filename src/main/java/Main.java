import java.io.File;
import java.util.*;
import java.io.IOException;

public class Main {

    private static String[] validCommands = {"echo", "exit", "type"};
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            String line = sc.nextLine();
            if (line.equals("exit")) {
                break;
            }
            String [] parts = line.trim().split(" ");
            if(parts.length > 0 && parts[0].equals("echo")){
                for(int i=1; i<parts.length; i++){
                    System.out.print(parts[i] + " ");
                }
                System.out.print("\n");
            }

            else if(parts[0].equals("type")){
                typeFunction(parts);
            }

            else if(validFunction(parts)){
                executeFunction(parts);
            }

            else{
                System.out.println(line +": command not found");
            }

    
        }
        sc.close();
    }

    public static void typeFunction(String[] s){
        for(String command : validCommands){
            if(command.equals(s[1])){
                System.out.println(s[1]+ " is a shell builtin");
                return;
            }
        }

            String path = System.getenv("PATH");
            String[] directories = path.split(File.pathSeparator);

            for (String dir : directories) {
                File file = new File(dir, s[1]);
                if (file.isFile() && file.canExecute()) {
                    System.out.println(s[1] + " is " + file.getAbsolutePath());
                    return;
                }
    }
        System.out.println(s[1] +": not found");
    }

    private static boolean validFunction(String[] s){
        String cmd = s[0];

        for(String command : validCommands){
            if(command.equals(cmd)){
                return true;
            }
        }
            String path = System.getenv("PATH");
            String[] directories = path.split(File.pathSeparator);

            for (String dir : directories) {
                File file = new File(dir, s[1]);
                if (file.isFile() && file.canExecute()) {
                    return true;
                }
    }
        return false;
    }

    public static void executeFunction(String [] s){
        try{
        ProcessBuilder p = new ProcessBuilder(s);
        p.inheritIO();
        Process process = p.start;
        process.waitFor();
        }

        catch(Exception e){
            System.out.println(e.printStackTrace());
        }

    }
    }
