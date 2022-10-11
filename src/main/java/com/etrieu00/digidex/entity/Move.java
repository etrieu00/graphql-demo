package com.etrieu00.digidex.entity;

import com.etrieu00.digidex.model.enumeration.AttributeType;
import com.etrieu00.digidex.model.enumeration.MoveType;
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
@Table("digimon_move")
public class Move {
  @Id
  private Long id;
  private UUID identifier;
  private String name;
  private Integer spCost;
  private MoveType moveType;
  private Integer power;
  private String attributeType;
  private Boolean inheritable;
  private String description;
}
