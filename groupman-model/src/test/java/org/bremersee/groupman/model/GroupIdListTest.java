package org.bremersee.groupman.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.UUID;
import org.junit.jupiter.api.Test;

/**
 * The group id list test.
 *
 * @author Christian Bremer
 */
class GroupIdListTest {

  /**
   * Test to string.
   */
  @Test
  void testToString() {
    String value = UUID.randomUUID().toString();
    GroupIdList expected = new GroupIdList();
    expected.add(value);
    GroupIdList actual = new GroupIdList(Collections.singleton(value));
    assertEquals(expected, actual);
    assertEquals(actual, actual);
    assertNotEquals(actual, null);
    assertNotEquals(actual, new Object());

    assertTrue(actual.toString().contains(value));
  }
}