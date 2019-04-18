package com.example.cs4500_sp19_random1;
import com.example.cs4500_sp19_random1.util.Address;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class AddressTest {

  @Test
  public void toStringTest() {
    Address address = new Address("360 Huntington Ave.", "Boston", "MA", "02115");
    assertEquals("360 Huntington Ave., Boston, MA 02115", address.toString());
  }

}
