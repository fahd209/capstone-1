package com.pluralsight.controller;

import com.pluralsight.Model.Entry;
import com.pluralsight.services.LoadEntries;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reports {

    public List<Entry> getMonthToDate()
    {
        LocalDate currentDate = LocalDate.now(); //<== getting current date
        LocalDate firstDayOfTheMonth = currentDate.withDayOfMonth(1); // <== getting first day of the current month
        LoadEntries loadEntries = new LoadEntries();
        ArrayList<Entry> allTransactions = loadEntries.loadAllEntries(); //<==getting all the entries

        /* filtering through the reports and checking if a reports date is not before the first day of the month
        and also checking its not after the current date then adding it to the list  */
        System.out.println();
        System.out.println("Report's from: " + firstDayOfTheMonth + " - " + currentDate);
        List<Entry> monthToDay = allTransactions.stream()
                .filter(report -> !report.getDate().isBefore(firstDayOfTheMonth))
                .filter(report -> !report.getDate().isAfter(currentDate))
                .toList();

        return monthToDay;
    }

    public List<Entry> getPreviousMonth()
    {
        LoadEntries loadEntries = new LoadEntries();
        ArrayList<Entry> allTransactions = loadEntries.loadAllEntries(); //<== getting all the entries
        LocalDate currentDate = LocalDate.now();

        // getting first day of the previous month
        LocalDate firstDayOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1);

        // getting the last day of the previous month
        LocalDate lastDayOfPreviousMonth = firstDayOfPreviousMonth.plusMonths(1).minusDays(1);

        /*
        * getting previous month's transAction by streaming and filtering through the report arrayList and checking if
        * the reports is not before the first day of the previous month and is not after the last day of the previous month
        * */

        System.out.println();
        System.out.println("Report's from: " + firstDayOfPreviousMonth + " - " + lastDayOfPreviousMonth);
        List<Entry> previousMonthReports = allTransactions.stream()
                .filter(prevMonthReports -> !prevMonthReports.getDate().isBefore(firstDayOfPreviousMonth))
                .filter(prevMonthReports -> !prevMonthReports.getDate().isAfter(lastDayOfPreviousMonth))
                .toList();

        return previousMonthReports;
    }

    public List<Entry> getYearToDay ()
    {
        LoadEntries loadEntries = new LoadEntries();
        ArrayList<Entry> allTransactions = loadEntries.loadAllEntries(); // <== getting all the transaction
        LocalDate currentDate = LocalDate.now(); //<== getting current date
        LocalDate firstDayOfTheYear = currentDate.withDayOfYear(1);  // <== getting the first day of the current year

        System.out.println();
        System.out.println("Report's from: " + firstDayOfTheYear + " - " + currentDate);
        List<Entry> yearToDate = allTransactions.stream()
                .filter(reports -> !reports.getDate().isBefore(firstDayOfTheYear))
                .filter(reports -> !reports.getDate().isAfter(currentDate))
                .toList();

        return yearToDate;
    }

    public List<Entry> getPreviousYear()
    {
        LoadEntries loadEntries = new LoadEntries();
        ArrayList<Entry> allTransactions = loadEntries.loadAllEntries();

        LocalDate currentDate = LocalDate.now();

        //getting first day of previous year and last day of the previous year
        LocalDate firstDayOfPreviousYear = LocalDate.of(currentDate.getYear() - 1, 1, 1);
        LocalDate lastDayOfPreviousYear = LocalDate.of(currentDate.getYear() - 1, 12, 31);

        System.out.println();
        System.out.println("Reports from: " + firstDayOfPreviousYear + " - " + lastDayOfPreviousYear);

        List<Entry> previousYearTransactions = allTransactions.stream()
                .filter(reports -> !reports.getDate().isBefore(firstDayOfPreviousYear))
                .filter(reports -> !reports.getDate().isAfter(lastDayOfPreviousYear))
                .toList();

        return previousYearTransactions;
    }

    public List<Entry> searchByVendor(String vendor)
    {
        LoadEntries loadEntries = new LoadEntries();
        ArrayList<Entry> allTransaction = loadEntries.loadAllEntries();


        List<Entry> transactionsByVendor = allTransaction.stream()
                .filter(reports -> reports.getVendor().equals(vendor))
                .toList();

        return transactionsByVendor;

    }
}
