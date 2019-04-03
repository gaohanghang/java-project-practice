package com.gaohanghang.springbootgephi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Test {
    public static void main(String[] args) throws ParseException {
        String date = "Thu Aug 27 18:05:49 CST 2015";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        String formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(simpleDateFormat.parse(date));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(formatDate, formatter);
        System.out.println(localDateTime);
    }
}
