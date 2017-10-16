package com.basaki.misc;

import java.util.Scanner;

/**
 * {@code CompoundInterest} calculates compound interest.
 * <p>
 * Answers to stackoverflow questions:
 * <ol>
 * <li>https://stackoverflow.com/questions/46751022/how-to-correlate-an-assigned-string-value-to-an-integer-value/46751305#46751305</li>
 * </ol>
 *
 * @author Indra Basak
 * @since 10/14/17
 */
@SuppressWarnings({"squid:CommentedOutCodeLine", "squid:S106", "squid:S1854", "squid:S1481"})
public class CompoundInterest {

    public static void main(String[] args) {
        //        System.out.println(Math.pow(2, 3));
        //        System.out.println(Math.pow(1 + 0.05, 1 * 3));
        //        double m = 5;
        //        double n = 100;
        //        double xxx = (1.0 + (double) m / n);
        //        System.out.println("xxx: " + xxx);
        //        double yyy = (1.0 + m / n);
        //        System.out.println("yyy: " + yyy);
        //        double x = Math.pow(1 + (double) 5 / 100 * 1, 1 * 3);
        //        System.out.println(x);
        //        int year = 3;
        //        double y = Math.pow(1 + (double) m / 100 * n, n * year);
        //        System.out.println("y: " + y);
        //        double a = 100.0 * x;
        //        System.out.println(a);

        Scanner cool = new Scanner(System.in);
        double saving;
        double rate;
        int principal;
        int years;
        int choice = 1;

        System.out.println("Please enter you principal investment:");
    /*Print statment prompts user to enter their principal investment*/
        principal = cool.nextInt();

        System.out.println("Would you like to have a regular investment plan?");
    /* Print out statement asks user if they would like to participate in a regular investment plan*/
        String question = cool.next();

        System.out.println(
                "What type of investment plan would you prefer (Annual, Quarterly, or Monthly)?");
        String quest = cool.next();

        /*
        Annual = 1, Monthly = 12, and Quarterly = 4.
         */
        if ("Annual".equalsIgnoreCase(quest)) {
            choice = 1;
        } else if ("Quarterly".equalsIgnoreCase(quest)) {
            choice = 4;
        } else if ("Monthly".equalsIgnoreCase(quest)) {
            choice = 12;
        }

        //        while (quest.equalsIgnoreCase(("Annual"))) {
        //            String Annual = "1";
        //            Annual.equals(choice);
        //
        //        }

        System.out.println(
                "Please enter the number of years that you wish to invest for:");
    /* Print statement prompts user to enter the number of years that they wish to invest for*/
        years = cool.nextInt();

        System.out.println("Please enter the return rate per year:");
    /* Print statement prompts user to enter the return rate per year*/
        rate = cool.nextDouble();


        //saving = principal * ((1 + (rate / choice)) * Math.pow(choice, years));
        //saving = principal *  Math.pow(1 + (double)(rate / (100 * choice)), choice * years);
        saving = principal * Math.pow(1 + (rate / (100 * choice)),
                (double) choice * years);
        System.out.printf("%.2f", saving);
    }
}
