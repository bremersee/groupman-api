package org.bremersee.groupman.mock;

import static java.util.Collections.singletonList;
import static org.bremersee.groupman.mock.GroupRepositoryMock.GROUP_0_ID;
import static org.bremersee.groupman.mock.GroupRepositoryMock.GROUP_0_OWNER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bremersee.groupman.model.Group;
import org.bremersee.groupman.model.GroupIdList;
import org.bremersee.groupman.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * The group controller mock test.
 *
 * @author Christian Bremer
 */
class GroupControllerMockTest {

  private static boolean ab;

  private GroupControllerMock mock;

  /**
   * Sets up.
   */
  @BeforeEach
  void setUp() {
    GroupRepositoryMock.reset();
    if (ab) {
      mock = new GroupControllerMock();
    } else {
      mock = new GroupControllerMock(() -> GROUP_0_OWNER);
    }
    ab = !ab;
  }

  /**
   * Create group.
   */
  @Test
  void createGroup() {
    final String value = UUID.randomUUID().toString();
    ResponseEntity<Group> response = mock.createGroup(Group.builder().name(value).build());
    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(value, response.getBody().getName());
  }

  /**
   * Delete group.
   */
  @Test
  void deleteGroup() {
    assertNotNull(mock.deleteGroup(GROUP_0_ID));
  }

  /**
   * Gets editable groups.
   */
  @Test
  void getEditableGroups() {
    List<Group> expected = GroupRepositoryMock.getEditableGroups(GROUP_0_OWNER);
    ResponseEntity<List<Group>> response = mock.getEditableGroups();
    assertNotNull(response);
    assertEquals(expected, response.getBody());
  }

  /**
   * Gets group by id.
   */
  @Test
  void getGroupById() {
    Group expected = GroupRepositoryMock.getGroupById(GROUP_0_ID);
    ResponseEntity<Group> response = mock.getGroupById(GROUP_0_ID);
    assertNotNull(response);
    assertEquals(expected, response.getBody());
  }

  /**
   * Gets group by id and expect error.
   */
  @Test
  void getGroupByIdAndExpectError() {
    ResponseEntity<Group> response = mock.getGroupById(UUID.randomUUID().toString());
    assertNotNull(response);
    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }

  /**
   * Gets groups by ids.
   */
  @Test
  void getGroupsByIds() {
    List<Group> expected = GroupRepositoryMock.getGroupsByIds(singletonList(GROUP_0_ID));
    ResponseEntity<List<Group>> response = mock.getGroupsByIds(singletonList(GROUP_0_ID));
    assertNotNull(response);
    assertEquals(expected, response.getBody());
  }

  /**
   * Gets membership.
   */
  @Test
  void getMembership() {
    List<Group> expected = GroupRepositoryMock.getMembership(GROUP_0_OWNER);
    ResponseEntity<List<Group>> response = mock.getMembership();
    assertNotNull(response);
    assertEquals(expected, response.getBody());
  }

  /**
   * Gets membership ids.
   */
  @Test
  void getMembershipIds() {
    Set<String> expected = GroupRepositoryMock.getMembershipIds(GROUP_0_OWNER);
    ResponseEntity<GroupIdList> response = mock.getMembershipIds();
    assertNotNull(response);
    assertNotNull(response.getBody());
    assertTrue(expected.containsAll(response.getBody()));
  }

  /**
   * Gets status.
   */
  @Test
  void getStatus() {
    Status expected = GroupRepositoryMock.getStatus(GROUP_0_OWNER);
    ResponseEntity<Status> response = mock.getStatus();
    assertNotNull(response);
    assertEquals(expected, response.getBody());
  }

  /**
   * Gets usable groups.
   */
  @Test
  void getUsableGroups() {
    List<Group> expected = GroupRepositoryMock.getUsableGroups(GROUP_0_OWNER);
    ResponseEntity<List<Group>> response = mock.getUsableGroups();
    assertNotNull(response);
    assertEquals(expected, response.getBody());
  }

  /**
   * Update group.
   */
  @Test
  void updateGroup() {
    final String value = UUID.randomUUID().toString();
    final Group newGroup = GroupRepositoryMock.getGroupById(GROUP_0_ID).toBuilder()
        .name(value)
        .build();
    ResponseEntity<Group> response = mock.updateGroup(GROUP_0_ID, newGroup);
    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(value, response.getBody().getName());
  }

  /**
   * Update group and expect error.
   */
  @Test
  void updateGroupAndExpectError() {
    final String value = UUID.randomUUID().toString();
    final Group newGroup = GroupRepositoryMock.getGroupById(GROUP_0_ID).toBuilder()
        .name(value)
        .build();
    ResponseEntity<Group> response = mock.updateGroup(UUID.randomUUID().toString(), newGroup);
    assertNotNull(response);
    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }
}