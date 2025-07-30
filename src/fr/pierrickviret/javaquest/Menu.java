package fr.pierrickviret.javaquest;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);

    public void showInformation(String data) {
        System.out.println(data);
    }

    public int listenResultBetween(int min, int max) {
        int result = 0;
        while ( result == 0 ) {
            try {
                result = Integer.parseInt(scanner.nextLine());
                if ( result > max || result < min ) {
                    result = 0;
                }
            } catch ( Exception e ) {
                scanner.nextLine();
            }
        }
        return result;
    }

    public String listenString() {
       return scanner.nextLine();
    }
}
