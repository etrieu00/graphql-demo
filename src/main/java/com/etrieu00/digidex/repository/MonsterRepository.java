package com.etrieu00.digidex.repository;

import com.etrieu00.digidex.entity.Monster;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface MonsterRepository extends ReactiveCrudRepository<Monster, Long> {
  Flux<Monster> findByIdentifier(UUID identifier);
}
