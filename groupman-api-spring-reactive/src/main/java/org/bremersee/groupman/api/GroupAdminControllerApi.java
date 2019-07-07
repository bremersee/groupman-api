/*
 * Copyright 2018 the original author or authors.
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

package org.bremersee.groupman.api;

import java.util.List;
import org.bremersee.groupman.model.Group;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The group admin controller interface.
 *
 * @author Christian Bremer
 */
@SuppressWarnings("unused")
public interface GroupAdminControllerApi {

  /**
   * Finds groups.
   *
   * @return the groups
   */
  Flux<Group> findGroups();

  /**
   * Adds group.
   *
   * @param group the group
   * @return the group
   */
  Mono<Group> addGroup(Group group);

  /**
   * Finds group by id.
   *
   * @param groupId the group id
   * @return the group by id
   */
  Mono<Group> findGroupById(String groupId);

  /**
   * Modifies group.
   *
   * @param groupId the group id
   * @param group   the group
   * @return the group
   */
  Mono<Group> modifyGroup(String groupId, Group group);

  /**
   * Removes group.
   *
   * @param groupId the group id
   * @return the mono
   */
  Mono<Void> removeGroup(String groupId);

  /**
   * Finds groups by ids.
   *
   * @param ids the ids
   * @return the groups by ids
   */
  Flux<Group> findGroupsByIds(List<String> ids);

}