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
import java.util.Set;
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
 * The group controller interface.
 *
 * @author Christian Bremer
 */
@SuppressWarnings("unused")
@Validated
public interface GroupControllerApi {

  /**
   * Create group.
   *
   * @param group the group
   * @return the group
   */
  @RequestMapping(
      value = "/api/groups",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  Mono<Group> createGroup(@Valid @RequestBody Group group);

  /**
   * Gets group by id.
   *
   * @param groupId the group id
   * @return the group by id
   */
  @RequestMapping(
      value = "/api/groups/{id}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Group> getGroupById(@PathVariable(value = "id") String groupId);

  /**
   * Update group.
   *
   * @param groupId the group id
   * @param group   the group
   * @return the group
   */
  @RequestMapping(
      value = "/api/groups/{id}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  Mono<Group> updateGroup(
      @PathVariable("id") String groupId,
      @Valid @RequestBody Group group);

  /**
   * Delete group.
   *
   * @param groupId the group id
   * @return the mono
   */
  @RequestMapping(
      value = "/api/groups/{id}",
      method = RequestMethod.DELETE)
  Mono<Void> deleteGroup(@PathVariable("id") String groupId);

  /**
   * Gets groups by ids.
   *
   * @param ids the ids
   * @return the groups by ids
   */
  @RequestMapping(
      value = "/api/groups/f",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Flux<Group> getGroupsByIds(@RequestParam(value = "id", required = false) List<String> ids);

  /**
   * Gets editable groups.
   *
   * @return the editable groups
   */
  @RequestMapping(
      value = "/api/groups/f/editable",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Flux<Group> getEditableGroups();

  /**
   * Gets usable groups.
   *
   * @return the usable groups
   */
  @RequestMapping(
      value = "/api/groups/f/usable",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Flux<Group> getUsableGroups();

  /**
   * Gets membership.
   *
   * @return the membership
   */
  @RequestMapping(
      value = "/api/groups/f/membership",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Flux<Group> getMembership();

  /**
   * Gets membership ids.
   *
   * @return the membership ids
   */
  @RequestMapping(
      value = "/api/groups/f/membership-ids",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Set<String>> getMembershipIds();

}
