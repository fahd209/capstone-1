package com.pluralsight;

import java.util.InputMismatchException;
import java.util.Scanner;

public class application {
    private static Scanner userInput = new Scanner(System.in);
    public void run()
    {
        homeScreen();
    }

    private void homeScreen()
    {
        String input = "";
        while(!input.equals("X")) {
            try {
                System.out.println();
                System.out.println("Home screen");
                System.out.println("-------------");
                System.out.println("D) ADD Deposit");
                System.out.println("P) Make Payment");
                System.out.println("L) Ledger");
                System.out.println("X) Exit");
                System.out.print("Enter input: ");
                input = userInput.nextLine().strip().toUpperCase();
                switch (input)
                {
                    case "D":
                        addDeposit();
                        break;
                    case "P":
                        makePayment();
                        break;
                    case "L":
                        ledgerScreen();
                        break;
                    case "X":
                        System.out.println("Good bye :)");
                        break;
                    default:
                        System.out.println();
                        System.out.println("Invalid input");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Invalid input");
            }
        }
    }

    private void addDeposit()
    {
        while (true)
        {
            try
            {
                System.out.println();
                System.out.print("Enter deposit description: ");
                String depositDescription = userInput.nextLine();

                System.out.println();
                System.out.print("Enter Deposit vendor: ");
                String depositVendor = userInput.nextLine();

                System.out.println();
                System.out.print("Enter deposit amount: ");
                double depositAmount = userInput.nextDouble();
                userInput.nextLine();

                Deposit deposit = new Deposit(depositDescription, depositVendor, depositAmount);
                deposit.logDeposit();

                //System.out.printf(" %s | %s | $%.2f \n",depositDescription, depositVendor, depositAmount );
                return;
            }
            catch (InputMismatchException e)
            {
                System.out.println();
                System.out.println("Invalid input");
            }
            catch (Exception e)
            {
                System.out.println();
                System.out.println("Something went wrong");
                e.printStackTrace();
            }
        }
    }

    private void makePayment()
    {
        System.out.println("Making payment");
    }

    private void ledgerScreen()
    {
        System.out.println("Ledger screen");
    }
}
