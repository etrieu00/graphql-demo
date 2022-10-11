package com.etrieu00.digidex.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Status {
  UUID id;
  Integer level;
  Integer hp;
  Integer sp;
  Integer atk;
  Integer def;
  Integer spd;
}
