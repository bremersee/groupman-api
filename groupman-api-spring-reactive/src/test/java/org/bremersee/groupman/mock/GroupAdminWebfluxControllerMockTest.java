/*
 * Copyright 2019-2020 the original author or authors.
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

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.bremersee.exception.ServiceException;
import org.bremersee.groupman.model.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/**
 * The group admin webflux controller mock test.
 *
 * @author Christian Bremer
 */
class GroupAdminWebfluxControllerMockTest {

  private final GroupAdminWebfluxControllerMock mock = new GroupAdminWebfluxControllerMock();

  /**
   * Sets up.
   */
  @BeforeEach
  void setUp() {
    GroupRepositoryMock.reset();
  }

  /**
   * Find groups.
   */
  @Test
  void findGroups() {
    final List<Group> groups = GroupRepositoryMock.getGroups();
    StepVerifier
        .create(mock.findGroups())
        .expectNext(groups.toArray(new Group[0]))
        .verifyComplete();
  }

  /**
   * Add group.
   */
  @Test
  void addGroup() {
    final String value = UUID.randomUUID().toString();
    StepVerifier
        .create(mock.addGroup(Group.builder().name(value).build()))
        .assertNext(group -> assertEquals(value, group.getName()))
        .verifyComplete();
  }

  /**
   * Find group by id.
   */
  @Test
  void findGroupById() {
    final Group expected = GroupRepositoryMock.getGroupById(GROUP_0_ID);
    StepVerifier
        .create(mock.findGroupById(GROUP_0_ID))
        .assertNext(group -> assertEquals(expected, group))
        .verifyComplete();
  }

  /**
   * Find group by id and expect error.
   */
  @Test
  void findGroupByIdAndExpectError() {
    StepVerifier
        .create(mock.findGroupById(UUID.randomUUID().toString()))
        .expectError(ServiceException.class)
        .verify();
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
    StepVerifier
        .create(mock.modifyGroup(GROUP_0_ID, newGroup))
        .assertNext(group -> assertEquals(value, newGroup.getName()))
        .verifyComplete();
  }

  /**
   * Modify group and expect error.
   */
  @Test
  void modifyGroupAndExpectError() {
    StepVerifier
        .create(mock.modifyGroup(UUID.randomUUID().toString(), Group.builder().build()))
        .expectError(ServiceException.class)
        .verify();
  }

  /**
   * Remove group.
   */
  @Test
  void removeGroup() {
    StepVerifier
        .create(mock.removeGroup(GROUP_0_ID))
        .verifyComplete();
  }

  /**
   * Find groups by ids.
   */
  @Test
  void findGroupsByIds() {
    final List<Group> groups = GroupRepositoryMock
        .getGroupsByIds(Arrays.asList(GROUP_0_ID, GROUP_1_ID));
    StepVerifier
        .create(mock.findGroupsByIds(Arrays.asList(GROUP_0_ID, GROUP_1_ID)))
        .expectNext(groups.toArray(new Group[0]))
        .verifyComplete();
  }
}