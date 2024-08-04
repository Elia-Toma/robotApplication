package it.unicam.cs.mp.robotapplication.api.model;

import it.unicam.cs.mp.robotapplication.utilities.FollowMeShapeChecker;

/**
 * This class implements the {@link FollowMeShapeChecker} interface and provides a method to check the validity
 * of shape parameters.
 */
public class ShapeChecker implements FollowMeShapeChecker {

    private static final String SHAPE_TYPE_CIRCLE = "CIRCLE";
    private static final String SHAPE_TYPE_RECTANGLE = "RECTANGLE";

    private static final int MIN_PARAMETERS_COUNT = 5;
    private static final int RADIUS_INDEX = 4;
    private static final int WIDTH_INDEX = 4;
    private static final int HEIGHT_INDEX = 5;

    /**
     * Checks the validity of the shape parameters.
     *
     * @param args The array of shape parameters to be checked.
     * @return {@code true} if the parameters are valid, {@code false} otherwise.
     */
    @Override
    public boolean checkParameters(String[] args) {
        // Check if the number of parameters is at least 5
        if (args.length < MIN_PARAMETERS_COUNT) return false;

        // Check if the first parameter is not null or empty
        if (args[0] == null || args[0].equals("")) return false;

        // Check if the shape type is either "CIRCLE" or "RECTANGLE"
        if (!args[1].equals(SHAPE_TYPE_CIRCLE) && !args[1].equals(SHAPE_TYPE_RECTANGLE)) return false;

        // Check additional parameters based on the shape type
        if (args[1].equals(SHAPE_TYPE_CIRCLE))
            // For CIRCLE, the last parameter (radius) should not be "0" or negative
            if (args[RADIUS_INDEX].equals("0") || args[RADIUS_INDEX].charAt(0) == '-') return false;
        if (args[1].equals(SHAPE_TYPE_RECTANGLE))
            // For RECTANGLE, the last two parameters (width and height) should not be "0" or negative
            if (args[WIDTH_INDEX].equals("0") || args[WIDTH_INDEX].charAt(0) == '-' || args[HEIGHT_INDEX].equals("0") || args[HEIGHT_INDEX].charAt(0) == '-')
                return false;

        // All checks passed, parameters are valid
        return true;
    }
}
