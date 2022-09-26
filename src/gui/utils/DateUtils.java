package gui.utils;

import javax.swing.*;
/*
Utility class to provide some extra validation and functionality for
dates.
 */
public class DateUtils {
    /*
    Formats dates to always be of the form MM-DD--YYYY
     */
    public static String getDate(JTextField dateField){
        String[] dateData = dateField.getText().split("[-/.]");
        if(dateData[0].length() == 1)dateData[0] = "0"+dateData[0];
        if(dateData.length >= 2 && dateData[1].length() == 1)dateData[1] = "0"+dateData[1];
        if(dateData.length >= 3 && dateData[2].length()==2)dateData[2] = "20"+dateData[2];
        String result = "";
        int count = 0;
        for(String s:dateData){
            result+= s + (count <2 ? "-":"");
            count++;
        }
        return result;
    }
    /*
    Validates whether an entered date is correct or not
     */
    public static boolean invalidDate(String date){
        if(date.isBlank())return true;
        String[] dateArray = date.split("[-/.]");
        if(dateArray.length == 3){
            int month,day,year;
            month = Integer.parseInt(dateArray[0]);
            day = Integer.parseInt(dateArray[1]);
            if((dateArray[2].length() == 2 || dateArray[2].length() == 4) && month >=1 && month <=12){
                if(dateArray[2].length() == 2)dateArray[2] = "20"+dateArray[2];
                year = Integer.parseInt(dateArray[2]);
                if(isValidDayForMonth(day,month,year)){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean isValidDayForMonth(int day,int month,int year){
        switch(month){
            case 1,3,5,7,8,10,12:
                return (day >=1 && day <=31);
            case 2:
                if(year % 4 == 0 ){
                    if( year%100 == 0 && year%400 != 0){
                        return (day >=1 && day <=28);
                    }
                    return (day >=1 && day <=29);
                }
                else{
                    return (day >=1 && day <=28);
                }
            case 4,6,9,11:
                return (day >=1 && day <=30);
            default:
                return false;
        }
    }
}
