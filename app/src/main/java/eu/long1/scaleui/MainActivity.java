package eu.long1.scaleui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static eu.long1.scaleui.AppClass.sScaleRatio;
import static eu.long1.scaleui.AppClass.saveScale;
import static eu.long1.scaleui.ScaleHelper.findClosest;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.seekBar) SeekBar seekBar;
    @BindView(R.id.seekBarText) TextView seekBarText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        seekBar.setProgress((int) (sScaleRatio * 100));
        seekBar.setOnSeekBarChangeListener(this);
        seekBarText.setText("x" + sScaleRatio);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentPlace, new FragmentExample())
                .commit();
    }

    //Use this if you want to scale the whole activity
    /*
    @Override
    protected void attachBaseContext(Context newBase) {
        Context context = ScaledContextWrapper.wrap(newBase);
        super.attachBaseContext(context);
    }*/

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progress = findClosest(progress);
        seekBar.setProgress(progress);
        float scaleRatio = (float) progress / 100f;
        String value = "x" + scaleRatio;
        seekBarText.setText(value);
        sScaleRatio = scaleRatio;
        recreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveScale(this);
                break;
        }
        return true;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
