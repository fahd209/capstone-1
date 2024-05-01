package com.pluralsight.services;

import com.pluralsight.Model.Entry;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

// reads file and loads the transactions
public class LoadEntries
{
    public LoadEntries()
    {

    }

    public ArrayList<Entry> loadAllEntries()
    {
        ArrayList<Entry> entries = new ArrayList<>();

        File file = new File("LogFiles/transactions.csv");


        // reading the transactions file
        try(
                FileReader filereader = new FileReader(file);
                Scanner reader = new Scanner(filereader)
                )
        {
            // skipping the first line
            reader.nextLine();
            while (reader.hasNextLine())
            {
                DateTimeFormatter dateFormat = DateTimeFormatter.ISO_DATE;
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("kk:mm:ss");

                //splitting each line with a "|"
                String line = reader.nextLine();
                String[] columns = line.split("[|]");
                LocalDate date = LocalDate.parse(columns[0].strip(),dateFormat);
                LocalTime time = LocalTime.parse(columns[1].strip(), timeFormat);
                String transActionType = columns[2].strip();
                String description = columns[3].strip();
                String vendor = columns[4].strip();
                double amount = Double.parseDouble(columns[5].strip().replace("$", ""));

                // making an entry object with info I got from the transaction file
                Entry entry = new Entry(date, time, transActionType, description, vendor, amount);

                // adding it to the array list then returning it
                entries.add(entry);

            }
        }
        catch (IOException e)
        {
            System.out.println("File does not exist");
        }
        catch (Exception e)
        {
            System.out.println("Ops! Something went wrong");
            e.printStackTrace();
        }
        return entries;
    }

    public ArrayList<Entry> loadAllDeposits()
    {
        ArrayList<Entry> allDeposits = new ArrayList<>();

        File file = new File("LogFiles/transactions.csv");


        // reading the transactions file
        try(
                FileReader filereader = new FileReader(file);
                Scanner reader = new Scanner(filereader)
        )
        {
            // skipping the first line
            reader.nextLine();
            while (reader.hasNextLine())
            {
                DateTimeFormatter dateFormat = DateTimeFormatter.ISO_DATE;
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("kk:mm:ss");

                //splitting each line with a "|"
                String line = reader.nextLine();
                String[] columns = line.split("[|]");
                LocalDate date = LocalDate.parse(columns[0].strip(),dateFormat);
                LocalTime time = LocalTime.parse(columns[1].strip(), timeFormat);
                String transActionType = columns[2].strip();
                String description = columns[3].strip();
                String vendor = columns[4].strip();
                double amount = Double.parseDouble(columns[5].strip().replace("$", ""));

                // making an entry object with info I got from the transaction file
                Entry entry = new Entry(date, time, transActionType, description, vendor, amount);

                // adding the entry to the object if the transaction type is a deposit
                if(entry.getTransActionType().equalsIgnoreCase("Deposit"))
                {
                    allDeposits.add(entry);
                }

            }
        }
        catch (IOException e)
        {
            System.out.println("File does not exist");
        }
        catch (Exception e)
        {
            System.out.println("Ops! Something went wrong");
            e.printStackTrace();
        }


        return allDeposits;
    }

    public ArrayList<Entry> loadAllPayments()
    {
        ArrayList<Entry> allPayments = new ArrayList<>();

        File file = new File("LogFiles/transactions.csv");


        // reading the transactions file
        try(
                FileReader filereader = new FileReader(file);
                Scanner reader = new Scanner(filereader)
        )
        {
            // skipping the first line
            reader.nextLine();
            while (reader.hasNextLine())
            {
                DateTimeFormatter dateFormat = DateTimeFormatter.ISO_DATE;
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("kk:mm:ss");

                //splitting each line with a "|"
                String line = reader.nextLine();
                String[] columns = line.split("[|]");
                LocalDate date = LocalDate.parse(columns[0].strip(),dateFormat);
                LocalTime time = LocalTime.parse(columns[1].strip(), timeFormat);
                String transActionType = columns[2].strip();
                String description = columns[3].strip();
                String vendor = columns[4].strip();
                double amount = Double.parseDouble(columns[5].strip().replace("$", ""));

                // making an entry object with info I got from the transaction file
                Entry entry = new Entry(date, time, transActionType, description, vendor, amount);

                // adding the entry to the object if the transaction type is a Withdrawal
                if(entry.getTransActionType().equalsIgnoreCase("Withdrawal"))
                {
                    allPayments.add(entry);
                }

            }
        }
        catch (IOException e)
        {
            System.out.println("File does not exist");
        }
        catch (Exception e)
        {
            System.out.println("Ops! Something went wrong");
            e.printStackTrace();
        }


        return allPayments;
    }
}
