import java.io.File;
import java.util.*;

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
                typeFunction(parts[1]);
            }

            else{
                System.out.println(line +": command not found");
            }

    
        }
        sc.close();
    }

    public static void typeFunction(String s){
        for(String command : validCommands){
            if(command.equals(s)){
                System.out.println(s+ " is a shell builtin");
                return;
            }
        }

        String path = System.getenv("PATH");
        String[] directories = path.split(";");

        for (String dir : directories) {
            File file = new File(dir, s);
            if (file.exists()) {
                System.out.println(s + " is " + file.getAbsolutePath());
                return;
            }
        }


        System.out.println(s +": not found");
    }
}
