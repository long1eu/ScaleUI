package eu.long1.scaleui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static eu.long1.scaleui.Helper.findClosest;
import static eu.long1.scaleui.Helper.restart;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    public static final int SCALE_SMALL = 75;
    public static final int SCALE_NORMAL = 100;
    public static final int SCALE_LARGE = 125;
    public static final int SCALE_XLARGE = 150;
    public static final int SCALE_XXLARGE = 175;
    public static final int SCALE_XXXLARGE = 200;

    public static final String SCALE_VALUE = "SCALE_VALUE";

    @BindView(R.id.seekBar) SeekBar seekBar;
    @BindView(R.id.seekBarText) TextView seekBarText;
    private SharedPreferences preferences;
    private float currentScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        preferences = getPreferences(MODE_PRIVATE);
        currentScale = preferences.getFloat(SCALE_VALUE, SCALE_NORMAL / 100f);


        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setProgress((int) (currentScale * 100));
        setScale(currentScale);
        setProgress(currentScale);
    }

    @SuppressWarnings("deprecation")
    private void setScale(float screenRatio) {
        Resources resources = getResources();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Configuration configuration = resources.getConfiguration();
        configuration.densityDpi = (int) (metrics.densityDpi * screenRatio);
        metrics.scaledDensity = metrics.scaledDensity * screenRatio;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progress = findClosest(progress);
        seekBar.setProgress(progress);
        float scaleRatio = (float) progress / 100f;
        setProgress(scaleRatio);
    }

    private void setProgress(float scaleRatio) {
        String value = "x" + scaleRatio;
        seekBarText.setText(value);
        currentScale = scaleRatio;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                preferences.edit().putFloat(SCALE_VALUE, currentScale).commit();
                restart(this);
                break;
        }
        return true;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
