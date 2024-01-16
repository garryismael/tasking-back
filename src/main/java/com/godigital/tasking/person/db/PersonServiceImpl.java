package com.godigital.tasking.person.db;

import com.godigital.tasking.person.domain.Person;
import com.godigital.tasking.person.domain.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;

  public PersonServiceImpl(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public Mono<Person> create(Person person) {
    PersonDataMapper instance = new PersonDataMapper(person);
    Mono<PersonDataMapper> mono = personRepository.save(instance);
    return cast(mono);
  }

  @Override
  public Flux<Person> findAll() {
    return cast(personRepository.findAll());
  }

  @Override
  public Mono<Person> edit(String id, Person person) {
    PersonDataMapper instance = new PersonDataMapper(person);
    Mono<PersonDataMapper> dataMapper = personRepository.save(instance);
    return cast(dataMapper);
  }

  @Override
  public Mono<Void> delete(String id) {
    return personRepository.deleteById(id);
  }

  @Override
  public Mono<Person> findOneById(String id) {
    Mono<PersonDataMapper> dataMapper = this.personRepository.findById(id);
    return cast(dataMapper);
  }

  private Mono<Person> cast(Mono<PersonDataMapper> dataMapper) {
    return dataMapper.map(person -> {
      return new Person(
        person.getId(),
        person.getUsername(),
        person.getLastName(),
        person.getAge(),
        person.getSex()
      );
    });
  }

  private Flux<Person> cast(Flux<PersonDataMapper> dataMappers) {
    return dataMappers.flatMap(dataMapper -> {
      return Mono.just(
        new Person(
          dataMapper.getId(),
          dataMapper.getUsername(),
          dataMapper.getLastName(),
          dataMapper.getAge(),
          dataMapper.getSex()
        )
      );
    });
  }

  @Override
  public Mono<Boolean> existsByUsername(String username) {
    return this.personRepository.existsByUsername(username);
  }

  @Override
  public Mono<Boolean> existsByUsernameAndIdNot(String username, String id) {
    return this.personRepository.existsByUsernameAndIdNot(username, id);
  }
}
