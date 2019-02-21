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

  DeliveryFee weekdayzero = new DeliveryFee(0.0f, Frequency.WEEKDAY, true);
  DeliveryFee holidaytwenty = new DeliveryFee(20.0f, Frequency.HOLIDAY, false);
  DeliveryFee weekendten = new DeliveryFee(10.0f, Frequency.WEEKEND, false);
  DeliveryFee yearlyflat = new DeliveryFee(15.0f, Frequency.YEARLY, true);
  DeliveryFee emergency = new DeliveryFee(30.0f, Frequency.EMERGENCY, false);
  List<DeliveryFee> listfees = new ArrayList<>(Arrays.asList(weekdayzero, holidaytwenty, weekendten, emergency, yearlyflat));


  Estimate estimate1 = new Estimate(0f, 750f, Frequency.HOLIDAY, false, Frequency.DAILY, Frequency.HOLIDAY, listfees);
  Estimate estimate2 = new Estimate(0f, 900f, Frequency.EMERGENCY, false, Frequency.DAILY, Frequency.WEEKEND, listfees);
  Estimate estimate3 = new Estimate(0f, 30f, Frequency.WEEKEND, false, Frequency.DAILY, Frequency.EMERGENCY,  listfees);
  Estimate estimate4 = new Estimate(0f, 200f, Frequency.WEEKDAY, false, Frequency.DAILY, Frequency.WEEKDAY,  listfees);
//  Estimate estimate5 = new Estimate(0f, 100f, Frequency.YEARLY, false, Frequency.DAILY, listfees2);

  @Test
  public void DeliveryFeeEstimate() {
    assertEquals(150f, estimate1.getFees(), 0);
    assertEquals(150f, estimate1.getFees(), 0);
  }

}