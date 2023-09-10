package newStuff.util;

public class SpiralDimentions extends Location {
    private final boolean isCounterClockwise;
    private final double anchorSize;

    public SpiralDimentions(CCoord start, CCoord end, boolean isCounterClockwise, double anchorSize) {
        super(start, end);
        this.isCounterClockwise = isCounterClockwise;
        this.anchorSize = anchorSize;
    }

    public SpiralDimentions(CCoord start, CCoord end, boolean isCounterClockwise, int numOfAnchorPoints) {
        super(start, end);
        this.isCounterClockwise = isCounterClockwise;
        this.anchorSize = super.getLength() / numOfAnchorPoints / 2 * 5;
    }

    public SpiralDimentions(CCoord start, double direction, double length, boolean isCounterClockwise, double anchorSize) {
        super(start, direction, length);
        this.isCounterClockwise = isCounterClockwise;
        this.anchorSize = anchorSize;
    }

    public SpiralDimentions(CCoord start, double direction, double length, boolean isCounterClockwise, int numOfAnchorPoints) {
        super(start, direction, length);
        this.isCounterClockwise = isCounterClockwise;
        this.anchorSize = length / numOfAnchorPoints / 2 * 5;
    }
}
