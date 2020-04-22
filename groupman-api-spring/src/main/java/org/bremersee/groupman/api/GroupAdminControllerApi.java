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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The group admin controller api.
 *
 * @author Christian Bremer
 */
@Api(value = "GroupAdminController")
@Validated
public interface GroupAdminControllerApi {

  /**
   * Gets groups.
   *
   * @return the groups
   */
  @ApiOperation(
      value = "Find all groups.",
      nickname = "findGroups",
      response = Group.class,
      responseContainer = "List",
      tags = {"group-admin-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Group.class, responseContainer = "List"),
      @ApiResponse(code = 403, message = "Forbidden")
  })
  @RequestMapping(
      value = "/api/admin/groups",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<Group>> findGroups();


  /**
   * Add group.
   *
   * @param group the group
   * @return the response entity
   */
  @ApiOperation(
      value = "Add a new group.",
      nickname = "addGroup",
      response = Group.class,
      tags = {"group-admin-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Group.class),
      @ApiResponse(code = 400, message = "Bad Request",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 403, message = "Forbidden"),
      @ApiResponse(code = 409, message = "Group already exists",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/admin/groups",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<Group> addGroup(
      @ApiParam(value = "The new group.", required = true) @Valid @RequestBody Group group);


  /**
   * Find group by id.
   *
   * @param id the id
   * @return the response entity
   */
  @ApiOperation(
      value = "Find a group.",
      nickname = "findGroupById",
      response = Group.class,
      tags = {"group-admin-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Group.class),
      @ApiResponse(code = 404, message = "Not Found",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 403, message = "Forbidden")
  })
  @RequestMapping(
      value = "/api/admin/groups/{id}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<Group> findGroupById(
      @ApiParam(value = "The group ID.", required = true) @PathVariable("id") String id);


  /**
   * Modify group.
   *
   * @param id    the id
   * @param group the group
   * @return the response entity
   */
  @ApiOperation(
      value = "Modify group.",
      nickname = "modifyGroup",
      response = Group.class,
      tags = {"group-admin-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Group.class),
      @ApiResponse(code = 400, message = "Bad Request",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 403, message = "Forbidden"),
      @ApiResponse(code = 404, message = "Not Found",
          response = org.bremersee.exception.model.RestApiException.class),
      @ApiResponse(code = 409, message = "Version is not up to date",
          response = org.bremersee.exception.model.RestApiException.class)
  })
  @RequestMapping(
      value = "/api/admin/groups/{id}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  ResponseEntity<Group> modifyGroup(
      @ApiParam(value = "The group ID.", required = true) @PathVariable("id") String id,
      @ApiParam(value = "The modified group.", required = true) @Valid @RequestBody Group group);


  /**
   * Remove group.
   *
   * @param id the id
   * @return the response entity
   */
  @ApiOperation(
      value = "Delete group.",
      nickname = "removeGroup",
      response = Group.class,
      tags = {"group-admin-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Group.class),
      @ApiResponse(code = 403, message = "Forbidden")
  })
  @RequestMapping(
      value = "/api/admin/groups/{id}",
      method = RequestMethod.DELETE)
  ResponseEntity<Group> removeGroup(
      @ApiParam(value = "The group ID.", required = true) @PathVariable("id") String id);


  /**
   * Find groups by ids.
   *
   * @param id the list of ids
   * @return the response entity
   */
  @ApiOperation(
      value = "Find groups by id.",
      nickname = "findGroupsByIds",
      response = Group.class,
      responseContainer = "List",
      tags = {"group-admin-controller"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Group.class, responseContainer = "List"),
      @ApiResponse(code = 403, message = "Forbidden")
  })
  @RequestMapping(
      value = "/api/admin/groups/f",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<Group>> findGroupsByIds(
      @ApiParam(value = "Group IDs") @RequestParam(value = "id", required = false) List<String> id);


}
