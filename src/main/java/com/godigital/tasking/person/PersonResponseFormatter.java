package com.godigital.tasking.person;

import com.godigital.tasking.config.ApiErrorException;
import com.godigital.tasking.config.ErrorResponse;
import com.godigital.tasking.person.domain.PersonPresenter;
import com.godigital.tasking.person.domain.PersonResponseDto;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PersonResponseFormatter implements PersonPresenter {

  @Override
  public Mono<PersonResponseDto> prepareSuccessView(
    Mono<PersonResponseDto> response
  ) {
    return response;
  }

  @Override
  public Flux<PersonResponseDto> prepareSuccessView(
    Flux<PersonResponseDto> responses
  ) {
    return responses;
  }

  @Override
  public Mono<PersonResponseDto> prepareInvalidDataView(
    String message,
    Map<String, String> errors
  ) {
    ErrorResponse errorResponse = new ErrorResponse(
      UUID.randomUUID().toString(),
      OffsetDateTime.now(),
      message,
      HttpStatus.BAD_REQUEST.value(),
      errors
    );
    throw new ApiErrorException(message, errorResponse);
  }

  @Override
  public Mono<PersonResponseDto> prepareResourceNotFoundView(
    String message,
    Map<String, String> errors
  ) {
    ErrorResponse errorResponse = new ErrorResponse(
      UUID.randomUUID().toString(),
      OffsetDateTime.now(),
      message,
      HttpStatus.NOT_FOUND.value(),
      errors
    );
    throw new ApiErrorException(message, errorResponse);
  }
}
