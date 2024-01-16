package com.godigital.tasking.person.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
  Mono<Person> create(Person person);

  Flux<Person> findAll();

  Mono<Person> edit(String id, Person person);

  Mono<Void> delete(String id);

  Mono<Person> findOneById(String id);

  Mono<Boolean> existsByUsername(String username);

  Mono<Boolean> existsByUsernameAndIdNot(String username, String id);
}
