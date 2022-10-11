package com.etrieu00.digidex.controller;

import com.etrieu00.digidex.model.Digimon;
import com.etrieu00.digidex.model.Status;
import com.etrieu00.digidex.service.DigimonService;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Controller
@SchemaMapping(typeName = "Digimon")
public class DigimonController {

  private final DigimonService digimonService;

  public DigimonController(DigimonService digimonService,
                           BatchLoaderRegistry batchLoaderRegistry) {
    this.digimonService = digimonService;
    // TODO: find a way to do this better. How to batch with input arguments? This Looks terrible
    batchLoaderRegistry.forName("DIGIMON_STATUS_WITH_LEVEL")
      .registerMappedBatchLoader((keys, env) -> digimonService.batchDigimonLevel((Map) env.getKeyContexts()));
    batchLoaderRegistry.forName("DIGIMON_STATUS_WITHOUT_LEVEL")
      .registerBatchLoader((keys, env) -> digimonService.batchDigimonLevel((List) keys));
  }


  @SchemaMapping(field = "status")
  public Mono<List<Status>> statusBatchResolver(Digimon digimon,
                                                @Argument Optional<Integer> level,
                                                DataFetchingEnvironment dataFetchingEnvironment) {
    return level
      .map(input -> {
        DataLoader<UUID, List<Status>> loader = dataFetchingEnvironment.getDataLoader("DIGIMON_STATUS_WITH_LEVEL");
        return Mono.fromFuture(loader.load(digimon.getId(), input));
      }).orElseGet(() -> {
        DataLoader<UUID, List<Status>> loader = dataFetchingEnvironment.getDataLoader("DIGIMON_STATUS_WITHOUT_LEVEL");
        return Mono.fromFuture(loader.load(digimon.getId()));
      });
  }

// Regular batch doesn't support arguments
//  @BatchMapping(field = "status")
//  public Flux<List<Status>> statusBatchResolver(List<Digimon> digimons) {
//    return digimonService.batchDigimonLevel(digimons.stream().map(Digimon::getId).collect(Collectors.toList()));
//  }
}