package com.godigital.tasking.person.domain;

import java.util.Map;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonPresenter {
  Mono<PersonResponseDto> prepareSuccessView(Mono<PersonResponseDto> response);

  Flux<PersonResponseDto> prepareSuccessView(Flux<PersonResponseDto> responses);

  Mono<PersonResponseDto> prepareInvalidDataView(
    String message,
    Map<String, String> errors
  );

  Mono<PersonResponseDto> prepareResourceNotFoundView(
    String message,
    Map<String, String> errors
  );
}
