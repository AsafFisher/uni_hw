package com.company;

import java.util.Calendar;
public class BirthDate {
    Calendar date;
    public BirthDate(int year, int month, int day){
        Calendar.Builder builder = new Calendar.Builder();
        builder.setDate(year,month,day);
        this.date = builder.build();
    }
    public int getMonth(){
        return this.date.get(Calendar.MONTH);
    }
}
