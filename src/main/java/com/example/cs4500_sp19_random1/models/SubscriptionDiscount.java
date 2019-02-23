package com.example.cs4500_sp19_random1.models;

import java.util.*;

public class SubscriptionDiscount {
    private float discount;
    private boolean isFlat;

    public SubscriptionDiscount(float discount, boolean flat) {
        this.discount     = discount;
        this.isFlat       = flat;
    }

    public float getDiscount() {
        return this.discount;
    }

    public void setDiscount(float disc) {
        this.discount = disc;
    }

    public boolean getIsFlat() {
        return this.isFlat;
    }

    public void setIsFlat(boolean isFlat) {
        this.isFlat = isFlat;
    }
}
