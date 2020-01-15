/*
 * Copyright 2019 the original author or authors.
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

package org.bremersee.groupman.mock;

import static org.bremersee.groupman.mock.GroupRepositoryMock.GROUP_0_ID;
import static org.bremersee.groupman.mock.GroupRepositoryMock.GROUP_1_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.bremersee.groupman.model.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * The group admin controller mock test.
 *
 * @author Christian Bremer
 */
class GroupAdminControllerMockTest {

  private final GroupAdminControllerMock mock = new GroupAdminControllerMock();

  /**
   * Sets up.
   */
  @BeforeEach
  void setUp() {
    GroupRepositoryMock.reset();
  }

  /**
   * Add group.
   */
  @Test
  void addGroup() {
    final String value = UUID.randomUUID().toString();
    ResponseEntity<Group> response = mock.addGroup(Group.builder().name(value).build());
    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(value, response.getBody().getName());
  }

  /**
   * Remove group.
   */
  @Test
  void removeGroup() {
    assertNotNull(mock.removeGroup(GROUP_0_ID));
  }

  /**
   * Find group by id.
   */
  @Test
  void findGroupById() {
    Group expected = GroupRepositoryMock.getGroupById(GROUP_0_ID);
    ResponseEntity<Group> response = mock.findGroupById(GROUP_0_ID);
    assertNotNull(response);
    assertEquals(expected, response.getBody());
  }

  /**
   * Find group by id and expect error.
   */
  @Test
  void findGroupByIdAndExpectError() {
    ResponseEntity<Group> response = mock.findGroupById(UUID.randomUUID().toString());
    assertNotNull(response);
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
  }

  /**
   * Find groups.
   */
  @Test
  void findGroups() {
    final List<Group> expected = GroupRepositoryMock.getGroups();
    ResponseEntity<List<Group>> response = mock.findGroups();
    assertNotNull(response);
    assertEquals(expected, response.getBody());
  }

  /**
   * Find groups by ids.
   */
  @Test
  void findGroupsByIds() {
    final List<Group> groups = GroupRepositoryMock
        .getGroupsByIds(Arrays.asList(GROUP_0_ID, GROUP_1_ID));
    ResponseEntity<List<Group>> response = mock
        .findGroupsByIds(Arrays.asList(GROUP_0_ID, GROUP_1_ID));
    assertNotNull(response);
    assertEquals(groups, response.getBody());
  }

  /**
   * Modify group.
   */
  @Test
  void modifyGroup() {
    final String value = UUID.randomUUID().toString();
    final Group newGroup = GroupRepositoryMock.getGroupById(GROUP_0_ID).toBuilder()
        .name(value)
        .build();
    ResponseEntity<Group> response = mock.modifyGroup(GROUP_0_ID, newGroup);
    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(value, response.getBody().getName());
  }

  /**
   * Modify group and expect error.
   */
  @Test
  void modifyGroupAndExpectError() {
    ResponseEntity<Group> response = mock.modifyGroup(
        UUID.randomUUID().toString(), Group.builder().build());
    assertNotNull(response);
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
  }

}