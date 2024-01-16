package login.thread;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static login.thread.Colors.*;


public class Main {

    public static void main(String[] args) {
        System.out.println(ANSI_GREEN+"--------------------------------------------------");
        System.out.println("       Welcome to the Login Application");
        System.out.println("--------------------------------------------------");
        Scanner input = new Scanner(System.in);
        String userInput = "";
        String usernameInput;
        String username = null;
        String passwordInput;
        String password = null;

        while(!userInput.equals("exit")){
            System.out.println();
            System.out.println(ANSI_RESET+"------------------------------");
            System.out.println("What would you like to do? (Type 1, 2, 3, or exit)");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("exit");
            System.out.println("------------------------------");
            System.out.print(ANSI_BLUE+ "Select an option: "+ANSI_RESET);
            userInput = input.nextLine();

            if(userInput.equals("1")){
                System.out.println(ANSI_RESET+"--------------------------------------------------"+ANSI_RESET);
                System.out.print(ANSI_BLUE+"Please create a username: "+ANSI_RESET);
                usernameInput = input.nextLine();
                System.out.println(ANSI_PURPLE+"--------------------------------------------------");
                System.out.println("Password must:");
                System.out.println("\t\t- Contain minimum 7 characters.");
                System.out.println("\t\t- Contain one uppercase letter.");
                System.out.println("\t\t- Contain one lowercase letter.");
                System.out.println("\t\t- Contain at least 1 number.");
                System.out.println("\t\t- Contain at least one special character.");
                System.out.println("--------------------------------------------------"+ANSI_RESET);
                while(password==null){
                    System.out.print(ANSI_BLUE+ "Please create a password: "+ANSI_RESET);
                    passwordInput = input.nextLine();
                    if(validatePW(passwordInput)){
                        username = usernameInput;
                        password = passwordInput;
                        System.out.println(ANSI_GREEN+ "Success. Account Created!"+ANSI_RESET);
                    }
                    else{
                        System.out.println(ANSI_RED+"Password does not meet requirements."+ANSI_RESET);
                    }

                }
            }
            else if(userInput.equals("2")){
                if(password != null){
                    boolean run = true;
                    while(run){
                        System.out.println("--------------------------------------------------");
                        System.out.print(ANSI_BLUE+"Username: "+ANSI_RESET);
                        usernameInput = input.nextLine();
                        if(usernameInput.equals(username)){
                            int counter=3;
                            while(counter>0){
                                System.out.print(ANSI_BLUE+"Password ("+counter+" attempts): "+ANSI_RESET);
                                passwordInput = input.nextLine();

                                if(passwordInput.equals(password)){
                                    System.out.println(GREEN_BOLD+WHITE_UNDERLINED+"LOGIN SUCCESSFUL!"+ANSI_RESET);
                                    System.exit(0);
                                }
                                else{
                                    System.out.println(ANSI_RED+"WRONG PASSWORD");
                                    counter--;
                                }
                            }
                            System.out.println(ANSI_RED+"You have been locked out."+ANSI_RESET);
                            System.exit(0);
                        }
                        else{
                            System.out.println(ANSI_RED+"There is no account associated with this username."+ANSI_RESET);
                        }
                    }
                }
                else{
                    System.out.println(ANSI_RED+"There is no account created yet."+ANSI_RESET);
                }
            }
            else{
                System.out.println(ANSI_RED+"Invalid Choice."+ANSI_RESET);
            }
        }
        System.out.println("-------------");
        System.out.println("THANK YOU!");
        System.out.println("-------------");

    }

    public static boolean validatePW(String password){
        /*
        The Password must:
            - Contain at least 7 characters
            - Contain one uppercase letter
            - Contain one lowercase letter
            - Contain at least 1 number
         */
        String passwordReGex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%&^*-]).{7,}$";

        Pattern pwPattern = Pattern.compile(passwordReGex);
        Matcher pwMatcher = pwPattern.matcher(password);
        return pwMatcher.matches();

    }

}
