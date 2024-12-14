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

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

/**
 * The group test.
 *
 * @author Christian Bremer
 */
class GroupTest {

  /**
   * Gets id.
   */
  @Test
  void getId() {
    String value = UUID.randomUUID().toString();
    Group model = new Group();
    model.setId(value);
    assertEquals(value, model.getId());

    model = Group.builder().id(value).build();
    assertEquals(value, model.getId());

    assertNotEquals(null, model);
    assertNotEquals(new Object(), model);
    //noinspection EqualsWithItself
    assertEquals(model, model);
    assertEquals(model, model.toBuilder().id(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets version.
   */
  @Test
  void getVersion() {
    Long value = 123456789L;
    Group model = new Group();
    model.setVersion(value);
    assertEquals(value, model.getVersion());

    model = Group.builder().version(value).build();
    assertEquals(value, model.getVersion());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().version(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets created by.
   */
  @Test
  void getCreatedBy() {
    String value = UUID.randomUUID().toString();
    Group model = new Group();
    model.setCreatedBy(value);
    assertEquals(value, model.getCreatedBy());

    model = Group.builder().createdBy(value).build();
    assertEquals(value, model.getCreatedBy());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().createdBy(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets created at.
   */
  @Test
  void getCreatedAt() {
    OffsetDateTime value = OffsetDateTime.now();
    Group model = new Group();
    model.setCreatedAt(value);
    assertEquals(value, model.getCreatedAt());

    model = Group.builder().createdAt(value).build();
    assertEquals(value, model.getCreatedAt());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().createdAt(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets modified at.
   */
  @Test
  void getModifiedAt() {
    OffsetDateTime value = OffsetDateTime.now();
    Group model = new Group();
    model.setModifiedAt(value);
    assertEquals(value, model.getModifiedAt());

    model = Group.builder().modifiedAt(value).build();
    assertEquals(value, model.getModifiedAt());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().modifiedAt(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets source.
   */
  @Test
  void getSource() {
    Source value = Source.LDAP;
    Group model = new Group();
    model.setSource(value);
    assertEquals(value, model.getSource());

    model = Group.builder().source(value).build();
    assertEquals(value, model.getSource());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().source(value).build());

    assertTrue(model.toString().contains(value.toString()));
  }

  /**
   * Gets name.
   */
  @Test
  void getName() {
    String value = UUID.randomUUID().toString();
    Group model = new Group();
    model.setName(value);
    assertEquals(value, model.getName());

    model = Group.builder().name(value).build();
    assertEquals(value, model.getName());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().name(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets description.
   */
  @Test
  void getDescription() {
    String value = UUID.randomUUID().toString();
    Group model = new Group();
    model.setDescription(value);
    assertEquals(value, model.getDescription());

    model = Group.builder().description(value).build();
    assertEquals(value, model.getDescription());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().description(value).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets members.
   */
  @Test
  void getMembers() {
    String value = UUID.randomUUID().toString();
    List<String> values = Collections.singletonList(value);
    Group model = new Group();
    model.setMembers(values);
    assertEquals(values, model.getMembers());

    model = Group.builder().members(values).build();
    assertEquals(values, model.getMembers());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().members(values).build());

    assertTrue(model.toString().contains(value));
  }

  /**
   * Gets owners.
   */
  @Test
  void getOwners() {
    String value = UUID.randomUUID().toString();
    List<String> values = Collections.singletonList(value);
    Group model = new Group();
    model.setOwners(values);
    assertEquals(values, model.getOwners());

    model = Group.builder().owners(values).build();
    assertEquals(values, model.getOwners());

    assertEquals(model, model);
    assertEquals(model, model.toBuilder().owners(values).build());

    assertTrue(model.toString().contains(value));
  }
}