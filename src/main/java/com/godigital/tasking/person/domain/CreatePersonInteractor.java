package com.godigital.tasking.person.domain;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreatePersonInteractor implements CreatePersonUseCase {

  private final PersonService personService;
  private final PersonPresenter personPresenter;
  private final PersonFactory personFactory;

  public CreatePersonInteractor(
    PersonService personService,
    PersonPresenter personPresenter,
    PersonFactory personFactory
  ) {
    this.personService = personService;
    this.personPresenter = personPresenter;
    this.personFactory = personFactory;
  }

  @Override
  public Mono<PersonResponseDto> execute(PersonRequestDto dto) {
    Map<String, String> errors = new HashMap<>();

    Mono<Boolean> usernameExists =
      this.personService.existsByUsername(dto.getUsername());

    return usernameExists.flatMap(exists -> {
      if (exists) {
        return this.personPresenter.prepareInvalidDataView(
            "Username already exists",
            errors
          );
      }
      Person person =
        this.personFactory.create(
            null,
            dto.getUsername(),
            dto.getLastName(),
            dto.getAge(),
            dto.getSex()
          );
      Mono<Person> mono = this.personService.create(person);
      Mono<PersonResponseDto> responseDto = mono.map(PersonResponseDto::from);
      return personPresenter.prepareSuccessView(responseDto);
    });
  }
}
