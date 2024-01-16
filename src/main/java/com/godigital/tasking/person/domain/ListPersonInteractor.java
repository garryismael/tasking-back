package com.godigital.tasking.person.domain;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ListPersonInteractor implements ListPersonUseCase {

  private final PersonService personService;
  private final PersonPresenter personPresenter;

  public ListPersonInteractor(
    PersonService personService,
    PersonPresenter personPresenter
  ) {
    this.personService = personService;
    this.personPresenter = personPresenter;
  }

  @Override
  public Flux<PersonResponseDto> execute() {
    Flux<Person> persons = this.personService.findAll();
    Flux<PersonResponseDto> response = persons.map(person -> {
      return PersonResponseDto.from(person);
    });
    return personPresenter.prepareSuccessView(response);
  }
}
