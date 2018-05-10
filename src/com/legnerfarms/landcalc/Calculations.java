package com.legnerfarms.landcalc;

public class Calculations {

    // Auction Details' Values
    public float mFloatPricePerAcre  = 0;
    public float mFloatPercentDown   = 0;
    public float mFloatLengthOfLoan  = 0;
    public float mFloatInterestRate  = 0;
    public float mFloatPercentReturn = 0;

    // Land Details' Values
    public float mFloatTotalAcres             = 0;
    public float mFloatTillableAcres          = 0;
    public float mFloatYearlyCRPPayments      = 0;
    public float mFloatYearlyTaxes            = 0;
    public float mFloatNumberOfYearlyPayments = 0;

    // Calculated Values
    public Float downPayment;
    public Float yearlyPayment;
    public Float pricePerTillable;
    public Float cashRent;

    protected Calculations() {

    }

    public static Calculations getInstance() {

        if ( instance == null ) {
            instance = new Calculations(); 
        }

        return instance;
    }

    public void calculateValues() {

        // Grab values from both 'Auction Details' and 'Land Details' screens
        
        AuctionDetailsFragment auctionDetails = AuctionDetailsFragment.getInstance();
        auctionDetails.grabInputValues();
        
        LandDetailsFragment landDetails = LandDetailsFragment.getInstance();
        landDetails.grabInputValues();
        
        Calculations calcObject = Calculations.getInstance();

        /* Calculations */

        // Total Dollars
        Float totalDollars = (float) (calcObject.mFloatPricePerAcre * calcObject.mFloatTotalAcres);

        // Down Payment
        calcObject.downPayment = (float) (totalDollars * (calcObject.mFloatPercentDown/100));

        // Principal
        Float principal = (float) (totalDollars - calcObject.downPayment);

        // Interest
        // 09/16/14 - Per spec, "Number of Yearly Payments" are not to be used in calculations
        //Float interest = (float) ((calcObject.mFloatInterestRate/100) / calcObject.mFloatNumberOfYearlyPayments);
        Float interest = (float) ((calcObject.mFloatInterestRate/100));

        // Yearly Payment
        int lengthOfLoan = (int) calcObject.mFloatLengthOfLoan;

        float onePlusIPowerN = 1;
        for( int index=0; index <lengthOfLoan; ++index) {
            onePlusIPowerN *= (float) (1+interest);
        }

        float equationOne   = (float) (principal * onePlusIPowerN * interest);
        float equationTwo   = (float) (onePlusIPowerN - 1);
        float equationThree = (float) (equationOne/equationTwo);

        calcObject.yearlyPayment = (float) ((equationThree + calcObject.mFloatYearlyTaxes) - calcObject.mFloatYearlyCRPPayments);

        // Price Per Tillable
        calcObject.pricePerTillable = (float)(calcObject.yearlyPayment/calcObject.mFloatTillableAcres);

        // Cash Rent
        calcObject.cashRent = (float) (((totalDollars - calcObject.mFloatYearlyCRPPayments)/calcObject.mFloatTillableAcres)*(calcObject.mFloatPercentReturn/100));
    }

    private static Calculations instance = null;
}
