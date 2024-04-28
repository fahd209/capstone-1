package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TransActions {
    final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE;
    final static DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("kk:mm:ss");
    final static String LOG_DIRECTORY = "logFiles";
    final static String FILE_NAME = "transactions.csv";
    static String  Description;
    static String transActionType = "";
    static String vendor;
    static double amount;

    public TransActions()
    {

    }

    public TransActions(String Description, String vendor, double amount)
    {
        TransActions.Description = Description;
        TransActions.vendor = vendor;
        TransActions.amount = amount;
    }


    public void logDeposit()
    {
        transActionType = "Deposit";

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        // checking if file exists if it doesn't then we create it
        File directory = new File(LOG_DIRECTORY);
        if (!directory.exists())
        {
            directory.mkdir();
        }

        File transactionLogs = new File(LOG_DIRECTORY + "\\"  + FILE_NAME);

        try(
                FileWriter fileWriter = new FileWriter(transactionLogs, true);
                PrintWriter writer = new PrintWriter(fileWriter);
        )
        {
            // logging the deposit info to the transaction file with date and time
            writer.printf(" %s | %s | %s | %s | %s | $%.2f \n", date.format(DATE_FORMAT), time.format(TIME_FORMAT),transActionType , Description, vendor, amount);

            System.out.println();
            System.out.printf("$%.2f deposit from %s ", amount, vendor);
            System.out.println();
        }
        catch (IOException e)
        {
            System.out.println("File does not exist");
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public void logPayment()
    {
        transActionType = "withdrawal";

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        File transactionLogs = new File(LOG_DIRECTORY + "\\"  + FILE_NAME);

        try(
                FileWriter fileWriter = new FileWriter(transactionLogs, true);
                PrintWriter writer = new PrintWriter(fileWriter);
        )
        {
            // logging the deposit info to the transaction file with date and time
            writer.printf(" %s | %s | %s | %s | %s | $%.2f \n", date.format(DATE_FORMAT), time.format(TIME_FORMAT),transActionType , Description, vendor, amount);

            System.out.println();
            System.out.printf("$%.2f payment to %s ", amount, vendor);
            System.out.println();
        }
        catch (IOException e)
        {
            System.out.println("File does not exist");
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
