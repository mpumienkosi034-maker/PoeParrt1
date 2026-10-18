package com.mycompany.poeparrt1;

import java.util.Scanner;

public class PoeParrt1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Login register = new Login();

        // ==========================================
        // PART 1: REGISTRATION & LOGIN
        // ==========================================
        System.out.println("\n===== Register =====");
        
        System.out.println("Enter first name:");
        String firstname = scan.nextLine();
        
        System.out.println("Enter last name:");
        String lastname = scan.nextLine();

        System.out.println("Please enter the username (must contain '_' and be max 5 chars):");
        String name = scan.nextLine();

        System.out.println("Please enter the password (min 8 chars, capital letter, number, special char):");
        String password = scan.nextLine();

        System.out.println("Please enter cellphone number (must contain international code like +27):");
        String cellphone = scan.nextLine();

        register.registerUser(name, password, cellphone);

        boolean loginSuccess = false;
        
        // Login Loop
        while (!loginSuccess) {
            System.out.println("\n===== Login =====");
            System.out.println("Enter username to login:");
            String enteredusername = scan.nextLine();

            System.out.println("Enter password to login:");
            String enteredpassword = scan.nextLine();

            loginSuccess = register.loginUser(enteredusername, enteredpassword);

            if (loginSuccess) {
                System.out.println(register.returnLoginStatus(true));
            } else {
                System.out.println(register.returnLoginStatus(false));
            }
        }

        // ==========================================
        // PART 2: QUICKCHAT MENU
        // ==========================================
        if (loginSuccess) {
            System.out.println("\nWelcome to QuickChat.");
            boolean running = true;
            int numMessages = 0;
            boolean messagesSet = false;

            while (running) {
                System.out.println("\n=== QuickChat Menu ===");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");

                int choice;
                try {
                    choice = Integer.parseInt(scan.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        if (!messagesSet) {
                            System.out.print("How many messages do you wish to enter? ");
                            try {
                                numMessages = Integer.parseInt(scan.nextLine());
                                messagesSet = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number. Defaulting to 1.");
                                numMessages = 1;
                                messagesSet = true;
                            }
                        }

                        for (int i = 0; i < numMessages; i++) {
                            System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");
                            
                            System.out.print("Recipient cell (e.g., +27718693002): ");
                            String recipient = scan.nextLine();

                            System.out.print("Message text (max 250 chars): ");
                            String text = scan.nextLine();

                            if (text.length() > 250) {
                                System.out.println("Please enter a message of less than 250 characters.");
                                i--; 
                                continue;
                            }
                            
                            System.out.println("Message sent");

                            Message msg = new Message(recipient, text);

                            System.out.println(msg.checkRecipientCell());

                            System.out.println("\n--- Message Details ---");
                            System.out.println("Message ID:    " + msg.getMessageIDForMenu());
                            System.out.println("Message Hash:  " + msg.getMessageHash());
                            System.out.println("Recipient:     " + recipient);
                            System.out.println("Message:       " + text);
                            System.out.println("-----------------------");

                            System.out.print("Send / Store / Discard: ");
                            String action = scan.nextLine();
                            
                            System.out.println(msg.sentMessage(action));
                        }
                        
                        System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());
                        break;

                    case 2:
                        System.out.println("Coming Soon.");
                        break;

                    case 3:
                        System.out.println("Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option. Please choose 1, 2, or 3.");
                }
            }
        }

        scan.close();
    }
}