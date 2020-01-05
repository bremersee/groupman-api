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

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import javax.validation.Valid;
import org.bremersee.groupman.api.GroupControllerApi;
import org.bremersee.groupman.model.Group;
import org.bremersee.groupman.model.GroupIdList;
import org.bremersee.groupman.model.Status;
import org.springframework.http.ResponseEntity;

/**
 * The group controller mock.
 *
 * @author Christian Bremer
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class GroupControllerMock implements GroupControllerApi {

  private final Supplier<String> userNameSupplier;

  /**
   * Instantiates a new group controller mock.
   */
  public GroupControllerMock() {
    this(null);
  }

  /**
   * Instantiates a new group controller mock.
   *
   * @param userNameSupplier the user name supplier
   */
  public GroupControllerMock(Supplier<String> userNameSupplier) {
    if (userNameSupplier != null) {
      this.userNameSupplier = userNameSupplier;
    } else {
      this.userNameSupplier = () -> GroupRepositoryMock.GROUP_0_OWNER;
    }
  }

  @Override
  public ResponseEntity<Group> createGroup(@Valid Group group) {
    return ResponseEntity.ok(GroupRepositoryMock.createGroup(group));
  }

  @Override
  public ResponseEntity<Group> deleteGroup(String id) {
    GroupRepositoryMock.deleteGroup(id);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<List<Group>> getEditableGroups() {
    return ResponseEntity.ok(GroupRepositoryMock
        .getEditableGroups(userNameSupplier.get()));
  }

  @Override
  public ResponseEntity<Group> getGroupById(String id) {
    Group group = GroupRepositoryMock.getGroupById(id);
    if (group == null) {
      throw new ForbiddenException();
    }
    return ResponseEntity.ok(group);
  }

  @Override
  public ResponseEntity<List<Group>> getGroupsByIds(List<String> ids) {
    return ResponseEntity.ok(GroupRepositoryMock.getGroupsByIds(ids));
  }

  @Override
  public ResponseEntity<List<Group>> getMembership() {
    return ResponseEntity.ok(GroupRepositoryMock
        .getMembership(userNameSupplier.get()));
  }

  @Override
  public ResponseEntity<GroupIdList> getMembershipIds() {
    Set<String> ids = GroupRepositoryMock.getMembershipIds(userNameSupplier.get());
    GroupIdList list = new GroupIdList();
    list.addAll(ids);
    return ResponseEntity.ok(list);
  }

  @Override
  public ResponseEntity<Status> getStatus() {
    return ResponseEntity.ok(GroupRepositoryMock.getStatus(userNameSupplier.get()));
  }

  @Override
  public ResponseEntity<List<Group>> getUsableGroups() {
    return ResponseEntity.ok(GroupRepositoryMock
        .getUsableGroups(userNameSupplier.get()));
  }

  @Override
  public ResponseEntity<Group> updateGroup(String id, @Valid Group group) {
    Group newGroup = GroupRepositoryMock.updateGroup(id, group);
    if (newGroup == null) {
      throw new ForbiddenException();
    }
    return ResponseEntity.ok(newGroup);
  }

  @Override
  public ResponseEntity<Group> patchGroup(String id, @Valid Group group) {
    Group newGroup = GroupRepositoryMock.updateGroup(id, group);
    if (newGroup == null) {
      throw new ForbiddenException();
    }
    return ResponseEntity.ok(newGroup);
  }
}
