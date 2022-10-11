package com.etrieu00.digidex.repository;

import com.etrieu00.digidex.entity.Ability;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface AbilityRepository extends ReactiveCrudRepository<Ability, Long> {
  Flux<Ability> findByIdentifier(UUID identifier);
}
