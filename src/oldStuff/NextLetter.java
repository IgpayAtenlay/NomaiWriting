package oldStuff;

public class NextLetter {
    private int binetIndex = 3;
    private double binetScale = 1.3 /*(1 + Math.sqrt(5)) / 2*/;
    private int[] center;
    private int currentAngle;
    private int[] currentCenter;
    private int spiralScale;
    private int letterScale;
    private boolean isCounterClockwise;

//    constructor
    public NextLetter(int[] center, int currentAngle, int spiralScale, int letterScale, boolean isCounterClockwise) {
        this.center = center;
        this.currentAngle = currentAngle;
        this.spiralScale = spiralScale;
        this.letterScale = letterScale;
        int x = (int) (Math.cos(currentAngle * Math.PI / 180) * (getBinetNumber(binetIndex) * spiralScale));
        int y = (int) (Math.sin(currentAngle * Math.PI / 180) * (getBinetNumber(binetIndex) * spiralScale) * -1);
        this.currentCenter = new int[] {center[0] - x, center[1] - y};
        this.isCounterClockwise = isCounterClockwise;
    }
//    next letter stuff
    public int[] stepForward() {
        int degreeChange = (int) ((180 * letterScale) / (getBinetNumber(binetIndex) * spiralScale * Math.PI));
        int oldAngle = currentAngle;
        if (isCounterClockwise) {
            currentAngle += degreeChange;
        } else {
            currentAngle -= degreeChange;
        }
        if (currentAngle >= 360) {
            currentAngle -= 360;
        } else if (currentAngle < 0) {
            currentAngle += 360;
        }

        if (oldAngle / 90 != currentAngle / 90) {
            nextArc();
        }
        return getCoords();
    }
//    moving onto the next arc
    private void nextArc() {
        binetIndex++;
        newCenter();
    }
    public static double getBinetNumber(int i, double b) {
        return (Math.pow(b, i) - Math.pow(1 - b, i)) / Math.sqrt(5);
    }
    public double  getBinetNumber(int i) {
        return getBinetNumber(i, binetScale);
    }
    private void newCenter() {
        if (isCounterClockwise) {
            if (currentAngle < 90) {
                currentCenter[0] -= (getBinetNumber(binetIndex) - getBinetNumber(binetIndex - 1)) * spiralScale;
            } else if (currentAngle < 180) {
                currentCenter[1] += (getBinetNumber(binetIndex) - getBinetNumber(binetIndex - 1)) * spiralScale;
            } else if (currentAngle < 270) {
                currentCenter[0] += (getBinetNumber(binetIndex) - getBinetNumber(binetIndex - 1)) * spiralScale;
            } else {
                currentCenter[1] -= (getBinetNumber(binetIndex) - getBinetNumber(binetIndex - 1)) * spiralScale;
            }
        } else {
            if (currentAngle < 90) {
                currentCenter[1] += (getBinetNumber(binetIndex) - getBinetNumber(binetIndex - 1)) * spiralScale;
            } else if (currentAngle < 180) {
                currentCenter[0] += (getBinetNumber(binetIndex) - getBinetNumber(binetIndex - 1)) * spiralScale;
            } else if (currentAngle < 270) {
                currentCenter[1] -= (getBinetNumber(binetIndex) - getBinetNumber(binetIndex - 1)) * spiralScale;
            } else {
                currentCenter[0] -= (getBinetNumber(binetIndex) - getBinetNumber(binetIndex - 1)) * spiralScale;
            }
        }
    }
//    get the coords
    public int[] getCoords() {
        int x = (int) (Math.cos(currentAngle * Math.PI / 180) * (getBinetNumber(binetIndex) * spiralScale));
        int y = (int) (Math.sin(currentAngle * Math.PI / 180) * (getBinetNumber(binetIndex) * spiralScale) * -1);
        x += currentCenter[0];
        y += currentCenter[1];
        return new int[] {x, y};
    }

    public int getDirection() {
        int direction = currentAngle + 90;
        if (direction < 0) {
            direction += 360;
        }
        return direction;
    }
}
