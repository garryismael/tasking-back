package com.godigital.tasking.person.domain;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DeletePersonInteractor implements DeletePersonUseCase {

  private final PersonService personService;
  private final PersonPresenter personPresenter;

  public DeletePersonInteractor(
    PersonService personService,
    PersonPresenter personPresenter
  ) {
    this.personService = personService;
    this.personPresenter = personPresenter;
  }

  @Override
  public Mono<Void> execute(String id) {
    Mono<Person> personMono = this.personService.findOneById(id);

    Map<String, String> errors = new HashMap<>();

    return personMono
      .flatMap(person -> {
        this.personService.delete(id);
        return Mono.empty();
      })
      .switchIfEmpty(
        Mono.defer(() -> {
          return Mono.just(
            personPresenter.prepareResourceNotFoundView(id, errors)
          );
        })
      )
      .then();
  }
}
