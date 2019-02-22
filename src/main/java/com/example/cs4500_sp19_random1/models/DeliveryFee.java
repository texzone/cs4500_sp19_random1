package com.example.cs4500_sp19_random1.models;

public class DeliveryFee {
  private float fee;
  private Frequency frequency;
  private boolean flat;

  public DeliveryFee(float fee, Frequency frequency, boolean flat) {
    if(fee < 0.0f) {
      throw new IllegalArgumentException("Can't be negative");
    } else {
      this.fee = fee;
    }
    this.frequency = frequency;
    this.flat = flat;
  }

  public float getFee() {
    return fee;
  }

  public Frequency getFrequency() {
    return frequency;
  }

  public boolean isFlat() {
    return flat;
  }

  public void setFee(float fee) {
    this.fee = fee;
  }

  public void setFrequency(Frequency frequency) {
    this.frequency = frequency;
  }

  public void setFlat(boolean flat) {
    this.flat = flat;
  }
}
