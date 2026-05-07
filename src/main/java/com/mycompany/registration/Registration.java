/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registration;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 *
 * @author Student
 */
public class Registration {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login loginLogic = new Login();
        int mainChoice;
        
        do{
            System.out.println("\n=== REGISTER & LOGIN SYSTEM ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            mainChoice = input.nextInt();
            input.nextLine(); 
            switch (mainChoice) {
                case 1:
                    runRegistration(input, loginLogic);
                    break;
                case 2:
                    runLoginAndChat(input, loginLogic);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (mainChoice != 3);

        input.close();
    }
    
    public static void runRegistration(Scanner input, Login loginLogic){
        
        //Registration
        System.out.println("\n---Registration---");
        
        System.out.print("First Name: ");
        loginLogic.setFirstName(input.nextLine());
        
        System.out.print("Last Name: ");
        loginLogic.setLastName(input.nextLine());
        
        System.out.print("Username: ");
        loginLogic.setUsername(input.nextLine());
        
        System.out.print("Password: ");
        loginLogic.setPassword(input.nextLine());
        
        System.out.println("Cell Phone Number(+27): ");
        loginLogic.setCellPhoneNumber(input.nextLine());
        
        String regStatus = loginLogic.registerUser();
        System.out.println("\nRegistration Status: " + regStatus);
    }
    
    public static void runLoginAndChat(Scanner input, Login loginLogic){
        //Login
     
            System.out.print("\n---Login---");
            
            System.out.print("Enter Username: ");
            String enteredUsername = input.nextLine();
            
            System.out.print("Enter Password: ");
            String enteredPassword = input.nextLine();
            
            String loginStatus = loginLogic.returnLoginStatus(enteredUsername, enteredPassword);
            System.out.println("\n" + loginStatus);
            
            if(loginLogic.loginUser(enteredUsername, enteredPassword)){
                showQuickChatMenu(input);
            }
    }
    
    public static void showQuickChatMenu(Scanner input){
        int choice;
        
        do{
            System.out.println("\n===QUICKCHAT MENU===");
            
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Logout");
            System.out.println("Choose option: ");
            
            choice = input.nextInt();
            input.nextLine();
            
            if(choice == 1){
                initializeMessaging(input);
            }else if(choice == 2 ){
                System.out.println("Coming Soon");
            }
            
        }while(choice != 3);
    }
    
    public static void initializeMessaging(Scanner input){
        
        System.out.print("\nHow many messages do you want to send?");
        
        int num = input.nextInt();
        input.nextLine();
        int sentCount = 0;
        
        for(int i = 0; i < num; i++){
             System.out.println("\n--- Message " + i + " ---");
             
            System.out.print("Enter recipient: ");
            String recipient = input.nextLine();
            
            System.out.print("Enter message (max 250): ");
            String text = input.nextLine();

            if (text.length() > 250) {
                System.out.println("Message too long!");
                continue;
            }

            Message message = new Message(i, recipient, text);
            System.out.println(message.checkRecipientCell());
            
            if(message.checkRecipientCell().contains("successflly")){
                System.out.println("1, Send 2. Disregard 3. Store");
                
                int action = input.nextInt();
                input.nextLine();
                
                if(action == 1){
                    sentCount++;
                    System.out.println("Message Sent!");
                    System.out.println("ID: " + message.getMessageId());
                    System.out.println("Hash: " + message.createMessageHash());
                }
            }
        }
        System.out.println("\nTotal messages sent in this session: " + sentCount);
    }
}
