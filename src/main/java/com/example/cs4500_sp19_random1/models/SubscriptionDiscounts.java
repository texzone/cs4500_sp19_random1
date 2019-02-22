package com.example.cs4500_sp19_random1.models;

import java.util.*;

public class SubscriptionDiscounts {
    private HashMap<Frequency, Float> discountMap;
    private boolean isFlat;

    public SubscriptionDiscounts() {
        this.discountMap = new HashMap<Frequency, Float>();
        this.isFlat      = false;
    }
    
    public SubscriptionDiscounts(HashMap<Frequency, Float> discMap, boolean flat) {
        this.discountMap = discMap;
        this.isFlat      = flat;
    }

    public float getDiscountFor(Frequency f) {
        Float discount = this.discountMap.get(f);
        if (discount == null) {
            return 0f;
        }
        else {
            return discount.floatValue();
        }
    }

    public void setDiscountMap(HashMap<Frequency, Float> discMap) {
        this.discountMap = discMap;
    }

    public boolean getIsFlat() {
        return this.isFlat;
    }

    public void setIsFlat(boolean isFlat) {
        this.isFlat = isFlat;
    }
}
