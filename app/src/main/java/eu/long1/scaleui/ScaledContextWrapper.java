package eu.long1.scaleui;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import static android.os.Build.VERSION.SDK_INT;
import static eu.long1.scaleui.AppClass.sScaleRatio;

public class ScaledContextWrapper extends ContextWrapper {

    public ScaledContextWrapper(Context base) {
        super(base);
    }

    @SuppressWarnings("deprecation")
    public static ScaledContextWrapper wrap(Context context) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        configuration.densityDpi = (int) (metrics.densityDpi * sScaleRatio);
        configuration.fontScale = sScaleRatio;

        if (SDK_INT > 17) {
            context = context.createConfigurationContext(configuration);
        } else {
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }

        return new ScaledContextWrapper(context);
    }

}
