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
import org.bremersee.exception.ServiceException;
import org.bremersee.groupman.api.GroupAdminControllerApi;
import org.bremersee.groupman.model.Group;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The group admin controller mock.
 *
 * @author Christian Bremer
 */
@SuppressWarnings("unused")
public class GroupAdminControllerMock implements GroupAdminControllerApi {

  @Override
  public Flux<Group> getGroups() {
    return Flux.just(GroupRepositoryMock.getGroups().toArray(new Group[0]));
  }

  @Override
  public Mono<Group> createGroup(Group group) {
    return Mono.justOrEmpty(GroupRepositoryMock.createGroup(group));
  }

  @Override
  public Mono<Group> getGroupById(String groupId) {
    Group group = GroupRepositoryMock.getGroupById(groupId);
    if (group == null) {
      throw ServiceException.notFound("Group", groupId);
    }
    return Mono.just(group);
  }

  @Override
  public Mono<Group> updateGroup(String groupId, Group group) {
    Group newGroup = GroupRepositoryMock.updateGroup(groupId, group);
    if (newGroup == null) {
      throw ServiceException.notFound("Group", groupId);
    }
    return Mono.just(newGroup);
  }

  @Override
  public Mono<Void> deleteGroup(String groupId) {
    GroupRepositoryMock.deleteGroup(groupId);
    return Mono.empty();
  }

  @Override
  public Flux<Group> getGroupsByIds(List<String> ids) {
    return Flux.just(GroupRepositoryMock.getGroupsByIds(ids).toArray(new Group[0]));
  }
}
