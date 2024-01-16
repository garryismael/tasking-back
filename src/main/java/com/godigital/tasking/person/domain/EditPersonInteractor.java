package com.godigital.tasking.person.domain;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class EditPersonInteractor implements EditPersonUseCase {

  private final PersonService personService;
  private final PersonPresenter personPresenter;
  private final PersonFactory personFactory;

  public EditPersonInteractor(
    PersonService personService,
    PersonPresenter personPresenter,
    PersonFactory personFactory
  ) {
    this.personService = personService;
    this.personPresenter = personPresenter;
    this.personFactory = personFactory;
  }

  public Mono<PersonResponseDto> execute(String id, PersonRequestDto dto) {
    Mono<Person> personMono = this.personService.findOneById(id);

    Map<String, String> errors = new HashMap<>();

    Mono<Boolean> usernameExists =
      this.personService.existsByUsernameAndIdNot(dto.getUsername(), id);

    return usernameExists.flatMap(exists -> {
      if (exists) {
        return this.personPresenter.prepareInvalidDataView(
            "Username already exists",
            errors
          );
      }

      return personMono
        .flatMap(p -> {
          Person person =
            this.personFactory.create(
                p.getId(),
                dto.getUsername(),
                dto.getLastName(),
                dto.getAge(),
                dto.getSex()
              );

          return this.personService.edit(id, person).then(Mono.just(person));
        })
        .map(updatedPerson -> {
          return PersonResponseDto.from(updatedPerson);
        })
        .switchIfEmpty(
          Mono.defer(() -> {
            return this.personPresenter.prepareInvalidDataView(id, errors);
          })
        );
    });
  }
}
