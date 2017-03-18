package eu.long1.scaleui;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static eu.long1.scaleui.MainActivity.*;

class Helper {

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

    static void restart(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
        Runtime.getRuntime().exit(0);
    }

}
