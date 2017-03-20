package eu.long1.scaleui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static eu.long1.scaleui.AppClass.DPI;
import static eu.long1.scaleui.AppClass.writeScale;
import static eu.long1.scaleui.Helper.findClosest;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    public static final int SCALE_SMALL = 75;
    public static final int SCALE_NORMAL = 100;
    public static final int SCALE_LARGE = 125;
    public static final int SCALE_XLARGE = 150;
    public static final int SCALE_XXLARGE = 175;
    public static final int SCALE_XXXLARGE = 200;

    @BindView(R.id.seekBar) SeekBar seekBar;
    @BindView(R.id.seekBarText) TextView seekBarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setProgress((int) (DPI * 100));
        setProgress(DPI);

        getFragmentManager().beginTransaction().add(R.id.fragmentPlace, new FragmentExample()).commit();
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
        DPI = scaleRatio;
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
                writeScale();
                recreate();
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
