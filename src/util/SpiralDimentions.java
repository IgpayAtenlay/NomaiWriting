package util;

public class SpiralDimentions extends Location {
    private final boolean isCounterClockwise;
    private final double anchorSize;
    private final double binetNumber;
    private final int binetIndex;
    private final double spiralScale;

    public SpiralDimentions(CCoord start, CCoord end, boolean isCounterClockwise, double anchorSize, double binetNumber, int binetIndex, double spiralScale) {
        super(start, end);
        this.isCounterClockwise = isCounterClockwise;
        this.anchorSize = anchorSize;
        this.binetNumber = binetNumber;
        this.binetIndex = binetIndex;
        this.spiralScale = spiralScale;
    }

    public SpiralDimentions(CCoord start, double direction, double length, boolean isCounterClockwise, double anchorSize, double binetNumber, int binetIndex, double spiralScale) {
        super(start, direction, length);
        this.isCounterClockwise = isCounterClockwise;
        this.anchorSize = anchorSize;
        this.binetNumber = binetNumber;
        this.binetIndex = binetIndex;
        this.spiralScale = spiralScale;
    }

    public boolean isCounterClockwise() {
        return isCounterClockwise;
    }

    public double getAnchorSize() {
        return anchorSize;
    }

    public double getBinetNumber() {
        return binetNumber;
    }

    public int getBinetIndex() {
        return binetIndex;
    }

    public double getSpiralScale() {
        return spiralScale;
    }
}
