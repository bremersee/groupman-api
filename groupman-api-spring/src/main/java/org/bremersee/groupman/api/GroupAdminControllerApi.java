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

package org.bremersee.groupman.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "group-admin-controller", description = "The group admin API.")
@Validated
public interface GroupAdminControllerApi {

  /**
   * Gets groups.
   *
   * @return the groups
   */
  @Operation(
      summary = "Find all groups.",
      operationId = "findGroups",
      tags = {"group-admin-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The groups.",
          content = @Content(
              array = @ArraySchema(
                  schema = @Schema(implementation = Group.class)))),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden")
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
  @Operation(
      summary = "Add a new group.",
      operationId = "addGroup",
      tags = {"group-admin-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The added group.",
          content = @Content(
              schema = @Schema(
                  implementation = Group.class))),
      @ApiResponse(
          responseCode = "400",
          description = "Bad Request",
          content = @Content(
              schema = @Schema(
                  implementation = org.bremersee.exception.model.RestApiException.class))),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden"),
      @ApiResponse(
          responseCode = "409",
          description = "Group already exists",
          content = @Content(
              schema = @Schema(
                  implementation = org.bremersee.exception.model.RestApiException.class)))
  })
  @RequestMapping(
      value = "/api/admin/groups",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<Group> addGroup(
      @Parameter(description = "The new group.", required = true) @Valid @RequestBody Group group);


  /**
   * Find group by id.
   *
   * @param id the id
   * @return the response entity
   */
  @Operation(
      summary = "Find a group.",
      operationId = "findGroupById",
      tags = {"group-admin-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The group.",
          content = @Content(
              schema = @Schema(
                  implementation = Group.class))),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found",
          content = @Content(
              schema = @Schema(
                  implementation = org.bremersee.exception.model.RestApiException.class))),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden")
  })
  @RequestMapping(
      value = "/api/admin/groups/{id}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<Group> findGroupById(
      @Parameter(description = "The group ID.", required = true) @PathVariable("id") String id);


  /**
   * Modify group.
   *
   * @param id the id
   * @param group the group
   * @return the response entity
   */
  @Operation(
      summary = "Modify group.",
      operationId = "modifyGroup",
      tags = {"group-admin-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The modified group.",
          content = @Content(
              schema = @Schema(
                  implementation = Group.class))),
      @ApiResponse(
          responseCode = "400",
          description = "Bad Request",
          content = @Content(
              schema = @Schema(
                  implementation = org.bremersee.exception.model.RestApiException.class))),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden"),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found",
          content = @Content(
              schema = @Schema(
                  implementation = org.bremersee.exception.model.RestApiException.class)))
  })
  @RequestMapping(
      value = "/api/admin/groups/{id}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  ResponseEntity<Group> modifyGroup(
      @Parameter(description = "The group ID.", required = true) @PathVariable("id") String id,
      @Parameter(description = "The group.", required = true) @Valid @RequestBody Group group);


  /**
   * Remove group.
   *
   * @param id the id
   * @return the response entity
   */
  @Operation(
      summary = "Remove a group.",
      operationId = "removeGroup",
      tags = {"group-admin-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "OK"),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden")
  })
  @RequestMapping(
      value = "/api/admin/groups/{id}",
      method = RequestMethod.DELETE)
  ResponseEntity<Group> removeGroup(
      @Parameter(description = "The group ID.", required = true) @PathVariable("id") String id);


  /**
   * Find groups by ids.
   *
   * @param id the list of ids
   * @return the response entity
   */
  @Operation(
      summary = "Find groups by id.",
      operationId = "findGroupsByIds",
      tags = {"group-admin-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The groups.",
          content = @Content(
              array = @ArraySchema(
                  schema = @Schema(implementation = Group.class)))),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden")
  })
  @RequestMapping(
      value = "/api/admin/groups/f",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<Group>> findGroupsByIds(
      @Parameter(description = "Group IDs")
      @RequestParam(value = "id", required = false) List<String> id);


}
