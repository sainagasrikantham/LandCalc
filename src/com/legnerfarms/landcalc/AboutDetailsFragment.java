package com.legnerfarms.landcalc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Fragment that appears in the "content_frame", shows an option
 */
public class AboutDetailsFragment extends Fragment {
    public final static String ARG_MENU_ID = "menu_id";

    private View mViewAbout          = null;
    private View mViewFragment       = null;

    // Singleton instance
    private static AboutDetailsFragment instance = null;

    protected AboutDetailsFragment() {
        // Empty constructor required for fragment subclasses
    }

    public static AboutDetailsFragment getInstance() {

        if ( instance == null ) {
            instance = new AboutDetailsFragment();
        }

        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = null;
        int selected_menu_item = getArguments().getInt(ARG_MENU_ID);

        switch(selected_menu_item) {

        case MainActivity.MENU_ABOUT: {

            if ( mViewAbout == null ) {
                mViewAbout = inflater.inflate(R.layout.about_layout, container, false);
                mViewAbout.setBackgroundResource(R.drawable.about);
            }
            rootView = mViewAbout;
        }
        break;

        default: {

            if ( mViewFragment == null ) {
                mViewFragment = inflater.inflate(R.layout.fragment_menu, container, false);
            }
            rootView = mViewFragment;
        }
        break;

        }

        String menu = getResources().getStringArray(R.array.menu_array)[selected_menu_item];
        getActivity().setTitle(menu);

        return rootView;
    }
}
