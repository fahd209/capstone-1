package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Reports {

    public ArrayList<Entry> getMonthToDate()
    {
        DateTimeFormatter format = DateTimeFormatter.ISO_DATE;
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfTheMonth = currentDate.withDayOfMonth(1);
        LoadEntries loadEntries = new LoadEntries();
        ArrayList<Entry> reports = loadEntries.loadAllEntries();
        ArrayList<Entry> monthToDay = new ArrayList<>();

        for(int i = 0; i < reports.size(); i++)
        {
            Entry report = reports.get(i);
            if(!report.getDate().isAfter(currentDate) && !report.getDate().isBefore(firstDayOfTheMonth))
            {
                monthToDay = reports;
            }
        }
        return monthToDay;
    }
}
