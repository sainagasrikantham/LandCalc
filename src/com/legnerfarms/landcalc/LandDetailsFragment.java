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
public class LandDetailsFragment extends Fragment {
    public final static String ARG_MENU_ID = "menu_id";

    private View mViewLandDetails    = null;
    private View mViewAbout          = null;
    private View mViewFragment       = null;

    // Land Details' Values
    private EditText mEditTotalAcres               = null;
    private EditText mEditTillableAcres            = null;
    private EditText mEditYearlyCRPPayments        = null;
    private EditText mEditYearlyTaxes              = null;
    private EditText mEditNumberOfYearlyPayments   = null;
    private Button   mButtonLandDetailsClearValues = null;
    
    // Singleton instance
    private static LandDetailsFragment instance = null;

    protected LandDetailsFragment() {
        // Empty constructor required for fragment subclasses
    }
    
    public static LandDetailsFragment getInstance() {
        
        if ( instance == null ) {
            instance = new LandDetailsFragment();
        }
        
        return instance;
    }

    public void grabInputValues() {

        String inputText = null;
        
        Calculations calcObject = Calculations.getInstance();

        /* Land Details Values */

        // Total Acres
        calcObject.mFloatTotalAcres = 0;
        inputText = null;
        if ( mEditTotalAcres != null ) {

            inputText = mEditTotalAcres.getText().toString();
            if ( inputText.length() > 0 ) {
                calcObject.mFloatTotalAcres = Float.parseFloat(inputText);
            }
        }

        // Tillable Acres
        calcObject.mFloatTillableAcres = 0;
        inputText = null;
        if ( mEditTillableAcres != null ) {

            inputText = mEditTillableAcres.getText().toString();
            if ( inputText.length() > 0 ) {
                calcObject.mFloatTillableAcres = Float.parseFloat(inputText);
            }
        }

        // Yearly CRP Payments
        calcObject.mFloatYearlyCRPPayments = 0;
        inputText = null;
        if ( mEditYearlyCRPPayments != null ) {

            inputText = mEditYearlyCRPPayments.getText().toString();
            if ( inputText.length() > 0 ) {
                calcObject.mFloatYearlyCRPPayments = Float.parseFloat(inputText);
            }
        }

        // Yearly Taxes
        calcObject.mFloatYearlyTaxes = 0;
        inputText = null;
        if ( mEditYearlyTaxes != null ) {

            inputText = mEditYearlyTaxes.getText().toString();
            if ( inputText.length() > 0 ) {
                calcObject.mFloatYearlyTaxes = Float.parseFloat(inputText);
            }
        }

        // Number of Yearly Payments
        calcObject.mFloatNumberOfYearlyPayments = 0;
        inputText = null;
        if ( mEditNumberOfYearlyPayments != null ) {

            inputText = mEditNumberOfYearlyPayments.getText().toString();
            if ( inputText.length() > 0 ) {
                calcObject.mFloatNumberOfYearlyPayments = Float.parseFloat(inputText);
            }
        }

        inputText = null;
    }

    public void clearLandDetailsValues() {

        /* Land Details Values */

        // Total Acres
        if ( mEditTotalAcres != null ) {
            mEditTotalAcres.setText("");
        }

        // Tillable Acres
        if ( mEditTillableAcres != null ) {
            mEditTillableAcres.setText("");
        }

        // Yearly CRP Payments
        if ( mEditYearlyCRPPayments != null ) {
            mEditYearlyCRPPayments.setText("");
        }

        // Yearly Taxes
        if ( mEditYearlyTaxes != null ) {
            mEditYearlyTaxes.setText("");
        }

        // Number of Yearly Payments
        if ( mEditNumberOfYearlyPayments != null ) {
            mEditNumberOfYearlyPayments.setText("");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = null;
        int selected_menu_item = getArguments().getInt(ARG_MENU_ID);

        switch(selected_menu_item) {

        case MainActivity.MENU_LAND_DETAILS: {

            if ( mViewLandDetails == null ) {
                mViewLandDetails = inflater.inflate(R.layout.secondary_layout, container, false);

                // More Values
                mEditTotalAcres               = (EditText) mViewLandDetails.findViewById(R.id.editText_total_acres);
                mEditTillableAcres            = (EditText) mViewLandDetails.findViewById(R.id.editText_tillable_acres);
                mEditYearlyCRPPayments        = (EditText) mViewLandDetails.findViewById(R.id.editText_yearly_crp_payments);
                mEditYearlyTaxes              = (EditText) mViewLandDetails.findViewById(R.id.editText_yearly_taxes);
                mEditNumberOfYearlyPayments   = (EditText) mViewLandDetails.findViewById(R.id.editText_number_of_yearly_payments);
                mButtonLandDetailsClearValues = (Button)   mViewLandDetails.findViewById(R.id.button_land_details_clear);
            }

            mButtonLandDetailsClearValues.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    // Clear calculator screen input values
                    clearLandDetailsValues();
                }
            });

            rootView = mViewLandDetails;
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
