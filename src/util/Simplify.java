package util;

public class Simplify {

    public static double degree90(double direction) {
        while (direction < 0) {
            direction += 90;
        }
        direction = direction % 90;
        return direction;
    }

    public static double degree360(double direction) {
        while (direction < 0) {
            direction += 360;
        }
        direction = direction % 360;
        return direction;
    }
}
