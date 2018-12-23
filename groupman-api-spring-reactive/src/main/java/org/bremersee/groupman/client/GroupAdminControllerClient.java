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

package org.bremersee.groupman.client;

import java.util.List;
import org.bremersee.groupman.api.GroupAdminControllerApi;
import org.bremersee.groupman.model.Group;
import org.bremersee.web.ErrorDetectors;
import org.bremersee.web.reactive.function.client.DefaultWebClientErrorDecoder;
import org.bremersee.web.reactive.function.client.WebClientErrorDecoder;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Christian Bremer
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class GroupAdminControllerClient implements GroupAdminControllerApi {

  private final WebClient webClient;

  private final WebClientErrorDecoder<? extends Throwable> webClientErrorDecoder;

  public GroupAdminControllerClient(final WebClient webClient) {
    this(webClient, null);
  }

  public GroupAdminControllerClient(
      final WebClient webClient,
      final WebClientErrorDecoder<? extends Throwable> webClientErrorDecoder) {
    this.webClient = webClient;
    this.webClientErrorDecoder = webClientErrorDecoder != null
        ? webClientErrorDecoder
        : new DefaultWebClientErrorDecoder();
  }

  @Override
  public Flux<Group> getGroups() {
    return webClient
        .get()
        .uri("/api/admin/group")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToFlux(Group.class);
  }

  @Override
  public Mono<Group> createGroup(final Group group) {
    return webClient
        .post()
        .uri("/api/admin/group")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(group))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(Group.class);
  }

  @Override
  public Mono<Group> getGroupById(final String groupId) {
    return webClient
        .get()
        .uri("/api/admin/group/{id}", groupId)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(Group.class);
  }

  @Override
  public Mono<Group> updateGroup(final String groupId, final Group group) {
    return webClient
        .put()
        .uri("/api/admin/group/{id}", groupId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(group))
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(Group.class);
  }

  @Override
  public Mono<Void> deleteGroup(final String groupId) {
    return webClient
        .delete()
        .uri("/api/admin/group/{id}", groupId)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToMono(Void.class);
  }

  @Override
  public Flux<Group> getGroupsByIds(final List<String> ids) {
    return webClient
        .get()
        .uri("/api/admin/group?id={ids}", ids)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, webClientErrorDecoder)
        .bodyToFlux(Group.class);
  }
}
