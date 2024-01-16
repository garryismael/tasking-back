package com.godigital.tasking.person.domain;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class FindOnePersonInteractor implements FindOnePersonUseCase {

  private final PersonService personService;
  private final PersonPresenter personPresenter;

  public FindOnePersonInteractor(
    PersonService personService,
    PersonPresenter personPresenter
  ) {
    this.personService = personService;
    this.personPresenter = personPresenter;
  }

  @Override
  public Mono<PersonResponseDto> execute(String id) {
    Mono<Person> personMono = this.personService.findOneById(id);

    Map<String, String> errors = new HashMap<>();

    return personMono
      .map(updatedPerson -> {
        return PersonResponseDto.from(updatedPerson);
      })
      .switchIfEmpty(
        Mono.defer(() -> {
          return this.personPresenter.prepareResourceNotFoundView(id, errors);
        })
      );
  }
}
