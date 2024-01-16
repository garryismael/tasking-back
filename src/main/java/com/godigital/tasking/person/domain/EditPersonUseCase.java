package com.godigital.tasking.person.domain;

import reactor.core.publisher.Mono;

public interface EditPersonUseCase {
  Mono<PersonResponseDto> execute(String id, PersonRequestDto dto);
}
