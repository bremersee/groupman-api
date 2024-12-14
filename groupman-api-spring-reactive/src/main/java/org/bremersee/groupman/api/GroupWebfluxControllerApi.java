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
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import org.bremersee.groupman.model.Group;
import org.bremersee.groupman.model.Status;
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
@Tag(name = "group-controller", description = "The group API.")
@Valid
public interface GroupWebfluxControllerApi {

  /**
   * Create group.
   *
   * @param group the group
   * @return the group
   */
  @Operation(
      summary = "Create a new group.",
      operationId = "createGroup",
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The created group.",
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
          responseCode = "409",
          description = "Group already exists",
          content = @Content(
              schema = @Schema(
                  implementation = org.bremersee.exception.model.RestApiException.class)))
  })
  @RequestMapping(
      value = "/api/groups",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  Mono<Group> createGroup(
      @Parameter(description = "The new group.", required = true) @Valid @RequestBody Group group);

  /**
   * Gets group by id.
   *
   * @param id the group id
   * @return the group by id
   */
  @Operation(
      summary = "Get a group.",
      operationId = "getGroupById",
      tags = {"group-controller"})
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
                  implementation = org.bremersee.exception.model.RestApiException.class)))
  })
  @RequestMapping(
      value = "/api/groups/{id}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Group> getGroupById(
      @Parameter(description = "The group ID.", required = true) @PathVariable("id") String id);

  /**
   * Update group.
   *
   * @param id the group id
   * @param group the group
   * @return the group
   */
  @Operation(
      summary = "Update group.",
      operationId = "updateGroup",
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The updated group.",
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
                  implementation = org.bremersee.exception.model.RestApiException.class))),
      @ApiResponse(
          responseCode = "409",
          description = "Version is not up to date",
          content = @Content(
              schema = @Schema(
                  implementation = org.bremersee.exception.model.RestApiException.class)))
  })
  @RequestMapping(
      value = "/api/groups/{id}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  Mono<Group> updateGroup(
      @Parameter(description = "The group ID.", required = true) @PathVariable("id") String id,
      @Parameter(description = "The group.", required = true) @Valid @RequestBody Group group);

  /**
   * Delete group.
   *
   * @param id the group id
   * @return the mono
   */
  @Operation(
      summary = "Delete a group.",
      operationId = "deleteGroup",
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "OK"),
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
      value = "/api/groups/{id}",
      method = RequestMethod.DELETE)
  Mono<Void> deleteGroup(
      @Parameter(description = "The group ID.", required = true) @PathVariable("id") String id);

  /**
   * Gets groups by ids.
   *
   * @param id the list of ids
   * @return the groups by ids
   */
  @Operation(
      summary = "Get groups by id.",
      operationId = "getGroupsByIds",
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The groups.",
          content = @Content(
              array = @ArraySchema(
                  schema = @Schema(implementation = Group.class))))
  })
  @RequestMapping(
      value = "/api/groups/f",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Flux<Group> getGroupsByIds(
      @Parameter(description = "Group IDs")
      @RequestParam(value = "id", required = false) List<String> id);

  /**
   * Gets editable groups.
   *
   * @return the editable groups
   */
  @Operation(
      summary = "Get editable groups.",
      operationId = "getEditableGroups",
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The groups.",
          content = @Content(
              array = @ArraySchema(
                  schema = @Schema(implementation = Group.class))))
  })
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
  @Operation(
      summary = "Get usable groups.",
      operationId = "getUsableGroups",
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The groups.",
          content = @Content(
              array = @ArraySchema(
                  schema = @Schema(implementation = Group.class))))
  })
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
  @Operation(
      summary = "Get membership.",
      operationId = "getMembership",
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The groups.",
          content = @Content(
              array = @ArraySchema(
                  schema = @Schema(implementation = Group.class))))
  })
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
  @Operation(
      summary = "Get membership IDs.",
      operationId = "getMembershipIds",
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The group IDs.",
          content = @Content(
              array = @ArraySchema(
                  schema = @Schema(implementation = String.class))))
  })
  @RequestMapping(
      value = "/api/groups/f/membership-ids",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Set<String>> getMembershipIds();

  /**
   * Get status of the current user.
   *
   * @return the status
   */
  @Operation(
      summary = "Get status of the current user.",
      operationId = "getStatus",
      tags = {"group-controller"})
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "The user status.",
          content = @Content(
              schema = @Schema(
                  implementation = Status.class)))
  })
  @RequestMapping(
      value = "/api/groups/f/status",
      produces = {"application/json"},
      method = RequestMethod.GET)
  Mono<Status> getStatus();

}
