package util;

public class Binet {
    public static double getBinetValue(int i, double b) {
//        if b = Phi, output is fibonacci sequence
        return (Math.pow(b, i) - Math.pow(1 - b, i)) / Math.sqrt(5);
    }
}
