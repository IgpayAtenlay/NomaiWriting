package newStuff.util;

public class Binet {
    public static double getBinetValue(int i, double b) {
        return (Math.pow(b, i) - Math.pow(1 - b, i)) / Math.sqrt(5);
    }
}
