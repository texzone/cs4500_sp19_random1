package com.example.cs4500_sp19_random1.models;

import java.util.*;

public class SubscriptionDiscounts {
    private Map<Frequency, Float> discountMap;
    private boolean isFlat;

    public SubscriptionDiscounts() {}
    
    public SubscriptionDiscounts(Map<Frequency, Float> discMap, boolean flat) {
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

    public void setDiscountMap(Map<Frequency, Float> discMap) {
        this.discountMap = discMap;
    }

    public boolean getIsFlat() {
        return this.isFlat;
    }

    public void setIsFlat(boolean isFlat) {
        this.isFlat = isFlat;
    }
}
