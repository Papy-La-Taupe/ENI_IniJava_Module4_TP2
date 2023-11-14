package basicPayroll;

import java.util.Scanner;

public class basicPayroll {

    // Something always fixed: charges. since we pay all of them, it's a sum.
    public static final double CHARGES = 26.91;

    // Second fixed thing: extra hours rate
    public static final int BASIC_MAJORATION = 50;
    public static final int EXTRA_MAJORATION = 60;

    // First function to know: bonuses
    public static int bonus;
    public static int children;

    public static int calculateBonus(int children) {
        switch (children) {
            case 0:
                return 0;
            case 1:
                return 20;
            case 2:
                return 50;
            default:
                return 70 + (20 * (children - 2));
        }
    }

    // now, some basic variables
    public static String name;
    public static String status;
    public static int timeDone;
    public static double extraTimeBasedHourlyRate;
    public static int statusBasedHourlyRate = 15;

    // then, taking info and calculating final rate functions
    public static void basicInfos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please state your first and last name.");
        name = scanner.nextLine();
        System.out.println("Please state your professional status");
        status = scanner.nextLine();
        System.out.println("Please state how many children do you have");
        children = scanner.nextInt();
        System.out.println("Please state how many hours of work you've done this month");
        timeDone = scanner.nextInt();
        scanner.close();
    }

    public static double finalRateCalculation(double time) {
        if (time < 169) {
            return 1.0;
        } else if (time >= 169 && time < 180) {
            return (((time - 169) * 1.5) + 168) / time;
        } else if (time >= 180) {
            return (((time - 179) * 1.6) + ((178 - 169) * 1.5) + 168) / time;
        } else {
            return 0.0; // Handle other cases or return a default value
        }
    }

    // finally, and even though we absolutely can't do it since we don't have the necessary information, the pay.
    // we invented a 15€ hourlyRate for the occasion.
    public static double finalNetPay(double hourlyRate, int hours, int bonus, int basePay) {
        final double pay = (basePay * hours * hourlyRate) - CHARGES + bonus;
        System.out.println(pay);
        return pay;
    }

    public static void main(String[] args) {
        System.out.println("Pay Roll Generator");
        System.out.println("");
        basicInfos();
        extraTimeBasedHourlyRate = finalRateCalculation(timeDone);
        int calculatedBonus = calculateBonus(children);
        double netPay = finalNetPay(extraTimeBasedHourlyRate, timeDone, calculatedBonus, statusBasedHourlyRate);
        System.out.println("Final Net Pay: " + netPay);
    }
}