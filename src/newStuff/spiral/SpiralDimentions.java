package newStuff.spiral;

import newStuff.util.Binet;

public class SpiralDimentions {
    private static final int minNumArcs = 5;
    private static final int maxNumArcs = 20;
    private final int numAnchorPoints;
    private final double maxSize;
    private final int letterSize;
    private double binetNumber;
    private int spiralScale;
    private int binetIndex;
    private double maxWidth;
//    constructor
    public SpiralDimentions(int numAnchorPoints, double maxSize, int letterSize) {
        this(numAnchorPoints, maxSize, letterSize, Double.MAX_VALUE);
    }

    public SpiralDimentions(int numAnchorPoints, double maxSize, int letterSize, double maxWidth) {
        this.numAnchorPoints = numAnchorPoints;
        this.maxSize = maxSize;
        this.letterSize = letterSize;
        this.maxWidth = maxWidth;
    }
//    main code
    public void createDimentions() {
//        calculations
        double bestBinetNumber = 0;
        int bestBinetIndex = 0;
//        make this as small as possible without going under
        int minNumAnchorPoints = Integer.MAX_VALUE;
//        test this binet number
        for (double binetNumber = 1.2; binetNumber < 1.7; binetNumber += 0.01) {
            int binetIndex = getBestBinetIndex(binetNumber);
            if (binetIndex > 0) {
                int numAnchorPoints = calculateNumberAnchorPoints(binetNumber, binetIndex);
//                if this gives less but enough anchor points
                if (numAnchorPoints < minNumAnchorPoints && numAnchorPoints > this.numAnchorPoints && calculateSpiralWidth(binetNumber, binetIndex, getSpiralScale(binetNumber, binetIndex)) < maxWidth) {
                    minNumAnchorPoints = numAnchorPoints;
                    bestBinetIndex = binetIndex;
                    bestBinetNumber = binetNumber;
                }
            }
        }

//        set it
        this.binetNumber = bestBinetNumber;
        this.binetIndex = bestBinetIndex;
        this.spiralScale = (int) getSpiralScale(bestBinetNumber, bestBinetIndex);
    }

    private int getBestBinetIndex(double binetNumber) {
//        get diameter

        int minNumAnchorPoints = Integer.MAX_VALUE;
        int bestBinetIndex = -1;

        for(int binetIndex = minNumArcs; binetIndex <= maxNumArcs; binetIndex++) {
            int numAnchorPoints = calculateNumberAnchorPoints(binetNumber, binetIndex);
//            if this gives less but enough anchor points
            if (numAnchorPoints < minNumAnchorPoints && numAnchorPoints > this.numAnchorPoints && calculateSpiralWidth(binetNumber, binetIndex, getSpiralScale(binetNumber, binetIndex)) < maxWidth) {
                minNumAnchorPoints = numAnchorPoints;
                bestBinetIndex = binetIndex;
            }
        }
        return bestBinetIndex;
    }

    private int calculateNumberAnchorPoints(double binetNumber, int binetIndex, double spiralScale) {
        return calculateSpiralLength(binetNumber, binetIndex, spiralScale) / this.letterSize;
    }

    private int calculateNumberAnchorPoints(double binetNumber, int binetIndex) {
        return calculateNumberAnchorPoints(binetNumber, binetIndex, getSpiralScale(binetNumber, binetIndex));
    }

    private double getSpiralScale(double binetNumber, int binetIndex) {
        return this.maxSize / calculateRelativeSpiralDiameter(binetNumber, binetIndex);
    }

    private int calculateSpiralLength(double binetNumber, int binetIndex, double spiralScale) {
        int length = 0;
        for (int i = 1; i <= binetIndex; i++) {
            length += getArcCircumference(Binet.getBinetValue(i, binetNumber) * spiralScale);
        }
        return length;
    }

    private double calculateRelativeSpiralDiameter(double binetNumber, int binetIndex) {
        double radiusLargerCircle = Binet.getBinetValue(binetIndex, binetNumber);
        double radiusSmallerCircle = Binet.getBinetValue(binetIndex - 1, binetNumber);
        return radiusLargerCircle + radiusSmallerCircle;
    }

    private static double getArcCircumference(double radius) {
        return Math.PI * radius / 2;
    }

    private double calculateSpiralWidth(double binetNumber, int binetIndex, double spiralScale) {
        double radiusLargerCircle = Binet.getBinetValue(binetIndex, binetNumber) * spiralScale;
        double radiusMediumCircle = Binet.getBinetValue(binetIndex - 1, binetNumber) * spiralScale;
        double radiusSmallerCircle = Binet.getBinetValue(binetIndex - 2, binetNumber) * spiralScale;
        return radiusLargerCircle;
    }
//    getters and setters
    public double getBinetNumber() {
        return binetNumber;
    }

    public int getSpiralScale() {
        return spiralScale;
    }

    public int getBinetIndex() {
        return binetIndex;
    }

    //    to string
    @Override
    public String toString() {
        return "SpiralDimentions{" +
                "numAnchorPoints=" + numAnchorPoints +
                ", maxSize=" + maxSize +
                ", letterSize=" + letterSize +
                ", binetNumber=" + binetNumber +
                ", spiralScale=" + spiralScale +
                ", binetIndex=" + binetIndex +
                '}';
    }
}
