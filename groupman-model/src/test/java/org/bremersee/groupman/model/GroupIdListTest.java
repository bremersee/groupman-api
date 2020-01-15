/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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