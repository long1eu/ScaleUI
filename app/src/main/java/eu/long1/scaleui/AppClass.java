package eu.long1.scaleui;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AppClass extends Application {

    public static float DPI;

    private static String scaleDataFileLocation;

    public static void writeScale() {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(scaleDataFileLocation));
            outputStreamWriter.write(DPI + "");
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private static String readScale() {
        String ret = "";
        try {
            FileInputStream inputStream = new FileInputStream(scaleDataFileLocation);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString;
            StringBuilder stringBuilder = new StringBuilder();

            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString);
            }

            inputStream.close();
            ret = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        scaleDataFileLocation = new File(getApplicationInfo().dataDir, "scaleFile.txt").getAbsolutePath();
        String scale = readScale();
        DPI = (scale.isEmpty() ? 1 : Float.parseFloat(scale));
        Log.d("Ap onCreate", DPI + "");
    }
}
