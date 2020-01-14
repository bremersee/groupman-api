package org.bremersee.groupman.mock;

import static org.bremersee.groupman.mock.GroupRepositoryMock.GROUP_0_ID;
import static org.bremersee.groupman.mock.GroupRepositoryMock.GROUP_0_OWNER;
import static org.bremersee.groupman.mock.GroupRepositoryMock.GROUP_1_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bremersee.exception.ServiceException;
import org.bremersee.groupman.model.Group;
import org.bremersee.groupman.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/**
 * The group webflux controller mock test.
 *
 * @author Christian Bremer
 */
class GroupWebfluxControllerMockTest {

  private static boolean ab;

  private GroupWebfluxControllerMock mock;

  /**
   * Sets up.
   */
  @BeforeEach
  void setUp() {
    GroupRepositoryMock.reset();
    if (ab) {
      mock = new GroupWebfluxControllerMock();
    } else {
      mock = new GroupWebfluxControllerMock(() -> GROUP_0_OWNER);
    }
    ab = !ab;
  }

  /**
   * Create group.
   */
  @Test
  void createGroup() {
    final String value = UUID.randomUUID().toString();
    StepVerifier
        .create(mock.createGroup(Group.builder().name(value).build()))
        .assertNext(group -> assertEquals(value, group.getName()))
        .verifyComplete();
  }

  /**
   * Gets group by id.
   */
  @Test
  void getGroupById() {
    final Group expected = GroupRepositoryMock.getGroupById(GROUP_0_ID);
    StepVerifier
        .create(mock.getGroupById(GROUP_0_ID))
        .assertNext(group -> assertEquals(expected, group))
        .verifyComplete();
  }

  /**
   * Gets group by id and expect error.
   */
  @Test
  void getGroupByIdAndExpectError() {
    StepVerifier
        .create(mock.getGroupById(UUID.randomUUID().toString()))
        .expectError(ServiceException.class)
        .verify();
  }

  /**
   * Update group.
   */
  @Test
  void updateGroup() {
    final String value = UUID.randomUUID().toString();
    final Group newGroup = GroupRepositoryMock.getEditableGroups(GROUP_0_OWNER).stream().findAny()
        .map(g -> g.toBuilder().name(value).build())
        .orElseThrow(() -> new AssertionError("No editable group found."));
    assertNotNull(newGroup);
    StepVerifier
        .create(mock.updateGroup(newGroup.getId(), newGroup))
        .assertNext(group -> assertEquals(value, newGroup.getName()))
        .verifyComplete();
  }

  /**
   * Update group and expect error.
   */
  @Test
  void updateGroupAndExpectError() {
    StepVerifier
        .create(mock.updateGroup(UUID.randomUUID().toString(), Group.builder().build()))
        .expectError(ServiceException.class)
        .verify();
  }

  /**
   * Delete group.
   */
  @Test
  void deleteGroup() {
    final String id = GroupRepositoryMock.getEditableGroups(GROUP_0_OWNER).stream().findAny()
        .map(Group::getId)
        .orElseThrow(() -> new AssertionError("No editable group found."));
    StepVerifier
        .create(mock.deleteGroup(id))
        .verifyComplete();
  }

  /**
   * Gets groups by ids.
   */
  @Test
  void getGroupsByIds() {
    final List<Group> groups = GroupRepositoryMock
        .getGroupsByIds(Arrays.asList(GROUP_0_ID, GROUP_1_ID));
    StepVerifier
        .create(mock.getGroupsByIds(Arrays.asList(GROUP_0_ID, GROUP_1_ID)))
        .expectNext(groups.toArray(new Group[0]))
        .verifyComplete();
  }

  /**
   * Gets editable groups.
   */
  @Test
  void getEditableGroups() {
    final List<Group> groups = GroupRepositoryMock
        .getEditableGroups(GROUP_0_OWNER);
    StepVerifier
        .create(mock.getEditableGroups())
        .expectNext(groups.toArray(new Group[0]))
        .verifyComplete();
  }

  /**
   * Gets usable groups.
   */
  @Test
  void getUsableGroups() {
    final List<Group> groups = GroupRepositoryMock
        .getUsableGroups(GROUP_0_OWNER);
    StepVerifier
        .create(mock.getUsableGroups())
        .expectNext(groups.toArray(new Group[0]))
        .verifyComplete();
  }

  /**
   * Gets membership.
   */
  @Test
  void getMembership() {
    final List<Group> groups = GroupRepositoryMock
        .getMembership(GROUP_0_OWNER);
    StepVerifier
        .create(mock.getMembership())
        .expectNext(groups.toArray(new Group[0]))
        .verifyComplete();
  }

  /**
   * Gets membership ids.
   */
  @Test
  void getMembershipIds() {
    final Set<String> expected = GroupRepositoryMock
        .getMembershipIds(GROUP_0_OWNER);
    StepVerifier
        .create(mock.getMembershipIds())
        .assertNext(actual -> assertEquals(expected, actual))
        .verifyComplete();
  }

  /**
   * Gets status.
   */
  @Test
  void getStatus() {
    Status expected = GroupRepositoryMock.getStatus(GROUP_0_OWNER);
    StepVerifier
        .create(mock.getStatus())
        .assertNext(actual -> assertEquals(expected, actual))
        .verifyComplete();
  }
}