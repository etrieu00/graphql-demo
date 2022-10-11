package com.etrieu00.digidex.service;

import com.etrieu00.digidex.model.Attack;
import com.etrieu00.digidex.model.Digimon;
import com.etrieu00.digidex.model.Skill;
import com.etrieu00.digidex.model.Status;
import com.etrieu00.digidex.model.input.AttackFilter;
import com.etrieu00.digidex.repository.AbilityRepository;
import com.etrieu00.digidex.repository.LevelRepository;
import com.etrieu00.digidex.repository.MonsterRepository;
import com.etrieu00.digidex.repository.MoveRepository;
import com.etrieu00.digidex.utility.FilterRule;
import com.etrieu00.digidex.utility.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.etrieu00.digidex.mapper.DigimonMapper.*;
import static com.etrieu00.digidex.utility.FilterRule.*;

@Slf4j
@Service
public class DigimonService {

  private final AbilityRepository abilityRepository;
  private final MonsterRepository monsterRepository;
  private final LevelRepository levelRepository;
  private final MoveRepository moveRepository;
  private final List<FilterRule<Attack, AttackFilter>> attackFilterRules;

  public DigimonService(AbilityRepository abilityRepository,
                        MonsterRepository monsterRepository,
                        LevelRepository levelRepository,
                        MoveRepository moveRepository) {
    this.abilityRepository = abilityRepository;
    this.monsterRepository = monsterRepository;
    this.levelRepository = levelRepository;
    this.moveRepository = moveRepository;
    this.attackFilterRules = List.of(
      SP_RULE,
      MOVE_TYPE_RULE,
      POWER_RULE,
      ATTRIBUTE_TYPE_RULE,
      INHERITABLE_RULE
    );
  }

  public Flux<Digimon> retrieveDigimons() {
    return monsterRepository.findAll()
      .map(MONSTER_TO_STATUS_MAPPER);
  }

  public Flux<List<Status>> batchDigimonLevel(List<UUID> identifiers) {
    return Flux.fromIterable(identifiers)
      .flatMap(identifier -> levelRepository
        .findAllByIdentifier(identifier)
        .map(LEVEL_TO_STATUS_MAPPER)
        .collectList());
  }

  public Mono<Map<UUID, List<Status>>> batchDigimonLevel(Map<UUID, Integer> items) {
    return Flux.fromIterable(items.entrySet())
      .flatMap(entry -> levelRepository
        .findAllByIdentifierAndLevel(entry.getKey(), entry.getValue())
        .map(LEVEL_TO_STATUS_MAPPER)
        .collectList()
        .map(result -> new Tuple<>(entry.getKey(), result)))
      .collectMap(Tuple::left, Tuple::right);
  }

  public Flux<Digimon> findDigimon(UUID identifier) {
    return monsterRepository.findByIdentifier(identifier)
      .map(MONSTER_TO_STATUS_MAPPER);
  }

  public Flux<Attack> findAttack(UUID uuid) {
    return moveRepository.findByIdentifier(uuid)
      .map(MOVE_TO_ATTACK_MAPPER);
  }

  public Flux<Attack> filterAttack(AttackFilter attackFilter) {
    //TODO this is nasty, look at JOOQ for dynamic queries or find something else
    return moveRepository.findAll()
      .map(MOVE_TO_ATTACK_MAPPER)
      .filter(attack -> attackFilterRules.stream()
        .allMatch(rule -> rule.validate(attack, attackFilter)));
  }

  public Flux<Attack> retrieveAttacks() {
    return moveRepository.findAll()
      .map(MOVE_TO_ATTACK_MAPPER);
  }

  public Flux<Skill> retrieveSkills() {
    return abilityRepository.findAll()
      .map(ABILITY_TO_SKILL_MAPPER);
  }

  public Flux<Skill> findSkill(UUID uuid) {
    return abilityRepository.findByIdentifier(uuid)
      .map(ABILITY_TO_SKILL_MAPPER);
  }
}
