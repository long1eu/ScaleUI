package eu.long1.scaleui;

import java.util.ArrayList;

class ScaleHelper {

    private static final int SCALE_SMALL = 75;
    private static final int SCALE_NORMAL = 100;
    private static final int SCALE_LARGE = 125;
    private static final int SCALE_XLARGE = 150;
    private static final int SCALE_XXLARGE = 175;
    private static final int SCALE_XXXLARGE = 200;

    private static ArrayList<Integer> numbers = new ArrayList<>();

    static {
        numbers.add(SCALE_SMALL);
        numbers.add(SCALE_NORMAL);
        numbers.add(SCALE_LARGE);
        numbers.add(SCALE_XLARGE);
        numbers.add(SCALE_XXLARGE);
        numbers.add(SCALE_XXXLARGE);
    }

    static int findClosest(int number) {
        int distance = Math.abs(numbers.get(0) - number);
        int index = 0;
        for (int c = 1; c < numbers.size(); c++) {
            int currentDistance = Math.abs(numbers.get(c) - number);
            if (currentDistance < distance) {
                index = c;
                distance = currentDistance;
            }
        }
        return numbers.get(index);
    }
}
