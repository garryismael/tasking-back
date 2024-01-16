package com.godigital.tasking.person.domain;

import java.util.HashMap;
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
    this.personService.delete(id).then(Mono.empty());
    return Mono.empty();
  }
}
