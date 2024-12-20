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

package org.bremersee.groupman.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The group.
 *
 * @author Christian Bremer
 */
@Schema(description = "The group.")
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Valid
public class Group implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("version")
  private Long version = null;

  @JsonProperty("createdBy")
  private String createdBy = null;

  @JsonProperty("createdAt")
  private OffsetDateTime createdAt = null;

  @JsonProperty("modifiedAt")
  private OffsetDateTime modifiedAt = null;

  @JsonProperty("source")
  private Source source = null;

  @JsonProperty(value = "name", required = true)
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("members")
  private List<String> members = null;

  @JsonProperty("owners")
  private List<String> owners = null;

  /**
   * Instantiates a new group.
   *
   * @param id the id
   * @param version the version
   * @param createdBy the created by
   * @param createdAt the created at
   * @param modifiedAt the modified at
   * @param source the source
   * @param name the name
   * @param description the description
   * @param members the members
   * @param owners the owners
   */
  @Builder(toBuilder = true)
  public Group(String id, Long version, String createdBy, OffsetDateTime createdAt,
      OffsetDateTime modifiedAt, Source source, String name, String description,
      List<String> members, List<String> owners) {
    this.id = id;
    this.version = version;
    this.createdBy = createdBy;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
    this.source = source;
    this.name = name;
    this.description = description;
    this.members = members;
    this.owners = owners;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  @Schema(
      description = "Unique identifier of the group.",
      accessMode = AccessMode.READ_ONLY,
      maxLength = 255)
  @Size(max = 255)
  public String getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets version.
   *
   * @return the version
   */
  @Schema(description = "The database version number.", accessMode = AccessMode.READ_ONLY)
  public Long getVersion() {
    return version;
  }

  /**
   * Sets version.
   *
   * @param version the version
   */
  public void setVersion(Long version) {
    this.version = version;
  }

  /**
   * Gets created by.
   *
   * @return the created by
   */
  @Schema(description = "The user who created the group.", maxLength = 255)
  @Size(max = 255)
  public String getCreatedBy() {
    return createdBy;
  }

  /**
   * Sets created by.
   *
   * @param createdBy the created by
   */
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * Gets created at.
   *
   * @return the created at
   */
  @Schema(description = "The creation time.", accessMode = AccessMode.READ_ONLY)
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets created at.
   *
   * @param createdAt the created at
   */
  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Gets modified at.
   *
   * @return the modified at
   */
  @Schema(description = "The last modification time.", accessMode = AccessMode.READ_ONLY)
  public OffsetDateTime getModifiedAt() {
    return modifiedAt;
  }

  /**
   * Sets modified at.
   *
   * @param modifiedAt the modified at
   */
  public void setModifiedAt(OffsetDateTime modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  /**
   * Gets source.
   *
   * @return the source
   */
  @Schema(description = "The source.", accessMode = AccessMode.READ_ONLY)
  public Source getSource() {
    return source;
  }

  /**
   * Sets source.
   *
   * @param source the source
   */
  public void setSource(Source source) {
    this.source = source;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  @Schema(
      description = "The name of the group.",
      requiredMode = RequiredMode.REQUIRED,
      minLength = 3,
      maxLength = 75)
  @NotNull
  @Size(min = 3, max = 75)
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  @Schema(description = "The description of the group.", maxLength = 255)
  @Size(max = 255)
  public String getDescription() {
    return description;
  }

  /**
   * Sets description.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets members.
   *
   * @return the members
   */
  @Schema(description = "The members of the group.")
  public List<String> getMembers() {
    if (members == null) {
      members = new ArrayList<>();
    }
    return members;
  }

  /**
   * Sets members.
   *
   * @param members the members
   */
  public void setMembers(List<String> members) {
    this.members = members;
  }

  /**
   * Gets owners.
   *
   * @return the owners
   */
  @Schema(description = "The owners of the group.")
  public List<String> getOwners() {
    if (owners == null) {
      owners = new ArrayList<>();
    }
    return owners;
  }

  /**
   * Sets owners.
   *
   * @param owners the owners
   */
  public void setOwners(List<String> owners) {
    this.owners = owners;
  }

}

