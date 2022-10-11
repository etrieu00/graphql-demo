package com.etrieu00.digidex.model;

import com.etrieu00.digidex.model.enumeration.AttributeType;
import com.etrieu00.digidex.model.enumeration.DigimonType;
import com.etrieu00.digidex.model.enumeration.EvolutionType;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Digimon {
  UUID id;
  String name;
  EvolutionType evolutionType;
  DigimonType digimonType;
  AttributeType attributeType;
  Integer memory;
  Integer equipSlots;
}
