package com.etrieu00.digidex.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("digimon_status")
public class Level {
  @Id
  private Long id;
  private UUID identifier;
  private Integer level;
  private Integer statHp;
  private Integer statSp;
  private Integer statAtk;
  private Integer statDef;
  private Integer statInt;
  private Integer statSpd;
}
