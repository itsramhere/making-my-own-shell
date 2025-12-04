import java.util.Scanner;

public class Main {
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
            }
    
        }
        sc.close();
    }
}
