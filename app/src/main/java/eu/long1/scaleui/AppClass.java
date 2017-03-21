package eu.long1.scaleui;

import android.app.Application;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class AppClass extends Application {

    private static final String FILE_NAME = "scaleFile.txt";
    public static float sScaleRatio;

    public static void saveScale(Context context) {
        try {
            File scaleDataFileLocation = new File(ContextCompat.getDataDir(context), FILE_NAME);
            FileUtils.writeStringToFile(scaleDataFileLocation,
                    String.valueOf(sScaleRatio),
                    Charset.defaultCharset());
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        readScale(this);
    }

    private void readScale(Context context) {
        try {
            File scaleDataFileLocation = new File(ContextCompat.getDataDir(context), FILE_NAME);
            String scale = FileUtils.readFileToString(scaleDataFileLocation, Charset.defaultCharset());

            sScaleRatio = (scale.isEmpty() ? 1 : Float.parseFloat(scale));
        } catch (IOException e) {
            Log.e("Exception", "Can not read file: " + e.toString());

            sScaleRatio = 1;
            saveScale(this);
        }
    }
}
