import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("$ ");
        Scanner sc = new Scanner(System.in);
        while (1) {
            System.out.print("$ ");
            String line = sc.nextLine();
            System.out.println(line +": command not found");
        }

        sc.close();
    }
}
