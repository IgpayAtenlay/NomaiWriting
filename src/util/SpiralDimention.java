package util;

public class SpiralDimention {

    private double letterSize;
    private double binetNumber;
    private double length;
    private boolean counterClockwise;
    // calculated
    private int binetIndex;
    private double spiralScale;

    public SpiralDimention(double letterSize, double binetNumber, double length, int binetIndex, double spiralScale, boolean counterClockwise) {
        this.letterSize = letterSize;
        this.binetNumber = binetNumber;
        this.length = length;
        this.binetIndex = binetIndex;
        this.spiralScale = spiralScale;
        this.counterClockwise = counterClockwise;
    }

    public SpiralDimention(double letterSize, double binetNumber, double length, int binetIndex, double spiralScale) {
        this(letterSize, binetNumber, length, binetIndex, spiralScale, true);
    }

    public double getLetterSize() {
        return letterSize;
    }

    public double getBinetNumber() {
        return binetNumber;
    }

    public double getLength() {
        return length;
    }

    public int getBinetIndex() {
        return binetIndex;
    }

    public double getSpiralScale() {
        return spiralScale;
    }

    public boolean isCounterClockwise() {
        return counterClockwise;
    }
}
