package com.etrieu00.digidex.entity;

import com.etrieu00.digidex.model.enumeration.AttributeType;
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
@Table("digimon_info")
public class Monster {
  @Id
  private Long id;
  private UUID identifier;
  private String name;
  private String evolutionStage;
  private String digimonType;
  private String attributeType;
  private Integer memory;
  private Integer equipSlots;
}
