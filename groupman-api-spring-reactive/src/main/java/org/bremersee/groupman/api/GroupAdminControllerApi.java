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
import javax.validation.Valid;
import org.bremersee.groupman.model.Group;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The group admin controller interface.
 *
 * @author Christian Bremer
 */
@SuppressWarnings("unused")
@Validated
public interface GroupAdminControllerApi {

  /**
   * Finds groups.
   *
   * @return the groups
   */
  @RequestMapping(
      value = "/api/admin/groups",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Flux<Group> findGroups();

  /**
   * Adds group.
   *
   * @param group the group
   * @return the group
   */
  @RequestMapping(
      value = "/api/admin/groups",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  Mono<Group> addGroup(@Valid @RequestBody Group group);

  /**
   * Finds group by id.
   *
   * @param groupId the group id
   * @return the group by id
   */
  @RequestMapping(
      value = "/api/admin/groups/{id}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Group> findGroupById(@PathVariable("id") String groupId);

  /**
   * Modifies group.
   *
   * @param groupId the group id
   * @param group   the group
   * @return the group
   */
  @RequestMapping(
      value = "/api/admin/groups/{id}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  Mono<Group> modifyGroup(
      @PathVariable("id") String groupId,
      @Valid @RequestBody Group group);

  /**
   * Removes group.
   *
   * @param groupId the group id
   * @return the mono
   */
  @RequestMapping(
      value = "/api/admin/groups/{id}",
      method = RequestMethod.DELETE)
  Mono<Void> removeGroup(@PathVariable("id") String groupId);

  /**
   * Finds groups by ids.
   *
   * @param ids the ids
   * @return the groups by ids
   */
  @RequestMapping(
      value = "/api/admin/groups/f",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Flux<Group> findGroupsByIds(@RequestParam(value = "id", required = false) List<String> ids);

}