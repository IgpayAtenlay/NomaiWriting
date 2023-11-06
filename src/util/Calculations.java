package util;

public class Calculations {
    public static double getBinetValue(int i, double b) {
        // if b = Phi, output is fibonacci sequence
        return (Math.pow(b, i) - Math.pow(1 - b, i)) / Math.sqrt(5);
    }

    public static double getOrderOfMagnitude(double num) {
        int zeros = 0;
        while (num >= 10) {
            zeros++;
            num /= 10;
        }

        return Math.pow(10, zeros);
    }
}
