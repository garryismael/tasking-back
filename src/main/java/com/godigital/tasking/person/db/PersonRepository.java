package com.godigital.tasking.person.db;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PersonRepository
  extends ReactiveMongoRepository<PersonDataMapper, String> {
  Mono<Boolean> existsByUsername(String username);
  Mono<Boolean> existsByUsernameAndIdNot(String username, String id);
}
