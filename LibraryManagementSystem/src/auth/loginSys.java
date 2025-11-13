package auth;

import java.util.Scanner;
import users.member;
import users.librarian;
import model.person;

public class loginSys {

   
    private final String LIBRARIAN_PASSWORD = "admin123";

    public person login() {
        Scanner sc = new Scanner(System.in);

        System.out.println("-- Library Login --");
        System.out.println("1. Librarian");
        System.out.println("2. Member");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter your ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            
            System.out.print("Enter librarian password: ");
            String password = sc.nextLine();

            if (password.equals(LIBRARIAN_PASSWORD)) {
                System.out.println("Librarian login successful.\n");
                return new librarian(id, name);
            } else {
                System.out.println("Incorrect password! Access denied.\n");
                return null;
            }
        } else if (choice == 2) {
            System.out.println("Member login successful.\n");
            return new member(id, name);
        } else {
            System.out.println("Invalid choice!\n");
            return null;
        }
    }
}
