package com.etrieu00.digidex.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Skill {
  UUID id;
  String name;
  String description;
}
