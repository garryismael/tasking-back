package com.godigital.tasking.person.domain;

import reactor.core.publisher.Mono;

public interface FindOnePersonUseCase {
  Mono<PersonResponseDto> execute(String id);
}
