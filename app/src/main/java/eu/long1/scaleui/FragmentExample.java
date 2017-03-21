package eu.long1.scaleui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentExample extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Context scaledContext = ScaledContextWrapper.wrap(getActivity());
        LayoutInflater scaledInflater = LayoutInflater.from(scaledContext);

        return scaledInflater.inflate(R.layout.fragment_layout, container, false);
    }
}
