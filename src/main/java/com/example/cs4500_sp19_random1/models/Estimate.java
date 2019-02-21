package com.example.cs4500_sp19_random1.models;

import java.util.List;

public class Estimate {
    private float estimate;
    private float basePrice;
    private Frequency baseFrequency;
    private boolean subscription;
    private Frequency subscriptionFrequency;
    private Frequency deliveryFrequency;
    private List<DeliveryFee> deliveryFees;
    
    public Estimate(float est, float basePrice, Frequency baseFrequency,
            boolean sub, Frequency subFreq, Frequency deliveryFreq, List<DeliveryFee> deliveryFees) {
        this.estimate              = est;
        this.basePrice             = basePrice;
        this.baseFrequency         = baseFrequency;
        this.subscription          = sub;
        this.subscriptionFrequency = subFreq;
        this.deliveryFrequency     = deliveryFreq;
        this.deliveryFees          = deliveryFees;
    }

    public float getEstimate() {
        return this.estimate;
    }

    public void setEstimate(float estimate) {
        this.estimate = estimate;
    }

    public float getBasePrice() {
        return this.basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public Frequency getBaseFrequency() {
        return this.baseFrequency;
    }

    public void setBaseFrequency(Frequency baseFrequency) {
        this.baseFrequency = baseFrequency;
    }

    public boolean getSubscription() {
        return this.subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    public Frequency getSubscriptionFrequency() {
        return this.subscriptionFrequency;
    }

    public void setSubscriptionFrequency(Frequency subFrequency) {
        this.subscriptionFrequency = subFrequency;
    }

    public Frequency getDeliveryFrequency() {
        return this.deliveryFrequency;
    }

    public void setDeliveryFrequency(Frequency deliveryFrequency) {
        this.deliveryFrequency = deliveryFrequency;
    }

    public List<DeliveryFee> getDeliveryFees() {
        return this.deliveryFees;
    }

    public void setDeliveryFees(List<DeliveryFee> deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

    public float getFinalPrice() {
        return basePrice + getFees() - getDiscount();
    }

    // TODO: Add a real discount algorithm
    public float getDiscount() {
        return 0.0f;
    }

    public float getFees() {
        Float ans = 0.0f;
        for(DeliveryFee fee : deliveryFees) {
            if(fee.getFrequency().equals(deliveryFrequency)) {
                if(fee.isFlat() == true) {
                    ans = fee.getFee();
                } else {
                    ans = fee.getFee() / 100 * basePrice;
                }
            }
        }
        return ans;
    }

}
