package models;

import java.util.Scanner;

/**
 * models.Utility class to store general error checking methods.
 */
public class Utility {

    private final static Scanner scan = new Scanner(System.in);

    /**
     * Force integer input from user
     * @return int input
     */
    public static int checkIntInput() {
        int input;

        while (true) {
            if (scan.hasNextInt()) {
                input = scan.nextInt();
            } else {
                System.out.println("Please enter a valid integer (0, 1, 2, ...).");
                scan.next();
                continue;
            }

            break;
        }

        return input;
    }
}
