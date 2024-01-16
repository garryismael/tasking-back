package com.godigital.tasking.person.domain;

import reactor.core.publisher.Flux;

public interface ListPersonUseCase {
  Flux<PersonResponseDto> execute();
}
