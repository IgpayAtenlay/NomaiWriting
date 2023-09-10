package spiral;

import util.Binet;
import util.CCoord;

public class SpiralDimentions {
    private static final int maxNumArcs = 20;
    private static final double anchorSizeMax = 20;
    private static final double idealBinetNumber = (1 + Math.sqrt(5)) / 2;
    private final int numAnchorPoints;
    private final double maxSize;
    private final double maxWidth;
    private double anchorSize;
    private double binetNumber;
    private double spiralScale;
    private int binetIndex;
    private double diameter;
//    constructor
    public SpiralDimentions(int numAnchorPoints, double maxSize, double maxWidth) {
        this.numAnchorPoints = numAnchorPoints;
        this.maxSize = maxSize;
        this.maxWidth = maxWidth;
    }
//    main code
    public static util.SpiralDimentions getDimentions(CCoord start, double direction, boolean isCounterClockwise, int numAnchorPoints, double maxSize, double maxWidth) {
        SpiralDimentions spiral = new SpiralDimentions(numAnchorPoints, maxSize, maxWidth);
        spiral.createDimentions();
        return new util.SpiralDimentions(start, direction, spiral.diameter, isCounterClockwise, spiral.anchorSize, spiral.binetNumber, spiral.binetIndex, spiral.spiralScale);
    }
    public void createDimentions() {
        double bestBinetNumber = 0;
        int bestBinetIndex = 0;
        double bestSpiralScale = 0;
        double bestAnchorSize = 0;
        double bestDiameter = 0;
//        test binet number
        for (double binetNumber = 1.1; binetNumber < 1.70; binetNumber += 0.01) {
//            test binet index
            for (int binetIndex = (binetNumber > 1.3 ? 5 : (binetNumber > 1.2 ? 6 : (binetNumber > 1.15 ? 7 : 8))); binetIndex < maxNumArcs; binetIndex++) {
//                test spiral scale
                for (double spiralScale = 0; spiralScale < calculateMaxSpiralSize(binetNumber, binetIndex, maxSize) ; spiralScale++) {
                    double diameter = calculateSpiralDiameter(binetNumber, binetIndex, spiralScale); /*calculate*/
                    double width = calculateSpiralWidth(binetNumber, binetIndex, spiralScale); /*calculate*/
                    double anchorSize = calculateAnchorSize(binetNumber, binetIndex, spiralScale); /*calculate*/

                    if (diameter < maxSize && width < maxWidth && anchorSize < anchorSizeMax) {
                        double ideal = anchorSize;
                        if (anchorSize > bestAnchorSize - 1 && diameter > bestDiameter - 10) {
                            bestBinetNumber = binetNumber;
                            bestBinetIndex = binetIndex;
                            bestSpiralScale = spiralScale;
                            bestAnchorSize = Math.max(bestAnchorSize, anchorSize);
                            bestDiameter = Math.max(bestDiameter, diameter);
                        }
                    }
                }
            }
        }

//        set it
        this.binetNumber = bestBinetNumber;
        this.binetIndex = bestBinetIndex;
        this.spiralScale = bestSpiralScale;
        this.anchorSize = calculateAnchorSize(binetNumber, binetIndex, spiralScale);
        this.diameter = calculateSpiralDiameter(binetNumber, binetIndex, spiralScale);
        System.out.println(this);
    }
//    calculations
    private double calculateAnchorSize(double binetNumber, int binetIndex, double spiralScale) {
        return calculateSpiralLength(binetNumber, binetIndex, spiralScale) / numAnchorPoints;
    }
    private double calculateSpiralLength(double binetNumber, int binetIndex, double spiralScale) {
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
    private double calculateSpiralDiameter(double binetNumber, int binetIndex, double spiralScale) {
        return calculateRelativeSpiralDiameter(binetNumber, binetIndex) * spiralScale;
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
    private double calculateMaxSpiralSize(double binetNumber, int binetIndex, double length) {
        return length / calculateRelativeSpiralDiameter(binetNumber, binetIndex);
    }
    //    to string
    @Override
    public String toString() {
        return "SpiralDimentions{" +
                "numAnchorPoints=" + numAnchorPoints +
                ", maxSize=" + maxSize +
                ", letterSize=" + anchorSize +
                ", binetNumber=" + binetNumber +
                ", spiralScale=" + spiralScale +
                ", binetIndex=" + binetIndex +
                '}';
    }
}
