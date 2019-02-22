package com.example.cs4500_sp19_random1;

import com.example.cs4500_sp19_random1.models.*;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.assertEquals;

public class SubscriptionDiscountEstimateTest {


    SubscriptionDiscount flatNone = new SubscriptionDiscount(0f, true);
    SubscriptionDiscount flatLow  = new SubscriptionDiscount(5f, true);
    SubscriptionDiscount flatMed  = new SubscriptionDiscount(50f, true);
    SubscriptionDiscount flatHigh = new SubscriptionDiscount(300f, true);
    SubscriptionDiscount flatOver = new SubscriptionDiscount(750f, true);

    HashMap<Frequency, SubscriptionDiscount> theMap = 
        new HashMap<Frequency, SubscriptionDiscount>();
    List<DeliveryFee> garbFees = new ArrayList<DeliveryFee>();

    Estimate oneEstimate   = new Estimate(700f, Frequency.MONTHLY, true,
            Frequency.MONTHLY, null, Frequency.MONTHLY, garbFees); 
    /* WILL BE USED IN FUTURE PR
    Estimate twoEstimate   = new Estimate(700f, Frequency.DAILY, true,
            Frequency.WEEKLY, null, Frequency.MONTHLY, garbFees); 
    Estimate threeEstimate = new Estimate(700f, Frequency.DAILY, true,
            Frequency.DAILY, null, Frequency.MONTHLY, garbFees); 
    */
    Estimate noSubEstimate = new Estimate(700f, Frequency.DAILY, false,
            Frequency.DAILY, null, Frequency.MONTHLY, garbFees);

    public void prestep(Frequency f, SubscriptionDiscount s) {
        theMap.clear();
        theMap.put(f, s);
    }

    @Test
    public void testGetDiscountFlat() {
        oneEstimate.setSubscriptionDiscounts(theMap);
        noSubEstimate.setSubscriptionDiscounts(theMap);

        prestep(Frequency.MONTHLY, flatHigh); 
        assertEquals(300f, oneEstimate.getDiscount(), 0.01);
        assertEquals(0f, noSubEstimate.getDiscount(), 0.01);

        prestep(Frequency.MONTHLY, flatMed);
        assertEquals(50f, oneEstimate.getDiscount(), 0.01);

        prestep(Frequency.MONTHLY, flatLow);
        assertEquals(5f, oneEstimate.getDiscount(), 0.01);

        prestep(Frequency.MONTHLY, flatOver);
        assertEquals(750f, oneEstimate.getDiscount(), 0.01);

        prestep(Frequency.MONTHLY, flatNone);
        assertEquals(0f, oneEstimate.getDiscount(), 0.01);
    }

    @Test
    public void testGetFinalPriceFlatDiscounts() {
        oneEstimate.setSubscriptionDiscounts(theMap);
        noSubEstimate.setSubscriptionDiscounts(theMap);

        prestep(Frequency.MONTHLY, flatHigh); 
        assertEquals(400f, oneEstimate.getFinalPrice(), 0.01);
        assertEquals(700f, noSubEstimate.getFinalPrice(), 0.01);

        prestep(Frequency.MONTHLY, flatMed);
        assertEquals(650f, oneEstimate.getFinalPrice(), 0.01);

        prestep(Frequency.MONTHLY, flatLow);
        assertEquals(695f, oneEstimate.getFinalPrice(), 0.01);

        prestep(Frequency.MONTHLY, flatNone);
        assertEquals(700f, oneEstimate.getFinalPrice(), 0.01);

        prestep(Frequency.MONTHLY, flatOver);
        assertEquals(-50f, oneEstimate.getFinalPrice(), 0.01);
    }
}

