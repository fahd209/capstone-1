package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Reports {

    public List<Entry> getMonthToDate()
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfTheMonth = currentDate.withDayOfMonth(1);
        LoadEntries loadEntries = new LoadEntries();
        ArrayList<Entry> reports = loadEntries.loadAllEntries(); //<==getting all the entries

        /* filtering through the reports and checking if a reports date is not before the first day of the month
        and also checking its not after the current date then adding it to the list  */
        System.out.println();
        System.out.println("Report's from: " + firstDayOfTheMonth + " - " + currentDate);
        List<Entry> monthToDay = reports.stream()
                .filter(report -> !report.getDate().isBefore(firstDayOfTheMonth))
                .filter(report -> !report.getDate().isAfter(currentDate))
                .toList();

        return monthToDay;
    }

    public List<Entry> getPreviousMonth()
    {
        LoadEntries loadEntries = new LoadEntries();
        ArrayList<Entry> report = loadEntries.loadAllEntries(); //<== getting all the entries
        LocalDate currentDate = LocalDate.now();

        /* getting the first day of the previous month by substring one month from current month and subtracting the current days
        and also adding one day so that it doesn't go to month that before the previous*/
        LocalDate firstDayOfPreviousMonth = currentDate.minusMonths(1).minusDays(currentDate.getDayOfMonth()).plusDays(1);

        /* getting the day of the previous month by adding one month to the firstDayOfThePreviousMonth and subtracting one day so that it d
        * doesn't go to the current month
        * */
        LocalDate lastDayOfPreviousMonth = firstDayOfPreviousMonth.plusMonths(1).minusDays(1);
        System.out.println(firstDayOfPreviousMonth);
        System.out.println(lastDayOfPreviousMonth);


        /*
        * getting previous month's transAction by streaming and filtering through the report arrayList and checking if
        * the reports is not before the first day of the previous month and is not after the last day of the previous month
        * */

        System.out.println();
        System.out.println("Report's from: " + firstDayOfPreviousMonth + " - " + lastDayOfPreviousMonth);
        List<Entry> previousMonthReports = report.stream()
                .filter(prevMonthReports -> !prevMonthReports.getDate().isBefore(firstDayOfPreviousMonth))
                .filter(prevMonthReports -> !prevMonthReports.getDate().isAfter(lastDayOfPreviousMonth))
                .toList();

        return previousMonthReports;
    }
}
