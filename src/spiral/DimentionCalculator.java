package spiral;

import util.CCoord;
import util.Calculations;
import util.SpiralLocation;

public class DimentionCalculator {
    private static final double minBinetNum = 1.4;
    private static final double maxBinetNum = 1.8;
    private static final int minNumArcs = 5;
    private static final int maxNumArcs = 7;
    private final double anchorSizeMax;
    private static final double idealBinetNumber = (1 + Math.sqrt(5)) / 2;
    private final int numAnchorPoints;
    private double anchorSize;
    private double binetNumber;
    private double spiralScale;
    private int binetIndex;
    private double diameter;

    // constructor
    public DimentionCalculator(int numAnchorPoints, double anchorSizeMax) {
        this.numAnchorPoints = numAnchorPoints;
        this.anchorSizeMax = anchorSizeMax;
    }

    // main code
    public static SpiralLocation getLocation(CCoord start, double direction, boolean isCounterClockwise, int numAnchorPoints, double diameter, double anchorSizeMax) {
        DimentionCalculator spiral = new DimentionCalculator(numAnchorPoints, anchorSizeMax);
        spiral.createDimentions(diameter);
        return new SpiralLocation(start, direction, spiral.diameter, isCounterClockwise, spiral.anchorSize, spiral.binetNumber, spiral.binetIndex, spiral.spiralScale);
    }

    public void createDimentions(double maxDiameter, double maxRadius) {
        double bestBinetNumber = 0;
        int bestBinetIndex = 0;
        double bestSpiralScale = 0;
        double bestAnchorSize = 0;
        double bestDiameter = 0;
        // test binet number
        for (double binetNumber = minBinetNum; binetNumber < maxBinetNum; binetNumber += 0.01) {
            // test binet index
            for (int binetIndex = minNumArcs; binetIndex < maxNumArcs; binetIndex++) {
                // test spiral scale
                for (double spiralScale = 0; spiralScale < calculateMaxSpiralSize(binetNumber, binetIndex, maxDiameter); spiralScale++) {
                    double diameter = calculateSpiralDiameter(binetNumber, binetIndex, spiralScale); /*calculate*/
                    double radius = calculateSpiralRadius(binetNumber, binetIndex, spiralScale); /*calculate*/
                    double anchorSize = calculateAnchorSize(binetNumber, binetIndex, spiralScale); /*calculate*/

                    if (diameter < maxDiameter && radius < maxRadius && anchorSize < anchorSizeMax) {
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

        // set it
        this.binetNumber = bestBinetNumber;
        this.binetIndex = bestBinetIndex;
        this.spiralScale = bestSpiralScale;
        this.anchorSize = calculateAnchorSize(binetNumber, binetIndex, spiralScale);
        this.diameter = calculateSpiralDiameter(binetNumber, binetIndex, spiralScale);
    }
    public void createDimentions(double maxDiameter) {
        createDimentions(maxDiameter, maxDiameter);
    }

    // calculations
    private double calculateAnchorSize(double binetNumber, int binetIndex, double spiralScale) {
        return calculateSpiralLength(binetNumber, binetIndex, spiralScale) / numAnchorPoints;
    }
    private double calculateSpiralLength(double binetNumber, int binetIndex, double spiralScale) {
        int length = 0;
        for (int i = 1; i <= binetIndex; i++) {
            length += getArcCircumference(Calculations.getBinetValue(i, binetNumber) * spiralScale);
        }
        return length;
    }
    private double calculateRelativeSpiralDiameter(double binetNumber, int binetIndex) {
        double radiusLargerCircle = Calculations.getBinetValue(binetIndex, binetNumber);
        double radiusSmallerCircle = Calculations.getBinetValue(binetIndex - 1, binetNumber);
        return radiusLargerCircle + radiusSmallerCircle;
    }
    private double calculateSpiralDiameter(double binetNumber, int binetIndex, double spiralScale) {
        return calculateRelativeSpiralDiameter(binetNumber, binetIndex) * spiralScale;
    }
    private static double getArcCircumference(double radius) {
        return Math.PI * radius / 2;
    }
    private double calculateSpiralRadius(double binetNumber, int binetIndex, double spiralScale) {
        double radiusLargerCircle = Calculations.getBinetValue(binetIndex, binetNumber) * spiralScale;
        double radiusMediumCircle = Calculations.getBinetValue(binetIndex - 1, binetNumber) * spiralScale;
        double radiusSmallerCircle = Calculations.getBinetValue(binetIndex - 2, binetNumber) * spiralScale;
        return radiusLargerCircle;
    }
    private double calculateMaxSpiralSize(double binetNumber, int binetIndex, double length) {
        return length / calculateRelativeSpiralDiameter(binetNumber, binetIndex);
    }

    // to string
    @Override
    public String toString() {
        return "DimentionCalculator{" +
                "numAnchorPoints=" + numAnchorPoints +
                ", anchorSize=" + anchorSize +
                ", binetNumber=" + binetNumber +
                ", spiralScale=" + spiralScale +
                ", binetIndex=" + binetIndex +
                ", diameter=" + diameter +
                '}';
    }
}
