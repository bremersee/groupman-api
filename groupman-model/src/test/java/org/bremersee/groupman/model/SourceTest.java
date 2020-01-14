package org.bremersee.groupman.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;
import org.junit.jupiter.api.Test;

/**
 * The source test.
 *
 * @author Christian Bremer
 */
class SourceTest {

  /**
   * From value.
   */
  @Test
  void fromValue() {
    for (Source expected : Source.values()) {
      assertEquals(expected, Source.fromValue(expected.toString()));
    }
    assertNull(Source.fromValue(UUID.randomUUID().toString()));
  }
}