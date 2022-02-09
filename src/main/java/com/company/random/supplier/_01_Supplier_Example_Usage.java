package com.company.random.supplier;

/* In Java 8, Supplier is a functional interface;
   It takes no arguments and returns a result. */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class _01_Supplier_Example_Usage {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {

        Supplier<LocalDateTime> s = () -> LocalDateTime.now();
        LocalDateTime time = s.get();
        System.out.println(time);

        Supplier<String> s1 = () -> dtf.format(LocalDateTime.now());
        String time2 = s1.get();
        System.out.println(time2);

    }

}