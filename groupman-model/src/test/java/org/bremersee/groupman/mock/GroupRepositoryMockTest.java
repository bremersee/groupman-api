package org.bremersee.groupman.mock;

import static org.bremersee.groupman.mock.GroupRepositoryMock.GROUP_0_ID;
import static org.bremersee.groupman.mock.GroupRepositoryMock.GROUP_0_MEMBER_0;
import static org.bremersee.groupman.mock.GroupRepositoryMock.GROUP_0_OWNER;
import static org.bremersee.groupman.mock.GroupRepositoryMock.createGroup;
import static org.bremersee.groupman.mock.GroupRepositoryMock.deleteGroup;
import static org.bremersee.groupman.mock.GroupRepositoryMock.getEditableGroups;
import static org.bremersee.groupman.mock.GroupRepositoryMock.getGroupById;
import static org.bremersee.groupman.mock.GroupRepositoryMock.getGroups;
import static org.bremersee.groupman.mock.GroupRepositoryMock.getGroupsByIds;
import static org.bremersee.groupman.mock.GroupRepositoryMock.getMembership;
import static org.bremersee.groupman.mock.GroupRepositoryMock.getMembershipIds;
import static org.bremersee.groupman.mock.GroupRepositoryMock.getStatus;
import static org.bremersee.groupman.mock.GroupRepositoryMock.getUsableGroups;
import static org.bremersee.groupman.mock.GroupRepositoryMock.reset;
import static org.bremersee.groupman.mock.GroupRepositoryMock.updateGroup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.UUID;
import org.bremersee.groupman.model.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The group repository mock test.
 *
 * @author Christian Bremer
 */
class GroupRepositoryMockTest {

  /**
   * Reset db.
   */
  @BeforeEach
  void resetDb() {
    reset();
  }

  /**
   * Test reset.
   */
  @Test
  void testReset() {
    int expected = getGroups().size();
    createGroup(Group.builder().build());
    int actual = getGroups().size();
    assertTrue(actual > expected);
    reset();
    actual = getGroups().size();
    assertEquals(expected, actual);
  }

  /**
   * Test create group.
   */
  @Test
  void testCreateGroup() {
    assertThrows(IllegalArgumentException.class, () -> createGroup(null));
    Group expected = createGroup(Group.builder().name("value").build());
    assertNotNull(expected.getId());
    assertEquals("value", expected.getName());
  }

  /**
   * Test get groups.
   */
  @Test
  void testGetGroups() {
    assertNotNull(getGroups());
  }

  /**
   * Test get group by id.
   */
  @Test
  void testGetGroupById() {
    assertNull(getGroupById(UUID.randomUUID().toString()));
    assertNotNull(getGroupById(GROUP_0_ID));
  }

  /**
   * Test update group.
   */
  @Test
  void testUpdateGroup() {
    assertThrows(IllegalArgumentException.class, () -> updateGroup(null, Group.builder().build()));
    assertThrows(IllegalArgumentException.class, () -> updateGroup("123", null));
    assertNull(updateGroup(UUID.randomUUID().toString(), Group.builder().build()));
    Group model = updateGroup(GROUP_0_ID, Group.builder().name("newGroupName").build());
    assertNotNull(model);
    assertEquals("newGroupName", model.getName());
  }

  /**
   * Test delete group.
   */
  @Test
  void testDeleteGroup() {
    deleteGroup(UUID.randomUUID().toString());
  }

  /**
   * Test get groups by ids.
   */
  @Test
  void testGetGroupsByIds() {
    assertTrue(getGroupsByIds(null).isEmpty());
    assertTrue(getGroupsByIds(Collections.singletonList(GROUP_0_ID)).stream()
        .anyMatch(group -> GROUP_0_ID.equals(group.getId())));
  }

  /**
   * Test get editable groups.
   */
  @Test
  void testGetEditableGroups() {
    assertThrows(IllegalArgumentException.class, () -> getEditableGroups(null));
    assertFalse(getEditableGroups(GROUP_0_OWNER).isEmpty());
  }

  /**
   * Test get usable groups.
   */
  @Test
  void testGetUsableGroups() {
    assertThrows(IllegalArgumentException.class, () -> getUsableGroups(null));
    assertFalse(getUsableGroups(GROUP_0_OWNER).isEmpty());
  }

  /**
   * Test get membership.
   */
  @Test
  void testGetMembership() {
    assertThrows(IllegalArgumentException.class, () -> getMembership(null));
    assertFalse(getMembership(GROUP_0_MEMBER_0).isEmpty());
  }

  /**
   * Test get membership ids.
   */
  @Test
  void testGetMembershipIds() {
    assertThrows(IllegalArgumentException.class, () -> getMembershipIds(null));
    assertFalse(getMembershipIds(GROUP_0_MEMBER_0).isEmpty());
  }

  /**
   * Test get status.
   */
  @Test
  void testGetStatus() {
    assertThrows(IllegalArgumentException.class, () -> getStatus(null));
    assertNotNull(getStatus(GROUP_0_OWNER));
  }
}