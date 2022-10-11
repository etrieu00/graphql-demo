package com.etrieu00.digidex.model;

import com.etrieu00.digidex.model.enumeration.AttributeType;
import com.etrieu00.digidex.model.enumeration.MoveType;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Attack {
  UUID id;
  String name;
  Integer spCost;
  MoveType moveType;
  Integer power;
  AttributeType attributeType;
  Boolean inheritable;
  String description;
}
