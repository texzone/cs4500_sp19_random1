package com.example.cs4500_sp19_random1.models;

import java.util.List;
import java.util.HashMap;

public class Estimate {
    private float estimate;
    private float basePrice;
    private Frequency baseFrequency;
    private boolean subscription;
    private Frequency subscriptionFrequency;
    private Frequency deliveryFrequency;
    private List<DeliveryFee> deliveryFees;
    private HashMap<Frequency, SubscriptionDiscount> subscriptionDiscounts;
    
    public Estimate(float basePrice, Frequency baseFrequency,
            boolean sub, Frequency subFreq, HashMap<Frequency, SubscriptionDiscount> subDiscounts,
            Frequency deliveryFreq, List<DeliveryFee> deliveryFees) {
        this.basePrice             = basePrice;
        this.baseFrequency         = baseFrequency;
        this.subscription          = sub;
        this.subscriptionFrequency = subFreq;
        this.subscriptionDiscounts = subDiscounts;
        this.deliveryFrequency     = deliveryFreq;
        this.deliveryFees          = deliveryFees;
    }

    public float getEstimate() {
        this.estimate = this.getFinalPrice();
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

    public HashMap<Frequency, SubscriptionDiscount> getSubscriptionDiscounts() {
        return this.subscriptionDiscounts;
    }

    public void setSubscriptionDiscounts(HashMap<Frequency, SubscriptionDiscount> subDiscs) {
        this.subscriptionDiscounts = subDiscs;
    }

    public void addSubscriptionDiscount(Frequency f, SubscriptionDiscount disc) {
        this.subscriptionDiscounts.put(f, disc);
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

    public void addDeliveryFee(DeliveryFee fee) {
        this.deliveryFees.add(fee);
    }

    public void generateProgressiveFee(Float percentFee) {
        DeliveryFee progressiveFee = new DeliveryFee(percentFee, deliveryFrequency, false);
        addDeliveryFee(progressiveFee);
    }

    public float getFinalPrice() {
        return basePrice + getFees() - getDiscount();
    }

    public float getDiscount() {
        if (!this.subscription) {
            return 0f;
        }
        else {
            SubscriptionDiscount theDiscount = this.subscriptionDiscounts.get(
                    this.subscriptionFrequency);
            if (null == theDiscount) {
                return 0f;
            }
            else if (theDiscount.getIsFlat()) {
                return theDiscount.getDiscount();
            }
            else {
                return (theDiscount.getDiscount() / 100f) * this.basePrice;
            }
        }
    }

    public float getFees() {
        Float ans = 0.0f;
        for(DeliveryFee fee : deliveryFees) {
            if(fee.getFrequency().equals(deliveryFrequency)) {
                if(fee.isFlat() == true) {
                    ans = ans + fee.getFee();
                } else {
                    Float percentFee = fee.getFee() / 100 * basePrice;
                    ans = ans + percentFee;
                }
            }
        }
        return ans;
    }

}
