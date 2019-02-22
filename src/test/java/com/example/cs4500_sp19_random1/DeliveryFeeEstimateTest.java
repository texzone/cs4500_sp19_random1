package com.example.cs4500_sp19_random1;

import com.example.cs4500_sp19_random1.models.*;
import com.example.cs4500_sp19_random1.models.Estimate;
import com.example.cs4500_sp19_random1.models.Frequency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import org.junit.rules.*;
import org.junit.Rule;

public class DeliveryFeeEstimateTest {

  DeliveryFee weekdayzero = new DeliveryFee(0.0f, Frequency.WEEKDAY, false);
  DeliveryFee holidaytwenty = new DeliveryFee(20.0f, Frequency.HOLIDAY, false);
  DeliveryFee weekendten = new DeliveryFee(10.0f, Frequency.WEEKEND, false);
  DeliveryFee yearly = new DeliveryFee(15.0f, Frequency.YEARLY, false);
  DeliveryFee emergency = new DeliveryFee(30.0f, Frequency.EMERGENCY, false);
  DeliveryFee weekdayflat = new DeliveryFee(0.0f, Frequency.WEEKDAY, true);
  DeliveryFee holidayflat = new DeliveryFee(20.0f, Frequency.HOLIDAY, true);
  DeliveryFee weekendflat = new DeliveryFee(10.0f, Frequency.WEEKEND, true);
  DeliveryFee yearlyflat = new DeliveryFee(15.0f, Frequency.YEARLY, true);
  DeliveryFee emergencyflat = new DeliveryFee(30.0f, Frequency.EMERGENCY, true);
  List<DeliveryFee> listfees = new ArrayList<>(Arrays.asList(weekdayzero, holidaytwenty, weekendten,
          yearly, emergency));
  List<DeliveryFee> listfeesflat = new ArrayList<>(Arrays.asList(weekdayflat, holidayflat, weekendflat,
          yearlyflat, emergencyflat));
  List<DeliveryFee> listfeesmixed = new ArrayList<>(Arrays.asList(weekdayflat, holidaytwenty, weekendflat,
          yearly, emergencyflat));
  
  HashMap<Frequency, SubscriptionDiscount> garbDiscounts = 
      new HashMap<Frequency, SubscriptionDiscount>();

  Estimate estimate1 = new Estimate(750f, Frequency.HOLIDAY, false,
          Frequency.DAILY, garbDiscounts, Frequency.HOLIDAY, listfees);
  Estimate estimate2 = new Estimate(750f, Frequency.EMERGENCY, false,
          Frequency.DAILY, garbDiscounts, Frequency.WEEKEND, listfees);
  Estimate estimate3 = new Estimate(750f, Frequency.WEEKEND, false,
          Frequency.DAILY, garbDiscounts, Frequency.EMERGENCY, listfees);
  Estimate estimate4 = new Estimate(750f, Frequency.WEEKDAY, false,
          Frequency.DAILY, garbDiscounts, Frequency.YEARLY, listfees);
  Estimate estimate5 = new Estimate(750f, Frequency.YEARLY, false,
          Frequency.DAILY, garbDiscounts, Frequency.WEEKDAY, listfees);

  Estimate estimate1flat = new Estimate(750f, Frequency.HOLIDAY, false,
          Frequency.DAILY, garbDiscounts, Frequency.HOLIDAY, listfeesflat);
  Estimate estimate2flat = new Estimate(750f, Frequency.EMERGENCY, false,
          Frequency.DAILY, garbDiscounts, Frequency.WEEKEND, listfeesflat);
  Estimate estimate3flat = new Estimate(750f, Frequency.WEEKEND, false,
          Frequency.DAILY, garbDiscounts, Frequency.EMERGENCY, listfeesflat);
  Estimate estimate4flat = new Estimate(750f, Frequency.WEEKDAY, false,
          Frequency.DAILY, garbDiscounts, Frequency.YEARLY, listfeesflat);
  Estimate estimate5flat = new Estimate(750f, Frequency.YEARLY, false,
          Frequency.DAILY, garbDiscounts, Frequency.WEEKDAY, listfeesflat);

  Estimate estimate1m = new Estimate(750f, Frequency.HOLIDAY, false,
          Frequency.DAILY, garbDiscounts, Frequency.HOLIDAY, listfeesmixed);
  Estimate estimate2m = new Estimate(750f, Frequency.EMERGENCY, false,
          Frequency.DAILY, garbDiscounts, Frequency.WEEKEND, listfeesmixed);
  Estimate estimate3m = new Estimate(750f, Frequency.WEEKEND, false,
          Frequency.DAILY, garbDiscounts, Frequency.EMERGENCY, listfeesmixed);
  Estimate estimate4m = new Estimate(750f, Frequency.WEEKDAY, false,
          Frequency.DAILY, garbDiscounts, Frequency.YEARLY, listfeesmixed);
  Estimate estimate5m = new Estimate(750f, Frequency.YEARLY, false,
          Frequency.DAILY, garbDiscounts, Frequency.WEEKDAY, listfeesmixed);


  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void testNegativeDeliveryFee(){
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Fee cannot be negative!");
    new DeliveryFee(-1.0f, Frequency.WEEKDAY, false);
  }


  @Test
  public void DeliveryFeeEstimateNotFlat() {
    assertEquals(150f, estimate1.getFees(), 0.001);
    assertEquals(75f, estimate2.getFees(), 0.001);
    assertEquals(225f, estimate3.getFees(), 0.001);
    assertEquals(112.5f, estimate4.getFees(), 0.001);
    assertEquals(0f, estimate5.getFees(), 0.001);
  }

  @Test
  public void DeliveryFeeEstimateFlat() {
    assertEquals(20f, estimate1flat.getFees(), 0.001);
    assertEquals(10f, estimate2flat.getFees(), 0.001);
    assertEquals(30f, estimate3flat.getFees(), 0.001);
    assertEquals(15f, estimate4flat.getFees(), 0.001);
    assertEquals(0f, estimate5flat.getFees(), 0.001);
  }

  @Test
  public void DeliveryFeeEstimate() {
    assertEquals(150f, estimate1m.getFees(), 0.001);
    assertEquals(10f, estimate2m.getFees(), 0.001);
    assertEquals(30f, estimate3m.getFees(), 0.001);
    assertEquals(112.5f, estimate4m.getFees(), 0.001);
    assertEquals(0f, estimate5m.getFees(), 0.001);
  }

  // Update with new final price once discount is added
  @Test
  public void FinalPriceFlatFeeTest() {
    assertEquals(770f, estimate1flat.getFinalPrice(), 0.001);
    assertEquals(760f, estimate2flat.getFinalPrice(), 0.001);
    assertEquals(780f, estimate3flat.getFinalPrice(), 0.001);
    assertEquals(765f, estimate4flat.getFinalPrice(), 0.001);
    assertEquals(750f, estimate5flat.getFinalPrice(), 0.001);
  }

  // Update with new final price once discount is added
  @Test
  public void FinalPricePercentageFeeTest() {
    assertEquals(900f,   estimate1.getFinalPrice(), 0.001);
    assertEquals(825f,   estimate2.getFinalPrice(), 0.001);
    assertEquals(975f,   estimate3.getFinalPrice(), 0.001);
    assertEquals(862.5f, estimate4.getFinalPrice(), 0.001);
    assertEquals(750f,   estimate5.getFinalPrice(), 0.001);
  }

  // Update with new final price once discount is added
  @Test
  public void FinalPriceFrequencyFeeTest() {
    assertEquals(900f,   estimate1m.getFinalPrice(), 0.001);
    assertEquals(760f,   estimate2m.getFinalPrice(), 0.001);
    assertEquals(780f,   estimate3m.getFinalPrice(), 0.001);
    assertEquals(862.5f, estimate4m.getFinalPrice(), 0.001);
    assertEquals(750f,   estimate5m.getFinalPrice(), 0.001);
  }

  @Test
  public void ProgressiveFeeTest() {
    estimate1.generateProgressiveFee(10f);
    estimate2.generateProgressiveFee(10f);
    estimate3.generateProgressiveFee(10f);
    estimate4.generateProgressiveFee(10f);
    estimate5.generateProgressiveFee(10f);
    assertEquals(225f, estimate1.getFees(), 0.001);
    assertEquals(150f, estimate2.getFees(), 0.001);
    assertEquals(300f, estimate3.getFees(), 0.001);
    assertEquals(187.5f, estimate4.getFees(), 0.001);
    assertEquals(75f, estimate5.getFees(), 0.001);
  }

  // Update with new final price once discount is added
  @Test
  public void ProgressiveFeeFinalPriceTest() {
    estimate1flat.generateProgressiveFee(10f);
    estimate2flat.generateProgressiveFee(10f);
    estimate3flat.generateProgressiveFee(10f);
    estimate4flat.generateProgressiveFee(10f);
    estimate5flat.generateProgressiveFee(10f);
    assertEquals(845f, estimate1flat.getFinalPrice(), 0.001);
    assertEquals(835f, estimate2flat.getFinalPrice(), 0.001);
    assertEquals(855f, estimate3flat.getFinalPrice(), 0.001);
    assertEquals(840f, estimate4flat.getFinalPrice(), 0.001);
    assertEquals(825f, estimate5flat.getFinalPrice(), 0.001);
  }

}
