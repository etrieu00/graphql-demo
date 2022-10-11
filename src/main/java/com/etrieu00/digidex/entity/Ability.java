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
@Table("digimon_ability")
public class Ability {
  @Id
  private Long id;
  private UUID identifier;
  private String name;
  private String description;
}
