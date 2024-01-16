package com.godigital.tasking.person.domain;

import reactor.core.publisher.Mono;

public interface DeletePersonUseCase {
  Mono<Void> execute(String id);
}
