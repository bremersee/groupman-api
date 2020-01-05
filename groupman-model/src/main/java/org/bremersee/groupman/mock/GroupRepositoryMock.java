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

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.bremersee.groupman.model.Group;
import org.bremersee.groupman.model.Source;
import org.bremersee.groupman.model.Status;

/**
 * The group repository mock.
 *
 * @author Christian Bremer
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class GroupRepositoryMock {

  /**
   * The constant GROUP_0_ID.
   */
  public static final String GROUP_0_ID = "0571f182-048c-4351-9461-a91e945e4c1f";

  /**
   * The constant GROUP_0_OWNER.
   */
  public static final String GROUP_0_OWNER = "plato";

  /**
   * The constant GROUP_0_MEMBER_0.
   */
  public static final String GROUP_0_MEMBER_0 = "sokrates";

  /**
   * The constant GROUP_0_MEMBER_1.
   */
  public static final String GROUP_0_MEMBER_1 = "aristoteles";

  /**
   * The constant GROUP_1_ID.
   */
  public static final String GROUP_1_ID = "0d742bc4-266a-441d-9766-faf67aff918e";

  /**
   * The constant GROUP_1_OWNER.
   */
  public static final String GROUP_1_OWNER = "kant";

  /**
   * The constant GROUP_1_MEMBER_0.
   */
  public static final String GROUP_1_MEMBER_0 = "schopenhauer";

  /**
   * The constant GROUP_1_MEMBER_1.
   */
  public static final String GROUP_1_MEMBER_1 = "kierkegaard";

  private static final Map<String, Group> DB = new ConcurrentHashMap<>();

  static {
    Group group0 = new Group();
    group0.setCreatedAt(OffsetDateTime.now());
    group0.setCreatedBy(GROUP_0_OWNER);
    group0.setDescription("Old greek philosophers group");
    group0.setId(GROUP_0_ID);
    group0.setMembers(Arrays.asList(GROUP_0_OWNER, GROUP_0_MEMBER_0, GROUP_0_MEMBER_1));
    group0.setModifiedAt(OffsetDateTime.now());
    group0.setName("Old greek philosophers");
    group0.setOwners(Collections.singletonList(GROUP_0_OWNER));
    group0.setSource(Source.INTERNAL);
    group0.setVersion(1L);
    DB.put(group0.getId(), group0);

    Group group1 = new Group();
    group1.setCreatedAt(OffsetDateTime.now());
    group1.setCreatedBy(GROUP_1_OWNER);
    group1.setDescription("Kant group");
    group1.setId(GROUP_1_ID);
    group1.setMembers(
        Arrays.asList(GROUP_1_OWNER, GROUP_1_MEMBER_0, GROUP_1_MEMBER_1, GROUP_0_MEMBER_1));
    group1.setModifiedAt(OffsetDateTime.now());
    group1.setName("Kant and 'friends'");
    group1.setOwners(Collections.singletonList(GROUP_1_OWNER));
    group1.setSource(Source.INTERNAL);
    group1.setVersion(1L);
    DB.put(group1.getId(), group1);
  }

  /**
   * Create group.
   *
   * @param group the group
   * @return the group
   */
  public static Group createGroup(Group group) {
    if (group == null) {
      throw new IllegalArgumentException("Illegal group");
    }
    if (group.getId() == null) {
      group.setId(UUID.randomUUID().toString());
    }
    DB.put(group.getId(), group);
    return group;
  }

  /**
   * Gets groups.
   *
   * @return the groups
   */
  public static List<Group> getGroups() {
    return new ArrayList<>(DB.values());
  }

  /**
   * Gets group by id.
   *
   * @param groupId the group id
   * @return the group by id
   */
  public static Group getGroupById(String groupId) {
    return DB.get(groupId);
  }

  /**
   * Update group.
   *
   * @param groupId the group id
   * @param group   the group
   * @return the group
   */
  public static Group updateGroup(String groupId, Group group) {
    if (groupId == null) {
      throw new IllegalArgumentException("Illegal group ID");
    }
    if (group == null) {
      throw new IllegalArgumentException("Illegal group");
    }
    if (!DB.containsKey(groupId)) {
      return null;
    }
    group.setId(groupId);
    DB.put(groupId, group);
    return group;
  }

  /**
   * Delete group.
   *
   * @param groupId the group id
   */
  public static void deleteGroup(String groupId) {
    DB.remove(groupId);
  }

  /**
   * Gets groups by ids.
   *
   * @param ids the ids
   * @return the groups by ids
   */
  public static List<Group> getGroupsByIds(List<String> ids) {
    List<Group> list = new ArrayList<>();
    if (ids != null) {
      for (String id : ids) {
        Group group = DB.get(id);
        if (group != null) {
          list.add(group);
        }
      }
    }
    return list;
  }

  /**
   * Gets editable groups.
   *
   * @param userName the user name
   * @return the editable groups
   */
  public static List<Group> getEditableGroups(String userName) {
    if (userName == null) {
      throw new IllegalArgumentException("User name must be present.");
    }
    List<Group> list = new ArrayList<>();
    for (Group group : DB.values()) {
      if (userName.equals(group.getCreatedBy())
          || (group.getOwners() != null && group.getOwners().contains(userName))) {
        list.add(group);
      }
    }
    return list;
  }

  /**
   * Gets usable groups.
   *
   * @param userName the user name
   * @return the usable groups
   */
  public static List<Group> getUsableGroups(String userName) {
    if (userName == null) {
      throw new IllegalArgumentException("User name must be present.");
    }
    Set<Group> set = new HashSet<>(getEditableGroups(userName));
    for (Group group : DB.values()) {
      if (group.getMembers() != null && group.getMembers().contains(userName)) {
        set.add(group);
      }
    }
    return new ArrayList<>(set);
  }

  /**
   * Gets membership.
   *
   * @param userName the user name
   * @return the membership
   */
  public static List<Group> getMembership(String userName) {
    if (userName == null) {
      throw new IllegalArgumentException("User name must be present.");
    }
    List<Group> list = new ArrayList<>();
    for (Group group : DB.values()) {
      if (group.getMembers() != null && group.getMembers().contains(userName)) {
        list.add(group);
      }
    }
    return list;
  }

  /**
   * Gets membership ids.
   *
   * @param userName the user name
   * @return the membership ids
   */
  public static Set<String> getMembershipIds(String userName) {
    return getMembership(userName).stream().map(Group::getId).collect(Collectors.toSet());
  }

  /**
   * Gets status.
   *
   * @param userName the user name
   * @return the status
   */
  public static Status getStatus(String userName) {
    if (userName == null) {
      throw new IllegalArgumentException("User name must be present.");
    }
    return Status.builder()
        .membershipSize(getMembership(userName).size())
        .ownedGroupSize(getEditableGroups(userName).size())
        .build();
  }
}
