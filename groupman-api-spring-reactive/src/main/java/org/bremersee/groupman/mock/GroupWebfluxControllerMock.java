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
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import org.bremersee.exception.ServiceException;
import org.bremersee.groupman.api.GroupWebfluxControllerApi;
import org.bremersee.groupman.model.Group;
import org.bremersee.groupman.model.Status;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The group controller mock.
 *
 * @author Christian Bremer
 */
public class GroupWebfluxControllerMock implements GroupWebfluxControllerApi {

  private final Supplier<String> userNameSupplier;

  /**
   * Instantiates a new group controller mock.
   */
  public GroupWebfluxControllerMock() {
    this(null);
  }

  /**
   * Instantiates a new group controller mock.
   *
   * @param userNameSupplier the user name supplier
   */
  public GroupWebfluxControllerMock(Supplier<String> userNameSupplier) {
    this.userNameSupplier = Objects.requireNonNullElse(
        userNameSupplier,
        () -> GroupRepositoryMock.GROUP_0_OWNER);
  }

  @Override
  public Mono<Group> createGroup(Group group) {
    return Mono.justOrEmpty(GroupRepositoryMock.createGroup(group));
  }

  @Override
  public Mono<Group> getGroupById(String groupId) {
    Group group = GroupRepositoryMock.getGroupById(groupId);
    if (group == null) {
      return Mono.error(ServiceException.forbidden("Group", groupId));
    }
    return Mono.just(group);
  }

  @Override
  public Mono<Group> updateGroup(String groupId, Group group) {
    Group newGroup = GroupRepositoryMock.updateGroup(groupId, group);
    if (newGroup == null) {
      return Mono.error(ServiceException.forbidden("Group", groupId));
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

  @Override
  public Flux<Group> getEditableGroups() {
    return Flux.just(GroupRepositoryMock
        .getEditableGroups(userNameSupplier.get()).toArray(new Group[0]));
  }

  @Override
  public Flux<Group> getUsableGroups() {
    return Flux.just(GroupRepositoryMock
        .getUsableGroups(userNameSupplier.get()).toArray(new Group[0]));
  }

  @Override
  public Flux<Group> getMembership() {
    return Flux.just(GroupRepositoryMock
        .getMembership(userNameSupplier.get()).toArray(new Group[0]));
  }

  @Override
  public Mono<Set<String>> getMembershipIds() {
    Set<String> ids = GroupRepositoryMock.getMembershipIds(userNameSupplier.get());
    return Mono.just(ids);
  }

  @Override
  public Mono<Status> getStatus() {
    return Mono.just(GroupRepositoryMock.getStatus(userNameSupplier.get()));
  }
}
