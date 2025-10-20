package org.howard.edu.lsp.midterm.question2;

public class AreaCalculator {
    // Circle area: π r^2
    public static double area(double radius) {
        validatePositive(radius, "radius");
        return Math.PI * radius * radius; // correct formula
    }

    // Rectangle area: width * height
    public static double area(double width, double height) {
        validatePositive(width, "width");
        validatePositive(height, "height");
        return width * height; // correct formula
    }

    // Triangle area: 1/2 * base * height
    public static double area(int base, int height) {
        validatePositive(base, "base");
        validatePositive(height, "height");
        return 0.5 * base * height; // correct formula
    }

    // Square area: side^2
    public static double area(int side) {
        validatePositive(side, "side");
        return (double) side * side; // correct formula
    }

    private static void validatePositive(double v, String name) {
        if (v <= 0) throw new IllegalArgumentException(name + " must be > 0");
    }

    private static void validatePositive(int v, String name) {
        if (v <= 0) throw new IllegalArgumentException(name + " must be > 0");
    }
}
