# capstone-1
### Project overview
I created a account ledger project that will allow the user to make a deposit, and make a transaction. The user will also be able to display all deposits, transaction, and search through the reports with filters. 

This is how i started planning out my project

![diagram1](images/diagram1.png)

## home screen
I started off by making my home screen and prompting the user to make a deposit, make a payment, display Ledger screen, or Exit to exit the application

* Make deposit

When the user chooses to make a deposit the program will prompt the user to enter the description of the deposit, the vendor, and the deposit amount. When the user enter's all that information it log the deposit with the date and time plus the info to a csv file.

Code used to add the deposit functionalty:

```java
public void logDeposit()
    {
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
            writer.printf(" %s | %s | %s | %s | $%.2f \n", date.format(DATE_FORMAT), time.format(TIME_FORMAT), depositDescription, vendor, amount);
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
```

* Make a payment

When the user chooses to make a payment. The user will be prompted for payment descript, vendor, and amount. Once the user provides the information need to make the payment it will get saved in the transaction file.

