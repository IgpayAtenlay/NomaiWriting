package util;

public class SpiralDimention {

    private double letterSize;
    private double binetNumber;
    private double length;
//    calculated
    private int binetIndex;
    private double spiralScale;

    public SpiralDimention(double letterSize, double binetNumber, double length, int binetIndex, double spiralScale) {
        this.letterSize = letterSize;
        this.binetNumber = binetNumber;
        this.length = length;
        this.binetIndex = binetIndex;
        this.spiralScale = spiralScale;
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
}
