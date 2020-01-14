package org.bremersee.groupman.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * The status test.
 *
 * @author Christian Bremer
 */
class StatusTest {

  /**
   * Gets max owned groups.
   */
  @Test
  void getMaxOwnedGroups() {
    long value = 123456789L;
    Status model = new Status();
    model.setMaxOwnedGroups(value);
    assertEquals(value, model.getMaxOwnedGroups());

    model = Status.builder().maxOwnedGroups(value).build();
    assertEquals(value, model.getMaxOwnedGroups());

    assertNotEquals(model, null);
    assertNotEquals(model, new Object());
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().maxOwnedGroups(value).build());

    assertTrue(model.toString().contains(String.valueOf(value)));
  }

  /**
   * Gets owned group size.
   */
  @Test
  void getOwnedGroupSize() {
    long value = 123456789L;
    Status model = new Status();
    model.setOwnedGroupSize(value);
    assertEquals(value, model.getOwnedGroupSize());

    model = Status.builder().ownedGroupSize(value).build();
    assertEquals(value, model.getOwnedGroupSize());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().ownedGroupSize(value).build());

    assertTrue(model.toString().contains(String.valueOf(value)));
  }

  /**
   * Gets membership size.
   */
  @Test
  void getMembershipSize() {
    long value = 123456789L;
    Status model = new Status();
    model.setMembershipSize(value);
    assertEquals(value, model.getMembershipSize());

    model = Status.builder().membershipSize(value).build();
    assertEquals(value, model.getMembershipSize());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().membershipSize(value).build());

    assertTrue(model.toString().contains(String.valueOf(value)));
  }
}