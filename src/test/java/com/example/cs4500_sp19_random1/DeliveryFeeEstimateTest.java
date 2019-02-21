package com.example.cs4500_sp19_random1;

import com.example.cs4500_sp19_random1.models.*;
import com.example.cs4500_sp19_random1.models.Estimate;
import com.example.cs4500_sp19_random1.models.Frequency;

import org.junit.Test;
//import org.junit.Assert;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

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


  Estimate estimate1 = new Estimate(0f, 750f, Frequency.HOLIDAY, false,
          Frequency.DAILY, Frequency.HOLIDAY, listfees);
  Estimate estimate2 = new Estimate(0f, 750f, Frequency.EMERGENCY, false,
          Frequency.DAILY, Frequency.WEEKEND, listfees);
  Estimate estimate3 = new Estimate(0f, 750f, Frequency.WEEKEND, false,
          Frequency.DAILY, Frequency.EMERGENCY, listfees);
  Estimate estimate4 = new Estimate(0f, 750f, Frequency.WEEKDAY, false,
          Frequency.DAILY, Frequency.YEARLY, listfees);
  Estimate estimate5 = new Estimate(0f, 750f, Frequency.YEARLY, false,
          Frequency.DAILY, Frequency.WEEKDAY, listfees);

  Estimate estimate1flat = new Estimate(0f, 750f, Frequency.HOLIDAY, false,
          Frequency.DAILY, Frequency.HOLIDAY, listfeesflat);
  Estimate estimate2flat = new Estimate(0f, 750f, Frequency.EMERGENCY, false,
          Frequency.DAILY, Frequency.WEEKEND, listfeesflat);
  Estimate estimate3flat = new Estimate(0f, 750f, Frequency.WEEKEND, false,
          Frequency.DAILY, Frequency.EMERGENCY, listfeesflat);
  Estimate estimate4flat = new Estimate(0f, 750f, Frequency.WEEKDAY, false,
          Frequency.DAILY, Frequency.YEARLY, listfeesflat);
  Estimate estimate5flat = new Estimate(0f, 750f, Frequency.YEARLY, false,
          Frequency.DAILY, Frequency.WEEKDAY, listfeesflat);

  Estimate estimate1m = new Estimate(0f, 750f, Frequency.HOLIDAY, false,
          Frequency.DAILY, Frequency.HOLIDAY, listfeesmixed);
  Estimate estimate2m = new Estimate(0f, 750f, Frequency.EMERGENCY, false,
          Frequency.DAILY, Frequency.WEEKEND, listfeesmixed);
  Estimate estimate3m = new Estimate(0f, 750f, Frequency.WEEKEND, false,
          Frequency.DAILY, Frequency.EMERGENCY, listfeesmixed);
  Estimate estimate4m = new Estimate(0f, 750f, Frequency.WEEKDAY, false,
          Frequency.DAILY, Frequency.YEARLY, listfeesmixed);
  Estimate estimate5m = new Estimate(0f, 750f, Frequency.YEARLY, false,
          Frequency.DAILY, Frequency.WEEKDAY, listfeesmixed);

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


}