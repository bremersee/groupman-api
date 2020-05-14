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
import org.bremersee.exception.ServiceException;
import org.bremersee.groupman.api.GroupAdminWebfluxControllerApi;
import org.bremersee.groupman.model.Group;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The group admin controller mock.
 *
 * @author Christian Bremer
 */
public class GroupAdminWebfluxControllerMock implements GroupAdminWebfluxControllerApi {

  @Override
  public Flux<Group> findGroups() {
    return Flux.just(GroupRepositoryMock.getGroups().toArray(new Group[0]));
  }

  @Override
  public Mono<Group> addGroup(Group group) {
    return Mono.justOrEmpty(GroupRepositoryMock.createGroup(group));
  }

  @Override
  public Mono<Group> findGroupById(String groupId) {
    Group group = GroupRepositoryMock.getGroupById(groupId);
    if (group == null) {
      return Mono.error(ServiceException.notFound("Group", groupId));
    }
    return Mono.just(group);
  }

  @Override
  public Mono<Group> modifyGroup(String groupId, Group group) {
    Group newGroup = GroupRepositoryMock.updateGroup(groupId, group);
    if (newGroup == null) {
      return Mono.error(ServiceException.notFound("Group", groupId));
    }
    return Mono.just(newGroup);
  }

  @Override
  public Mono<Void> removeGroup(String groupId) {
    GroupRepositoryMock.deleteGroup(groupId);
    return Mono.empty();
  }

  @Override
  public Flux<Group> findGroupsByIds(List<String> ids) {
    return Flux.just(GroupRepositoryMock.getGroupsByIds(ids).toArray(new Group[0]));
  }

}
