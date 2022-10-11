package com.etrieu00.digidex.repository;

import com.etrieu00.digidex.entity.Move;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface MoveRepository extends ReactiveCrudRepository<Move, Long> {
  Flux<Move> findByIdentifier(UUID identifier);
}
