package com.godigital.tasking.person;

import com.godigital.tasking.person.domain.CreatePersonUseCase;
import com.godigital.tasking.person.domain.DeletePersonUseCase;
import com.godigital.tasking.person.domain.EditPersonUseCase;
import com.godigital.tasking.person.domain.FindOnePersonUseCase;
import com.godigital.tasking.person.domain.ListPersonUseCase;
import com.godigital.tasking.person.domain.PersonRequestDto;
import com.godigital.tasking.person.domain.PersonResponseDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/persons")
public class PersonController {

  private final CreatePersonUseCase createUseCase;
  private final ListPersonUseCase listUseCase;
  private final FindOnePersonUseCase findOneUseCase;
  private final EditPersonUseCase editUseCase;
  private final DeletePersonUseCase deleteUseCase;

  public PersonController(
    CreatePersonUseCase createUseCase,
    ListPersonUseCase listUseCase,
    FindOnePersonUseCase findOneUseCase,
    EditPersonUseCase editUseCase,
    DeletePersonUseCase deleteUseCase
  ) {
    this.createUseCase = createUseCase;
    this.listUseCase = listUseCase;
    this.findOneUseCase = findOneUseCase;
    this.editUseCase = editUseCase;
    this.deleteUseCase = deleteUseCase;
  }

  @PostMapping
  public Mono<PersonResponseDto> create(@RequestBody PersonRequestDto dto) {
    return createUseCase.execute(dto);
  }

  @GetMapping
  public Flux<PersonResponseDto> findAll() {
    return listUseCase.execute();
  }

  @GetMapping("/{id}")
  public Mono<PersonResponseDto> getOne(@PathVariable String id) {
    return findOneUseCase.execute(id);
  }

  @PutMapping("/{id}")
  public Mono<PersonResponseDto> edit(
    @PathVariable String id,
    @RequestBody PersonRequestDto dto
  ) {
    return editUseCase.execute(id, dto);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable String id) {
    return deleteUseCase.execute(id);
  }
}
