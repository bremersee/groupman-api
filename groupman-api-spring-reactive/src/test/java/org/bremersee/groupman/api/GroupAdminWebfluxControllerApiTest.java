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

import static org.bremersee.test.web.RestApiTesterExclusion.exclusionBuilder;
import static org.bremersee.test.web.RestApiTesterPath.PathType.ANNOTATION;
import static org.bremersee.test.web.RestApiTesterPath.PathType.ATTRIBUTE;
import static org.bremersee.test.web.RestApiTesterPath.PathType.CLASS;
import static org.bremersee.test.web.RestApiTesterPath.PathType.METHOD;
import static org.bremersee.test.web.RestApiTesterPath.pathBuilder;

import org.bremersee.test.web.RestApiAssertionType;
import org.bremersee.test.web.RestApiTester;
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
    RestApiTester.assertSameApi(
        GroupAdminControllerApi.class,
        GroupAdminWebfluxControllerApi.class);
  }

  /**
   * Assert same annotations on group controller.
   */
  @Test
  void assertSameAnnotationsOnGroupController() {
    RestApiTester.assertSameApi(
        GroupControllerApi.class,
        GroupWebfluxControllerApi.class,
        exclusionBuilder()
            .path(pathBuilder()
                .add(CLASS, "GroupWebfluxControllerApi")
                .add(METHOD, "getMembershipIds")
                .add(ANNOTATION, "ApiOperation")
                .add(ATTRIBUTE, "response")
                .build())
            .type(RestApiAssertionType.SAME_ANNOTATION_ATTRIBUTE_VALUE)
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
            .type(RestApiAssertionType.SAME_ANNOTATION_ATTRIBUTE_VALUE)
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
            .type(RestApiAssertionType.SAME_ANNOTATION_ATTRIBUTE_VALUE)
            .build()
    );
  }
}