package thisPackage;

public class NextLetter {
    private int[] fibonacci = new int[] {1, 2};
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
        int x = (int) (Math.cos(currentAngle * Math.PI / 180) * (fibonacci[1] * spiralScale));
        int y = (int) (Math.sin(currentAngle * Math.PI / 180) * (fibonacci[1] * spiralScale) * -1);
        this.currentCenter = new int[] {center[0] - x, center[1] - y};
        this.isCounterClockwise = isCounterClockwise;
    }
//    next letter stuff
    public int[] stepForward() {
        int degreeChange = (int) ((180 * letterScale) / (fibonacci[1] * spiralScale * Math.PI));
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
        nextFibonacci();
        newCenter();
    }
    private void nextFibonacci() {
        int temp = fibonacci[1];
        fibonacci[1] += fibonacci[0];
        fibonacci[0] = temp;
    }
    private void newCenter() {
        if (isCounterClockwise) {
            if (currentAngle < 90) {
                currentCenter[0] -= (fibonacci[1] - fibonacci[0]) * spiralScale;
            } else if (currentAngle < 180) {
                currentCenter[1] += (fibonacci[1] - fibonacci[0]) * spiralScale;
            } else if (currentAngle < 270) {
                currentCenter[0] += (fibonacci[1] - fibonacci[0]) * spiralScale;
            } else {
                currentCenter[1] -= (fibonacci[1] - fibonacci[0]) * spiralScale;
            }
        } else {
            if (currentAngle < 90) {
                currentCenter[1] += (fibonacci[1] - fibonacci[0]) * spiralScale;
            } else if (currentAngle < 180) {
                currentCenter[0] += (fibonacci[1] - fibonacci[0]) * spiralScale;
            } else if (currentAngle < 270) {
                currentCenter[1] -= (fibonacci[1] - fibonacci[0]) * spiralScale;
            } else {
                currentCenter[0] -= (fibonacci[1] - fibonacci[0]) * spiralScale;
            }
        }
    }
//    get the coords
    public int[] getCoords() {
        int x = (int) (Math.cos(currentAngle * Math.PI / 180) * (fibonacci[1] * spiralScale));
        int y = (int) (Math.sin(currentAngle * Math.PI / 180) * (fibonacci[1] * spiralScale) * -1);
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
