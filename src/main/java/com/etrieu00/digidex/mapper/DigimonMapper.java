package com.etrieu00.digidex.mapper;

import com.etrieu00.digidex.entity.Ability;
import com.etrieu00.digidex.entity.Level;
import com.etrieu00.digidex.entity.Monster;
import com.etrieu00.digidex.entity.Move;
import com.etrieu00.digidex.model.Attack;
import com.etrieu00.digidex.model.Digimon;
import com.etrieu00.digidex.model.Skill;
import com.etrieu00.digidex.model.Status;
import com.etrieu00.digidex.model.enumeration.AttributeType;
import com.etrieu00.digidex.model.enumeration.DigimonType;
import com.etrieu00.digidex.model.enumeration.EvolutionType;

import java.util.function.Function;

public interface DigimonMapper {
  Function<Level, Status> LEVEL_TO_STATUS_MAPPER = level -> Status.builder()
    .id(level.getIdentifier())
    .level(level.getLevel())
    .hp(level.getStatHp())
    .sp(level.getStatSp())
    .atk(level.getStatAtk())
    .def(level.getStatDef())
    .spd(level.getStatSpd())
    .build();

  Function<Monster, Digimon> MONSTER_TO_STATUS_MAPPER = monster -> Digimon.builder()
    .id(monster.getIdentifier())
    .name(monster.getName())
    .evolutionType(EvolutionType.valueOf(monster.getEvolutionStage()))
    .digimonType(DigimonType.valueOf(monster.getDigimonType()))
    .attributeType(AttributeType.valueOf(monster.getAttributeType()))
    .memory(monster.getMemory())
    .equipSlots(monster.getEquipSlots())
    .build();

  Function<Move, Attack> MOVE_TO_ATTACK_MAPPER = move -> Attack.builder()
    .id(move.getIdentifier())
    .name(move.getName())
    .spCost(move.getSpCost())
    .moveType(move.getMoveType())
    .power(move.getPower())
    .attributeType(AttributeType.valueOf(move.getAttributeType()))
    .inheritable(move.getInheritable())
    .description(move.getDescription())
    .build();

  Function<Ability, Skill> ABILITY_TO_SKILL_MAPPER = ability -> Skill.builder()
    .id(ability.getIdentifier())
    .name(ability.getName())
    .description(ability.getDescription())
    .build();
}
