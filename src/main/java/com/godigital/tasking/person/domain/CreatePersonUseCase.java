package com.godigital.tasking.person.domain;

import reactor.core.publisher.Mono;

public interface CreatePersonUseCase {
  Mono<PersonResponseDto> execute(PersonRequestDto dto);
}
