package com.basaki.misc.type;

import java.util.Enumeration;
import java.util.Vector;

public class EnumerationFailSafeTest {

    public static void main(String args[]) {
        //Enumeration days;
        Vector<String> days = new Vector<>();

        days.add("Sunday");
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");

        Enumeration<String> daysEnumerator = days.elements();
        System.out.println(daysEnumerator.getClass());

        while (daysEnumerator.hasMoreElements()) {
            String day = daysEnumerator.nextElement();
            System.out.println(day);
            days.add("Sleepday");
        }

        daysEnumerator = days.elements();
        while (daysEnumerator.hasMoreElements()) {
            String day = daysEnumerator.nextElement();
            System.out.println(day);
        }
    }
}
