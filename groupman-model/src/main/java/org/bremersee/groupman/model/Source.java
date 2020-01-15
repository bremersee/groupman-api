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

package org.bremersee.groupman.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The source of a group. Only administrators can use other values than 'INTERNAL'.
 *
 * @author Christian Bremer
 */
@SuppressWarnings("unused")
public enum Source {

  /**
   * Internal source.
   */
  INTERNAL("INTERNAL"),

  /**
   * Ldap source.
   */
  LDAP("LDAP");

  private String value;

  Source(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  /**
   * From value source.
   *
   * @param text the text
   * @return the source
   */
  @JsonCreator
  public static Source fromValue(String text) {
    for (Source b : Source.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

