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

import static org.bremersee.spring.test.api.comparator.RestApiComparatorExclusion.exclusionBuilder;
import static org.bremersee.spring.test.api.comparator.RestApiComparatorPath.PathType.ANNOTATION;
import static org.bremersee.spring.test.api.comparator.RestApiComparatorPath.PathType.ATTRIBUTE;
import static org.bremersee.spring.test.api.comparator.RestApiComparatorPath.PathType.CLASS;
import static org.bremersee.spring.test.api.comparator.RestApiComparatorPath.PathType.METHOD;
import static org.bremersee.spring.test.api.comparator.RestApiComparatorPath.pathBuilder;

import org.bremersee.spring.test.api.comparator.RestApiComparator;
import org.bremersee.spring.test.api.comparator.RestApiComparatorAssertionType;
import org.junit.jupiter.api.Test;

/**
 * The group admin controller api test.
 *
 * @author Christian Bremer
 */
class GroupAdminWebfluxControllerApiTest {

  /**
   * Assert same annotations on group admin controller.
   */
  @Test
  void assertSameAnnotationsOnGroupAdminController() {
    RestApiComparator.assertSameApi(
        GroupAdminControllerApi.class,
        GroupAdminWebfluxControllerApi.class);
  }

  /**
   * Assert same annotations on group controller.
   */
  @Test
  void assertSameAnnotationsOnGroupController() {
    RestApiComparator.assertSameApi(
        GroupControllerApi.class,
        GroupWebfluxControllerApi.class,
        exclusionBuilder()
            .path(pathBuilder()
                .add(CLASS, "GroupWebfluxControllerApi")
                .add(METHOD, "getMembershipIds")
                .add(ANNOTATION, "ApiOperation")
                .add(ATTRIBUTE, "response")
                .build())
            .type(RestApiComparatorAssertionType.SAME_ANNOTATION_ATTRIBUTE_VALUE)
            .build(),
        exclusionBuilder()
            .path(pathBuilder()
                .add(CLASS, "GroupWebfluxControllerApi")
                .add(METHOD, "getMembershipIds")
                .add(ANNOTATION, "ApiResponses")
                .add(ATTRIBUTE, "value")
                .add(ANNOTATION, "ApiResponse")
                .add(ATTRIBUTE, "response")
                .build())
            .type(RestApiComparatorAssertionType.SAME_ANNOTATION_ATTRIBUTE_VALUE)
            .build(),
        exclusionBuilder()
            .path(pathBuilder()
                .add(CLASS, "GroupWebfluxControllerApi")
                .add(METHOD, "getMembershipIds")
                .add(ANNOTATION, "ApiResponses")
                .add(ATTRIBUTE, "value")
                .add(ANNOTATION, "ApiResponse")
                .add(ATTRIBUTE, "responseContainer")
                .build())
            .type(RestApiComparatorAssertionType.SAME_ANNOTATION_ATTRIBUTE_VALUE)
            .build()
    );
  }
}