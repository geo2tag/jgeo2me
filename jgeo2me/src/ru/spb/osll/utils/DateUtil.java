/**
 * 
 */
package ru.spb.osll.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Mark Zaslavskiy
 *
 */
public class DateUtil {


        private static int decPow(int power){
                int result = 1;
                for (int i=1; i<=power; i++){
                        result *= 10;
                }
                return result;
        }
        
        private static String identNumberWithZeros(int number, int digitsNumber){
                String zeros = "";
                
                for (int i=digitsNumber-1; i>0; i--){
                        if (decPow(i) > number)
                                zeros = zeros + "0";
                }
                
                return zeros+Integer.toString(number);
        }

        
        public static String getCurrentTime(){
                Date date = new Date();
                return getTime(date);
        }
        
        public static String getTime(Date date){
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                
                
                
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH)+1;
                int year = cal.get(Calendar.YEAR);
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);
                int millisecond = cal.get(Calendar.MILLISECOND);
                
                System.out.println("Month " + month);
//      "dd MM yyyy HH:mm:ss.SSS";              
                
                String currentTime = 
                                identNumberWithZeros(day,2) + " " +
                                identNumberWithZeros(month,2) + " " +
                                Integer.toString(year) + " " +
                                identNumberWithZeros(hour,2) + ":" + 
                                identNumberWithZeros(minute,2) + ":" + 
                                identNumberWithZeros(second,2) + "." + 
                                identNumberWithZeros(millisecond,3);
                
                
                return currentTime;
        }

        public static String getPastTime( int yearAge) {
                // TODO Auto-generated method stub
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                
                int year = cal.get(Calendar.YEAR);
                cal.set(Calendar.YEAR, year - yearAge);
                
                return getTime(cal.getTime());
        }
	
}
