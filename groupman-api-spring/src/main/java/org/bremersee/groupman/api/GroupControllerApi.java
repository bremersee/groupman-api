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

package org.bremersee.groupman.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.bremersee.groupman.model.Group;
import org.bremersee.groupman.model.GroupIdList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The group controller api.
 */
@Api(value = "GroupController")
public interface GroupControllerApi {

  /**
   * Create group.
   *
   * @param group the group
   * @return the response entity
   */
  @ApiOperation(
      value = "Create a new group.",
      nickname = "createGroup",
      response = Group.class,
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Group.class)
  })
  @RequestMapping(
      value = "/api/groups",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<Group> createGroup(
      @ApiParam(value = "The new group.", required = true) @Valid @RequestBody Group group);


  /**
   * Delete group.
   *
   * @param id the id
   * @return the response entity
   */
  @ApiOperation(
      value = "Delete group.",
      nickname = "deleteGroup",
      response = Group.class,
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Group.class),
      @ApiResponse(code = 403, message = "Forbidden",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/groups/{id}",
      method = RequestMethod.DELETE)
  ResponseEntity<Group> deleteGroup(
      @ApiParam(value = "The group ID.", required = true) @PathVariable("id") String id);


  /**
   * Gets editable groups.
   *
   * @return the editable groups
   */
  @ApiOperation(
      value = "Get editable groups.",
      nickname = "getEditableGroups",
      response = Group.class,
      responseContainer = "List",
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Group.class, responseContainer = "List")
  })
  @RequestMapping(
      value = "/api/groups/f/editable",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<Group>> getEditableGroups();


  /**
   * Gets group by id.
   *
   * @param id the id
   * @return the group by id
   */
  @ApiOperation(
      value = "Get a group.",
      nickname = "getGroupById",
      response = Group.class,
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Group.class),
      @ApiResponse(code = 404, message = "Not Found",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/groups/{id}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<Group> getGroupById(
      @ApiParam(value = "The group ID.", required = true) @PathVariable("id") String id);


  /**
   * Gets groups by ids.
   *
   * @param id the id
   * @return the groups by ids
   */
  @ApiOperation(
      value = "Get groups by id.",
      nickname = "getGroupsByIds",
      response = Group.class,
      responseContainer = "List",
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Group.class, responseContainer = "List")
  })
  @RequestMapping(
      value = "/api/groups/f",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<Group>> getGroupsByIds(
      @ApiParam(value = "Group IDs") @RequestParam(value = "id", required = false) List<String> id);


  /**
   * Gets membership.
   *
   * @return the membership
   */
  @ApiOperation(
      value = "Get membership.",
      nickname = "getMembership",
      response = Group.class,
      responseContainer = "List",
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Group.class, responseContainer = "List")
  })
  @RequestMapping(
      value = "/api/groups/f/membership",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<Group>> getMembership();


  /**
   * Gets membership ids.
   *
   * @return the membership ids
   */
  @ApiOperation(
      value = "Get membership IDs.",
      nickname = "getMembershipIds",
      response = GroupIdList.class,
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = GroupIdList.class)
  })
  @RequestMapping(
      value = "/api/groups/f/membership-ids",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<GroupIdList> getMembershipIds();


  /**
   * Gets usable groups.
   *
   * @return the usable groups
   */
  @ApiOperation(
      value = "Get usable groups.",
      nickname = "getUsableGroups",
      response = Group.class,
      responseContainer = "List",
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Group.class, responseContainer = "List")
  })
  @RequestMapping(
      value = "/api/groups/f/usable",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<Group>> getUsableGroups();


  /**
   * Update group.
   *
   * @param id    the id
   * @param group the group
   * @return the response entity
   */
  @ApiOperation(
      value = "Update group.",
      nickname = "updateGroup",
      response = Group.class,
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Group.class),
      @ApiResponse(code = 403, message = "Forbidden",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 404, message = "Not Found",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/groups/{id}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  ResponseEntity<Group> updateGroup(
      @ApiParam(value = "The group ID.", required = true) @PathVariable("id") String id,
      @ApiParam(value = "The update data.", required = true) @Valid @RequestBody Group group);

}
