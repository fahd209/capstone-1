package com.pluralsight.ui;

import com.pluralsight.Model.Entry;
import com.pluralsight.services.LoadEntries;
import com.pluralsight.services.TransActions;
import com.pluralsight.controller.Reports;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// UI class
public class application {
    private static final Scanner userInput = new Scanner(System.in);
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
                System.out.println(Colors.YELLOW + "---Year Up account ledger---" + Colors.RESET);
                System.out.println();
                System.out.println(Colors.BACKGROUND_BLACK + "------Home screen-----" + Colors.RESET);
                System.out.println(Colors.BACKGROUND_BLACK + "----------------------" + Colors.RESET);
                System.out.println(Colors.GREEN + "D) ADD Deposit" + Colors.RESET);
                System.out.println(Colors.RED + "P) Make Payment" + Colors.RESET);
                System.out.println(Colors.CYAN + "B) Balance" + Colors.RESET);
                System.out.println(Colors.BLUE + "L) Ledger" + Colors.RESET);
                System.out.println(Colors.RED + "X) Exit" + Colors.RESET);
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
                    case "B":
                        getBalance();
                        break;
                    case "L":
                        ledgerScreen();
                        break;
                    case "X":
                        System.out.println();
                        System.out.println(Colors.CYAN + "Good bye :)" + Colors.RESET);
                        break;
                    default:
                        System.out.println();
                        System.out.println(Colors.RED + "Invalid input" + Colors.RESET);
                }
            } catch (Exception e) {
                System.out.println( Colors.RED + "Invalid input" + Colors.RESET);
            }
        }
    }

    private void getBalance()
    {
        LoadEntries loadEntries = new LoadEntries();
        ArrayList<Entry> allTransaction = loadEntries.loadAllEntries();
        double balance = 0;
        double withDrawals = 0;
        double deposits = 0;
        for(int i = 0; i < allTransaction.size(); i++)
        {
            Entry transaction = allTransaction.get(i);
            if(transaction.getTransActionType().equalsIgnoreCase("Withdrawal"))
            {
                withDrawals += transaction.getAmount();
            }

            if(transaction.getTransActionType().equalsIgnoreCase("Deposit"))
            {
                deposits += transaction.getAmount();
            }
        }
        double withDrawalAbsPostive = Math.abs(withDrawals);

        balance = deposits - withDrawalAbsPostive;

        System.out.println();
        System.out.println("Your balance is: " + Colors.GREEN + balance + Colors.RESET);

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

                if(!depositInput.isEmpty())
                {
                    double depositAmount = Double.parseDouble(depositInput);
                    TransActions deposit = new TransActions(depositDescription, depositVendor, depositAmount);
                    deposit.logDeposit();
                    return;
                }
                else
                {
                    System.out.println(Colors.RED + "Deposit failed try again" + Colors.RESET);
                }

            }
            catch (InputMismatchException e)
            {
                System.out.println();
                System.out.println(Colors.RED + "Invalid input" + Colors.RESET);

                // consumes invalid input, so it doesn't get stored into deposit description
                userInput.nextLine();
            }
            catch (Exception e)
            {
                System.out.println();
                System.out.println(Colors.RED + "Something went wrong, try again" + Colors.RESET);
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

                if(!paymentInput.isEmpty())
                {
                    double paymentAmount = Double.parseDouble(paymentInput);
                    paymentAmount = paymentAmount * -1;
                    TransActions payment = new TransActions(paymentDescription, paymentVendor, paymentAmount);
                    payment.logPayment();
                    return;
                }
                else
                {
                    System.out.println(Colors.RED + "Transaction failed try again" + Colors.RESET);
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println();
                System.out.println(Colors.RED + "Invalid input" + Colors.RESET);

                // consumes invalid input, so it doesn't get stored into deposit description
                userInput.nextLine();
            }
            catch (Exception e)
            {
                System.out.println();
                System.out.println(Colors.RED + "Something went wrong, try again." + Colors.RESET);
            }
        }
    }

    private void ledgerScreen()
    {
        String choice = "";
        while(!choice.equals("H"))
        {
            System.out.println();
            System.out.println(Colors.BACKGROUND_BLACK + Colors.BLUE + "--------Ledger-screen--------" + Colors.RESET);
            System.out.println(Colors.BACKGROUND_BLACK + Colors.BLUE + "-----------------------------" + Colors.RESET);
            System.out.println(Colors.BLUE + "A) Display All Transaction" + Colors.RESET);
            System.out.println(Colors.GREEN + "D) Display All Deposits" + Colors.RESET);
            System.out.println(Colors.RED + "P) Display All Payments" + Colors.RESET);
            System.out.println(Colors.CYAN + "R) Reports" + Colors.RESET);
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
                    System.out.println(Colors.RED + "Invalid input" + Colors.RESET);
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
        System.out.println(Colors.BACKGROUND_BLACK + "                                                          All Transactions                                                                  " + Colors.RESET);
        System.out.println("-".repeat(140));
        for (int i = 0; i < entries.size(); i++)
        {
            Entry entry = entries.get(i);
            System.out.printf(Colors.BACKGROUND_BLACK + " %-20s | %-20s | %-20s | %-20s | %-20s | $%-20.2f " + Colors.RESET + " \n", entry.getDate(), entry.getTime(), entry.getTransActionType(), entry.getDescription(), entry.getVendor(), entry.getAmount());
            System.out.println("-".repeat(140));
        }

    }

    private void displayAllDeposits()
    {
        LoadEntries loadEntries = new LoadEntries();

        // getting all deposits with loadDeposits method in my loadEntries class and storing it to the arrayList
        ArrayList<Entry> allDeposits = loadEntries.loadAllDeposits();

        System.out.println();
        System.out.println(Colors.BACKGROUND_BLACK + Colors.GREEN + "                                                          All Deposit's                                                                    " + Colors.RESET);
        System.out.println("-".repeat(140));
        for(int i = 0; i < allDeposits.size(); i++)
        {
            Entry deposits = allDeposits.get(i);
            System.out.printf(Colors.BACKGROUND_BLACK + Colors.GREEN + " %-20s | %-20s | %-20s | %-20s | %-20s | $%-20.2f " + Colors.RESET + " \n", deposits.getDate(), deposits.getTime(), deposits.getTransActionType(), deposits.getDescription(), deposits.getVendor(), deposits.getAmount());
            System.out.println("-".repeat(140));
        }

    }

    private void displayAllPayments()
    {
        LoadEntries loadEntries = new LoadEntries();

        // getting all payments with loadAllPayments method in my loadEntries class and storing it to the arrayList
        ArrayList<Entry> allPayments = loadEntries.loadAllPayments();

        System.out.println();
        System.out.println(Colors.BACKGROUND_BLACK + Colors.RED + "                                                          All payments                                                                    " + Colors.RESET);
        System.out.println("-".repeat(140));
        for(int i = 0; i < allPayments.size(); i++)
        {
            Entry payment = allPayments.get(i);
            System.out.printf( Colors.BACKGROUND_BLACK + Colors.RED + " %-20s | %-20s | %-20s | %-20s | %-20s | $%-20.2f " + Colors.RESET + "\n"  , payment.getDate(), payment.getTime(), payment.getTransActionType(), payment.getDescription(), payment.getVendor(), payment.getAmount());
            System.out.println("-".repeat(140));
        }

    }

    private void reportsScreen()
    {
        String input = "";
        int choice = 6;
        while(choice != 0)
        {
            try
            {
                System.out.println();
                System.out.println(Colors.BACKGROUND_BLACK + Colors.CYAN +"---------Reports---------" + Colors.RESET);
                System.out.println(Colors.BACKGROUND_BLACK + Colors.CYAN +"-------------------------" + Colors.RESET);
                System.out.println( Colors.CYAN + "1) Month To Date" + Colors.RESET);
                System.out.println(Colors.CYAN + "2) Previous Month" + Colors.RESET );
                System.out.println(Colors.CYAN + "3) Year To date" + Colors.RESET);
                System.out.println(Colors.CYAN + "4) Previous Year" + Colors.RESET);
                System.out.println(Colors.CYAN + "5) Search by vendor" + Colors.RESET);
                System.out.println(Colors.CYAN + "6) Custom search" + Colors.RESET);
                System.out.println(Colors.CYAN + "0) Go back to ledger page" + Colors.RESET);
                System.out.print("Enter input:");
                input = userInput.nextLine().strip().replace(" ", "");
                choice = Integer.parseInt(input);

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
                    case 6:
                        getCustomSearchInput();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println();
                        System.out.println(Colors.RED + "Invalid input" + Colors.RESET);

                }
            }
            catch (InputMismatchException e)
            {
                userInput.nextLine();
                System.out.println();
                System.out.println(Colors.RED + "Invalid input please enter a number" + Colors.RESET);
            }
            catch (Exception e)
            {
                System.out.println();
                System.out.println(Colors.RED + "Something went wrong, try again )" + Colors.RESET);
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
            System.out.printf(Colors.BACKGROUND_WHITE + Colors.BLACK + " %-20s | %-20s | %-20s | %-20s | %-20s | $%-20.2f " + Colors.RESET+ " \n", MonthToDateTransaction.getDate(), MonthToDateTransaction.getTime(), MonthToDateTransaction.getTransActionType(), MonthToDateTransaction.getDescription(), MonthToDateTransaction.getVendor(), MonthToDateTransaction.getAmount());
            System.out.println("-".repeat(140));
        }

        if(monthToDateReports.isEmpty())
        {
            System.out.println(Colors.RED +"Month to date reports not found" + Colors.RESET);
        }
    }

    public void getPreviousMonthReports()
    {
        // getting all the previous month reports from my getPreviousMonth method in my reports class
        Reports reports = new Reports();
        List<Entry> previousMonthReports = reports.getPreviousMonth();

        // printing all the reports out
        System.out.println("                                                   Previous Month Report's                                                               ");
        System.out.println("-".repeat(140));
        for(int i = 0; i < previousMonthReports.size(); i++)
        {
            Entry previousMonthTransactions = previousMonthReports.get(i);
            System.out.printf(Colors.BACKGROUND_WHITE + Colors.BLACK + " %-20s | %-20s | %-20s | %-20s | %-20s | $%-20.2f " + Colors.RESET+ " \n", previousMonthTransactions.getDate(), previousMonthTransactions.getTime(), previousMonthTransactions.getTransActionType(), previousMonthTransactions.getDescription(), previousMonthTransactions.getVendor(), previousMonthTransactions.getAmount());
            System.out.println("-".repeat(140));
        }

        if(previousMonthReports.isEmpty())
        {
            System.out.println(Colors.RED + "Previous months reports not found" + Colors.RESET);
        }
    }

    public void getYearToDateReports()
    {
        // getting all the year to day reports from my getYearToDay method in my reports class
        Reports reports = new Reports();
        List<Entry> yearToDateReports = reports.getYearToDay();

        // printing the reports out
        System.out.println("                                                   Year to day reports                                                               ");
        System.out.println("-".repeat(140));
        for (int i = 0; i < yearToDateReports.size(); i++)
        {
            Entry yearToDay = yearToDateReports.get(i);
            System.out.printf(Colors.BACKGROUND_WHITE + Colors.BLACK + " %-20s | %-20s | %-20s | %-20s | %-20s | $%-20.2f " + Colors.RESET + " \n", yearToDay.getDate(), yearToDay.getTime(), yearToDay.getTransActionType(), yearToDay.getDescription(), yearToDay.getVendor(), yearToDay.getAmount());
            System.out.println("-".repeat(140));
        }

        if(yearToDateReports.isEmpty())
        {
            System.out.println(Colors.RED + "Year to date reports not found" + Colors.RESET);
        }
    }

    public void getPreviousYear()
    {
        // getting previous year reports from getPreviousYear method in my reports class
        Reports reports = new Reports();
        List<Entry> previousYearReports = reports.getPreviousYear();

        System.out.println("                                                  Previous year reports                                                              ");
        System.out.println("-".repeat(140));
        for (int i = 0; i < previousYearReports.size(); i++)
        {
            Entry previousYear = previousYearReports.get(i);
            System.out.printf(Colors.BACKGROUND_WHITE + Colors.BLACK + " %-20s | %-20s | %-20s | %-20s | %-20s | $%-20.2f " + Colors.RESET + " \n", previousYear.getDate(), previousYear.getTime(), previousYear.getTransActionType(), previousYear.getDescription(), previousYear.getVendor(), previousYear.getAmount());
            System.out.println("-".repeat(140));
        }

        if(previousYearReports.isEmpty())
        {
            System.out.println(Colors.RED + "Previous year's report's not found" + Colors.RESET);
        }
    }

    public void searchByVendor()
    {
        // prompting user for vendors name
        Reports reports = new Reports();
        System.out.println();
        System.out.print("Enter the vendor name: ");
        String vendor = userInput.nextLine().strip();

        // searching through the reports with searchByVendor method in my reports class
        List<Entry> reportsByVendor = reports.searchByVendor(vendor);

        //printing out the reports
        System.out.println();
        System.out.println("Vendor's name: " + vendor);
        System.out.println("                                                  Report's by vendor                                                              ");
        System.out.println("-".repeat(140));
        for (int i = 0; i < reportsByVendor.size(); i++)
        {
            Entry transactionsByVendor = reportsByVendor.get(i);
            System.out.printf(Colors.BACKGROUND_WHITE + Colors.BLACK + " %-20s | %-20s | %-20s | %-20s | %-20s | $%-20.2f " + Colors.RESET+ " \n", transactionsByVendor.getDate(), transactionsByVendor.getTime(), transactionsByVendor.getTransActionType(), transactionsByVendor.getDescription(), transactionsByVendor.getVendor(), transactionsByVendor.getAmount());
            System.out.println("-".repeat(140));
        }

        if(reportsByVendor.isEmpty())
        {
            System.out.println(Colors.RED + "Vendor not found" + Colors.RESET);
        }
    }

    public void getCustomSearchInput() {
        String startDateInput = "";
        String endDateInput = "";
        String description = "";
        String vendor = "";
        String inputAmount = "";
        double amount = '\0'; // < setting amount default value to null
        try {
            System.out.println();
            System.out.println("Enter date in this format (yyyy-mm-dd)");
            System.out.print("Enter the start date: ");
            startDateInput = userInput.nextLine().strip();

            System.out.print("Enter the end date: ");
            endDateInput = userInput.nextLine().strip();

            System.out.print("Enter transaction description: ");
            description = userInput.nextLine().strip();

            System.out.print("Enter transaction vendor: ");
            vendor = userInput.nextLine().strip();

            System.out.print("Enter transaction amount: ");
            inputAmount = userInput.nextLine().strip();

        } catch (InputMismatchException e) {
            System.out.println(Colors.RED + "Invalid input" + Colors.RESET);
        } catch (Exception e) {
            System.out.println(Colors.RED + "Ops! Something went wrong" + Colors.RESET);
        }

        startDateInput = startDateInput.isEmpty() ? null : startDateInput;
        endDateInput = endDateInput.isEmpty() ? null : endDateInput;
        description = description.isEmpty() ? null : endDateInput;
        vendor = vendor.isEmpty() ? null : vendor;
        amount = inputAmount.isEmpty() ? '\0' : Double.parseDouble(inputAmount);

        Reports reports = new Reports();
        ArrayList<Entry> customSearchTransaction = reports.customSearch(startDateInput, endDateInput, description, vendor, amount);

        displayCustomSearch(customSearchTransaction);
    }

    public void displayCustomSearch(ArrayList<Entry> customSearchTransaction)
    {
        System.out.println("                                                  Custom search transaction                                                              ");
        System.out.println("-".repeat(140));
        for(int i = 0; i < customSearchTransaction.size(); i++)
        {
            Entry customTransaction = customSearchTransaction.get(i);
            System.out.printf(Colors.BACKGROUND_WHITE + Colors.BLACK + " %-20s | %-20s | %-20s | %-20s | %-20s | $%-20.2f " + Colors.RESET+ " \n", customTransaction.getDate(), customTransaction.getTime(), customTransaction.getTransActionType(), customTransaction.getDescription(), customTransaction.getVendor(), customTransaction.getAmount());
            System.out.println("-".repeat(140));
        }

        if(customSearchTransaction.isEmpty())
        {
            System.out.println("Transaction not found");
        }
    }
}
