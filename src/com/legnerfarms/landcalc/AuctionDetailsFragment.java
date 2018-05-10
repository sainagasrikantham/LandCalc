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
public class AuctionDetailsFragment extends Fragment {
    public final static String ARG_MENU_ID = "menu_id";

    private View mViewAuctionDetails = null;
    private View mViewFragment       = null;

    // Auction Details' Values
    private EditText mEditPricePerAcre   = null;
    private EditText mEditPercentDown    = null;
    private EditText mEditLengthOfLoan   = null;
    private EditText mEditInterestRate   = null;
    private EditText mEditPercentReturn  = null;
    private Switch   mSwitchInvestorMode = null;
    private boolean  mInvestorModeOn     = false;

    // Calculated Values
    private EditText mEditPricePerTillable         = null;
    private EditText mEditYearlyPayment            = null;
    private EditText mEditDownPayment              = null;
    private EditText mEditCashRent                 = null;
    private Button   mButtonCalculateValues        = null;
    private Button   mButtonCalcClearValues        = null;

    // Singleton instance
    private static AuctionDetailsFragment instance = null;

    protected AuctionDetailsFragment() {
        // Empty constructor required for fragment subclasses
    }

    public static AuctionDetailsFragment getInstance() {

        if ( instance == null ) {
            instance = new AuctionDetailsFragment();
        }

        return instance;
    }

    public void grabInputValues() {

        String inputText = null;

        Calculations calcObject = Calculations.getInstance();

        /* Auction Details Values */

        // Price Per Acre
        calcObject.mFloatPricePerAcre = 0;
        inputText = null;
        if ( mEditPricePerAcre != null ) {

            inputText = mEditPricePerAcre.getText().toString();
            if ( inputText.length() > 0 ) {
                calcObject.mFloatPricePerAcre = Float.parseFloat(inputText);
            }
        }

        // Percent Down
        calcObject.mFloatPercentDown = 0;
        inputText = null;
        if ( mEditPercentDown != null ) {

            inputText = mEditPercentDown.getText().toString();
            if ( inputText.length() > 0 ) {
                calcObject.mFloatPercentDown = Float.parseFloat(inputText);
            }
        }

        // Length of Loan
        calcObject.mFloatLengthOfLoan = 0;
        inputText = null;
        if ( mEditLengthOfLoan != null ) {

            inputText = mEditLengthOfLoan.getText().toString();
            if ( inputText.length() > 0 ) {
                calcObject.mFloatLengthOfLoan = Float.parseFloat(inputText);
            }
        }

        // Interest Rate
        calcObject.mFloatInterestRate = 0;
        inputText = null;
        if ( mEditInterestRate != null ) {

            inputText = mEditInterestRate.getText().toString();
            if ( inputText.length() > 0 ) {
                calcObject.mFloatInterestRate = Float.parseFloat(inputText);
            }
        }

        // Percent Return
        calcObject.mFloatPercentReturn = 0;
        inputText = null;
        if ( mEditPercentReturn != null ) {

            inputText = mEditPercentReturn.getText().toString();
            if ( inputText.length() > 0 ) {
                calcObject.mFloatPercentReturn = Float.parseFloat(inputText);
            }
        }

        inputText = null;
    }

    public void clearAuctionDetailsValues() {

        /* Auction Details Values */

        // Price Per Acre
        if ( mEditPricePerAcre != null ) {
            mEditPricePerAcre.setText("");
        }

        // Percent Down
        if ( mEditPercentDown != null ) {
            mEditPercentDown.setText("");
        }

        // Length of Loan
        if ( mEditLengthOfLoan != null ) {
            mEditLengthOfLoan.setText("");
        }

        // Interest Rate
        if ( mEditInterestRate != null ) {
            mEditInterestRate.setText("");
        }

        // Percent Return
        if ( mEditPercentReturn != null ) {
            mEditPercentReturn.setText("");
        }
    }

    public void calculateValues() {

        // Grab the singleton
        Calculations calcObject = Calculations.getInstance();

        // Calculate the values
        calcObject.calculateValues();

        // Set the calculated values to the edit boxes

        // Down Payment
        mEditDownPayment.setText(calcObject.downPayment.toString());

        // Yearly Payment
        mEditYearlyPayment.setText(calcObject.yearlyPayment.toString());

        // Price Per Tillable
        mEditPricePerTillable.setText(calcObject.pricePerTillable.toString());

        // Cash Rent
        if ( mInvestorModeOn ) {
            mEditCashRent.setText(calcObject.cashRent.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = null;
        int selected_menu_item = getArguments().getInt(ARG_MENU_ID);

        switch(selected_menu_item) {

        case MainActivity.MENU_AUCTION_DETAILS: {

            if ( mViewAuctionDetails == null ) {
                mViewAuctionDetails = inflater.inflate(R.layout.calculator_layout, container, false);

                // Input Values
                mEditPricePerAcre  = (EditText) mViewAuctionDetails.findViewById(R.id.editText_price_per_acre);
                mEditPercentDown   = (EditText) mViewAuctionDetails.findViewById(R.id.editText_percent_down);
                mEditLengthOfLoan  = (EditText) mViewAuctionDetails.findViewById(R.id.editText_length_of_loan);
                mEditInterestRate  = (EditText) mViewAuctionDetails.findViewById(R.id.editText_interest_rate);
                mEditPercentReturn = (EditText) mViewAuctionDetails.findViewById(R.id.editText_percentage_return);

                // Investor Mode
                mSwitchInvestorMode = (Switch) mViewAuctionDetails.findViewById(R.id.switch_investor_mode);
                mSwitchInvestorMode.setChecked(mInvestorModeOn);

                // Calculated Values
                mEditPricePerTillable  = (EditText) mViewAuctionDetails.findViewById(R.id.editText_price_per_tillable);
                mEditYearlyPayment     = (EditText) mViewAuctionDetails.findViewById(R.id.editText_yearly_payment);
                mEditDownPayment       = (EditText) mViewAuctionDetails.findViewById(R.id.editText_down_payment);
                mEditCashRent          = (EditText) mViewAuctionDetails.findViewById(R.id.editText_cash_rent);
                mButtonCalculateValues = (Button)   mViewAuctionDetails.findViewById(R.id.button_calculate);
                mButtonCalcClearValues = (Button)   mViewAuctionDetails.findViewById(R.id.button_calc_clear);
            }

            mSwitchInvestorMode.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    mInvestorModeOn = isChecked;
                    mEditPercentReturn.setEnabled(mInvestorModeOn);
                }
            });

            mEditPercentReturn.setEnabled(mInvestorModeOn);

            mButtonCalculateValues.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    // Calculate the values
                    calculateValues();
                }
            });

            mButtonCalcClearValues.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    // Clear calculator screen input values
                    clearAuctionDetailsValues();
                }
            });

            rootView = mViewAuctionDetails;
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
