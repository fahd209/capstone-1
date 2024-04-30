package com.pluralsight;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
                String depositInput = userInput.nextLine();

                if(!depositInput.equals(""))
                {
                    double depositAmount = Double.parseDouble(depositInput);
                    TransActions deposit = new TransActions(depositDescription, depositVendor, depositAmount);
                    deposit.logDeposit();
                    return;
                }
                else
                {
                    System.out.println("If you skip the payment amount it will not get be added to the transactions");
                }

            }
            catch (InputMismatchException e)
            {
                System.out.println();
                System.out.println("Invalid input");

                // consumes invalid input, so it doesn't get stored into deposit description
                userInput.nextLine();
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
        while (true)
        {
            try
            {
                System.out.println();
                System.out.print("Enter payment description: ");
                String paymentDescription = userInput.nextLine();

                System.out.println();
                System.out.print("Enter payment vendor: ");
                String paymentVendor = userInput.nextLine();

                System.out.println();
                System.out.print("Enter payment amount: ");
                String paymentInput = userInput.nextLine();
                //userInput.nextLine();

                if(!paymentInput.equals(""))
                {
                    double paymentAmount = Double.parseDouble(paymentInput);
                    paymentAmount = paymentAmount * -1;
                    TransActions payment = new TransActions(paymentDescription, paymentVendor, paymentAmount);
                    payment.logPayment();
                    return;
                }
                else
                {
                    System.out.println("If you skip the payment amount it will not get be added to the transactions");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println();
                System.out.println("Invalid input");

                // consumes invalid input, so it doesn't get stored into deposit description
                userInput.nextLine();
            }
            catch (Exception e)
            {
                System.out.println();
                System.out.println("Something went wrong");
                e.printStackTrace();
            }
        }
    }

    private void ledgerScreen()
    {
        String choice = "";
        while(!choice.equals("H"))
        {
            System.out.println();
            System.out.println("Ledger screen");
            System.out.println("---------------");
            System.out.println("A) Display all entries");
            System.out.println("D) Display all deposits");
            System.out.println("P) Display all payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Enter input: ");
            choice = userInput.nextLine().toUpperCase().strip();
            switch (choice)
            {
                case "A":
                    displayAllEntries();
                    break;
                case "D":
                    displayAllDeposits();
                    break;
                case "P":
                    displayAllPayments();
                    break;
                case "R":
                    reportsScreen();
                    break;
                case "H":
                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid input");
            }
        }
    }

    private void displayAllEntries()
    {
        LoadEntries displayAll = new LoadEntries();

        // getting the entries with loadAllEntries method in my loadEntries class and storing it to the arrayList
        ArrayList<Entry> entries = displayAll.loadAllEntries();

        //displaying out all entries
        System.out.println();
        System.out.println("                              All entry's                    ");
        System.out.println("-".repeat(80));
        for (int i = 0; i < entries.size(); i++)
        {
            Entry entry = entries.get(i);
            System.out.printf(" \u001B[34m %-10s | %-10s | %-10s | %-10s | %-10s | $%-10.2f \u001B[0m \n", entry.getDate(), entry.getTime(), entry.getTransActionType(), entry.getDescription(), entry.getVendor(), entry.getAmount());
            System.out.println("-".repeat(80));
        }

    }

    private void displayAllDeposits()
    {
        LoadEntries loadEntries = new LoadEntries();

        // getting all deposits with loadDeposits method in my loadEntries class and storing it to the arrayList
        ArrayList<Entry> allDeposits = loadEntries.loadAllDeposits();

        System.out.println();
        System.out.println("                              All deposits                    ");
        System.out.println("-".repeat(80));
        for(int i = 0; i < allDeposits.size(); i++)
        {
            Entry deposits = allDeposits.get(i);
            System.out.printf(" \u001B[32m %-10s | %-10s | %-10s | %-10s | %-10s | $%-10.2f \u001B[0m \n", deposits.getDate(), deposits.getTime(), deposits.getTransActionType(), deposits.getDescription(), deposits.getVendor(), deposits.getAmount());
            System.out.println("-".repeat(80));
        }

    }

    private void displayAllPayments()
    {
        LoadEntries loadEntries = new LoadEntries();

        // getting all payments with loadAllPayments method in my loadEntries class and storing it to the arrayList
        ArrayList<Entry> allPayments = loadEntries.loadAllPayments();

        System.out.println();
        System.out.println("                              All payments                       ");
        System.out.println("-".repeat(80));
        for(int i = 0; i < allPayments.size(); i++)
        {
            Entry payment = allPayments.get(i);
            System.out.printf(" \u001B[31m %-10s | %-10s | %-10s | %-10s | %-10s | $%-10.2f \u001B[0m \n", payment.getDate(), payment.getTime(), payment.getTransActionType(), payment.getDescription(), payment.getVendor(), payment.getAmount());
            System.out.println("-".repeat(80));
        }

    }

    private void reportsScreen()
    {
        int choice = 6;
        while(choice != 0)
        {
            try
            {
                System.out.println();
                System.out.println("Reports");
                System.out.println("-------------------");
                System.out.println("1) Month To Date");
                System.out.println("2) Previous Month");
                System.out.println("3) Year To date");
                System.out.println("4) Previous Year");
                System.out.println("5) Search by vendor");
                System.out.println("0) Go back to ledger page");
                System.out.print("Enter input:");
                choice = userInput.nextInt();
                userInput.nextLine();

                switch (choice)
                {
                    case 1:
                        getMonthToDateReports();
                        break;
                    case 2:
                        getPreviousMonthReports();
                        break;
                    case 3:
                        getYearToDateReports();
                        break;
                    case 4:
                        getPreviousYear();
                        break;
                    case 5:
                        searchByVendor();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid input");

                }
            }
            catch (InputMismatchException e)
            {
                userInput.nextLine();
                System.out.println();
                System.out.println("Invalid input please enter a number");
            }
            catch (Exception e)
            {
                System.out.println("Something went wrong");
                e.printStackTrace();
            }
        }

    }

    public void getMonthToDateReports()
    {
        // getting Month to date reports by calling my method getMonthToDate reports and storing it inside the list
        Reports reports = new Reports();
        List<Entry> monthToDateReports = reports.getMonthToDate();

        System.out.println("                                                   Month to date reports                                                               ");
        System.out.println("-".repeat(140));
        for(int i = 0; i < monthToDateReports.size(); i++)
        {
            Entry MonthToDateTransaction = monthToDateReports.get(i);
            System.out.printf(" %-20s | %-20s | %-20s | %-20s | %-20s | $%-20.2f \n", MonthToDateTransaction.getDate(), MonthToDateTransaction.getTime(), MonthToDateTransaction.getTransActionType(), MonthToDateTransaction.getDescription(), MonthToDateTransaction.getVendor(), MonthToDateTransaction.getAmount());
            System.out.println("-".repeat(140));
        }
    }

    public void getPreviousMonthReports()
    {
        Reports reports = new Reports();
        List<Entry> previousMonthReports = reports.getPreviousMonth();

        System.out.println("                                                   Previous Month Report's                                                               ");
        System.out.println("-".repeat(140));
        for(int i = 0; i < previousMonthReports.size(); i++)
        {
            Entry previousMonthTransactions = previousMonthReports.get(i);
            System.out.printf(" %-20s | %-20s | %-20s | %-20s | %-20s | $%-20.2f \n", previousMonthTransactions.getDate(), previousMonthTransactions.getTime(), previousMonthTransactions.getTransActionType(), previousMonthTransactions.getDescription(), previousMonthTransactions.getVendor(), previousMonthTransactions.getAmount());
            System.out.println("-".repeat(140));
        }
    }

    public void getYearToDateReports()
    {
        Reports reports = new Reports();
        List<Entry> yearToDateReports = reports.getYearToDay();

        System.out.println("                                                   Year to day reports                                                               ");
        System.out.println("-".repeat(140));
        for (int i = 0; i < yearToDateReports.size(); i++)
        {
            Entry yearToDay = yearToDateReports.get(i);
            System.out.printf(" %-20s | %-20s | %-20s | %-20s | %-20s | $%-20.2f \n", yearToDay.getDate(), yearToDay.getTime(), yearToDay.getTransActionType(), yearToDay.getDescription(), yearToDay.getVendor(), yearToDay.getAmount());
            System.out.println("-".repeat(140));
        }
    }

    public void getPreviousYear()
    {
        Reports reports = new Reports();
        List<Entry> previousYearReports = reports.getPreviousYear();

        System.out.println("                                                  Previous year reports                                                              ");
        System.out.println("-".repeat(140));
        for (int i = 0; i < previousYearReports.size(); i++)
        {
            Entry previousYear = previousYearReports.get(i);
            System.out.printf(" %-20s | %-20s | %-20s | %-20s | %-20s | $%-20.2f \n", previousYear.getDate(), previousYear.getTime(), previousYear.getTransActionType(), previousYear.getDescription(), previousYear.getVendor(), previousYear.getAmount());
            System.out.println("-".repeat(140));
        }
    }

    public void searchByVendor()
    {
        Reports reports = new Reports();
        System.out.println();
        System.out.print("Enter the vendor name: ");
        String vendor = userInput.nextLine().strip();
        List<Entry> reportsByVendor = reports.searchByVendor(vendor);

        System.out.println();
        System.out.println("Vendor's name: " + vendor);
        System.out.println("                                                  Report's by vendor                                                              ");
        System.out.println("-".repeat(140));
        for (int i = 0; i < reportsByVendor.size(); i++)
        {
            Entry transactionsByVendor = reportsByVendor.get(i);
            System.out.printf("\u001B[31m %-20s | %-20s | %-20s | %-20s | %-20s | $%-20.2f \u001B[0m \n", transactionsByVendor.getDate(), transactionsByVendor.getTime(), transactionsByVendor.getTransActionType(), transactionsByVendor.getDescription(), transactionsByVendor.getVendor(), transactionsByVendor.getAmount());
            System.out.println("-".repeat(140));
        }

        if(reportsByVendor.size() == 0)
        {
            System.out.println("Vendors not found");
        }
    }
}
