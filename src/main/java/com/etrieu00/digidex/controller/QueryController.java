package com.etrieu00.digidex.controller;

import com.etrieu00.digidex.model.Attack;
import com.etrieu00.digidex.model.Digimon;
import com.etrieu00.digidex.model.Skill;
import com.etrieu00.digidex.model.input.AttackFilter;
import com.etrieu00.digidex.service.DigimonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class QueryController {

  private final DigimonService digimonService;

  @QueryMapping(name = "digimon")
  public Flux<Digimon> digimonResolver(@Argument("id") Optional<UUID> identifier) {
    return identifier
      .map(digimonService::findDigimon)
      .orElseGet(digimonService::retrieveDigimons);
  }

  @QueryMapping(name = "attack")
  public Flux<Attack> attackResolver(@Argument("id") Optional<UUID> identifier,
                                     @Argument("filter") Optional<AttackFilter> filter) {
    return identifier.map(digimonService::findAttack)
      .orElseGet(() -> filter.map(digimonService::filterAttack)
        .orElseGet(digimonService::retrieveAttacks)
      );
  }

  @QueryMapping(name = "skill")
  public Flux<Skill> abilityResolver(@Argument("id") Optional<UUID> identifier) {
    return identifier
      .map(digimonService::findSkill)
      .orElseGet(digimonService::retrieveSkills);
  }

}
