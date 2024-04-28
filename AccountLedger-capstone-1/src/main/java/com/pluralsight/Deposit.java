package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Deposit {
    final static String LOG_DIRECTORY = "Deposit";
    final static String FILE_NAME = "depositsLog.csv";
    static String  depositDescription;
    static String vendor;
    static double depositAmount;

    public Deposit(String depositDescription, String vendor, double depositAmount)
    {
        Deposit.depositDescription = depositDescription;
        Deposit.vendor = vendor;
        Deposit.depositAmount = depositAmount;
    }


    public void logDeposit()
    {
        System.out.println();

        File directory = new File(LOG_DIRECTORY);

        if(!directory.exists())
        {
            directory.mkdir();
        }

        File depositLog = new File(LOG_DIRECTORY + "/"  + FILE_NAME);

        try(
                FileWriter fileWriter = new FileWriter(depositLog, true);
                PrintWriter writer = new PrintWriter(fileWriter);
        )
        {
            writer.printf(" %s | %s | $%s \n",depositDescription, vendor, depositAmount);
            System.out.printf("$%s deposit from %s ", depositAmount, vendor);
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
