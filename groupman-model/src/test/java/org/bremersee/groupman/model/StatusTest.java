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

    assertNotEquals(null, model);
    assertNotEquals(new Object(), model);
    //noinspection EqualsWithItself
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

    //noinspection EqualsWithItself
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

    //noinspection EqualsWithItself
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().membershipSize(value).build());

    assertTrue(model.toString().contains(String.valueOf(value)));
  }

}