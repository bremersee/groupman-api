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

import java.util.List;
import org.bremersee.groupman.api.GroupAdminControllerApi;
import org.bremersee.groupman.model.Group;
import org.springframework.http.ResponseEntity;

/**
 * The group admin controller mock.
 *
 * @author Christian Bremer
 */
public class GroupAdminControllerMock implements GroupAdminControllerApi {

  @Override
  public ResponseEntity<Group> addGroup(Group group) {
    return ResponseEntity.ok(GroupRepositoryMock.createGroup(group));
  }

  @Override
  public ResponseEntity<Group> removeGroup(String id) {
    GroupRepositoryMock.deleteGroup(id);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Group> findGroupById(String id) {
    Group group = GroupRepositoryMock.getGroupById(id);
    if (group == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(group);
  }

  @Override
  public ResponseEntity<List<Group>> findGroups() {
    return ResponseEntity.ok(GroupRepositoryMock.getGroups());
  }

  @Override
  public ResponseEntity<List<Group>> findGroupsByIds(List<String> ids) {
    return ResponseEntity.ok(GroupRepositoryMock.getGroupsByIds(ids));
  }

  @Override
  public ResponseEntity<Group> modifyGroup(String id, Group group) {
    Group newGroup = GroupRepositoryMock.updateGroup(id, group);
    if (newGroup == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(newGroup);
  }
}
