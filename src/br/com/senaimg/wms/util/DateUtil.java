/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Alefe Lucas
 */
public abstract class DateUtil {

    /**
     * Converts java.time.LocalDate to java.util.Date
     *
     * @param localDate
     * @return
     */
    public static Date toDate(LocalDate localDate) {
        if(localDate == null){
            return null;
        }
        Integer day = localDate.getDayOfMonth();
        Integer month = localDate.getMonthValue();
        Integer year = localDate.getYear();

        Date date = new Date(year - 1900, month - 1, day);
        return date;
    }

    /**
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        if(date == null){
            return null;
        }
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    /**
     *
     * @param localDate
     * @return
     */
    public static boolean isPast(LocalDate localDate) {
        Date date = toDate(localDate);
        Date now = toDate(dateToLocalDate(new Date()));

        return (date.before(now));   
    }

    /**
     *
     * @param localDate
     * @return
     */
    public static boolean isFuture(LocalDate localDate) {
        Date date = toDate(localDate);
        Date now = toDate(dateToLocalDate(new Date()));

        return (date.after(now));        
        
    }

    /**
     *
     * @param date
     * @return
     */
    public static boolean isPast(Date date) {

        Date now = new Date();
        long dif = date.getTime() - now.getTime();
        if (dif > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param date
     * @return
     */
    public static boolean isFuture(Date date) {

        Date now = new Date();
        long dif = date.getTime() - now.getTime();
        if (dif < 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param localDate
     * @param yearsAgo
     * @return
     */
    public static boolean isPastTime(LocalDate localDate, int yearsAgo) {
        Date date = toDate(localDate);
        return isPastTime(date, yearsAgo);
    }

    private static boolean isPastTime(Date date, int yearsAgo) {
        long oneYearMili = 31536000000L;
        long milisAgo = oneYearMili * yearsAgo;
        date = new Date(milisAgo + date.getTime());

        return isPast(date);

    }

}
