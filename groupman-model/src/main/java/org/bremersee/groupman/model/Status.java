/*
 * Copyright 2020 the original author or authors.
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
import java.io.Serializable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The status of an user.
 *
 * @author Christian Bremer
 */
@Schema(description = "Status of an user.")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Status implements Serializable {

  private static final long serialVersionUID = 1L;

  @Schema(
      description = "The maximum number of owned groups.",
      required = true,
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty("maxOwnedGroups")
  private long maxOwnedGroups = -1;

  @Schema(
      description = "The current size of owned groups.",
      required = true,
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty("ownedGroupSize")
  private long ownedGroupSize = 0;

  @Schema(
      description = "The current membership size.",
      required = true,
      accessMode = AccessMode.READ_ONLY)
  @JsonProperty("membershipSize")
  private long membershipSize = 0;

  /**
   * Instantiates a new status.
   *
   * @param maxOwnedGroups the max owned groups
   * @param ownedGroupSize the owned group size
   * @param membershipSize the membership size
   */
  @Builder(toBuilder = true)
  @SuppressWarnings("unused")
  public Status(long maxOwnedGroups, long ownedGroupSize, long membershipSize) {
    this.maxOwnedGroups = maxOwnedGroups;
    this.ownedGroupSize = ownedGroupSize;
    this.membershipSize = membershipSize;
  }
}
