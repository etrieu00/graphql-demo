package com.etrieu00.digidex.repository;

import com.etrieu00.digidex.entity.Level;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface LevelRepository extends ReactiveCrudRepository<Level, Long> {
  Flux<Level> findAllByIdentifier(UUID identifier);

  Flux<Level> findAllByIdentifierAndLevel(UUID identifier, Integer level);

}
