package com.etrieu00.digidex.model.input;

import com.etrieu00.digidex.model.enumeration.AttributeType;
import com.etrieu00.digidex.model.enumeration.MoveType;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.web.ProjectedPayload;

import java.util.List;
import java.util.Optional;

@Value
@Builder
@ProjectedPayload
public class AttackFilter {

  List<Integer> spCost;
  List<MoveType> moveType;
  List<Integer> power;
  List<AttributeType> attributeType;
  Boolean inheritable;

  public Optional<List<Integer>> getSpCost() {
    return Optional.ofNullable(spCost);
  }

  public Optional<List<MoveType>> getMoveType() {
    return Optional.ofNullable(moveType);
  }

  public Optional<List<Integer>> getPower() {
    return Optional.ofNullable(power);
  }

  public Optional<List<AttributeType>> getAttributeType() {
    return Optional.ofNullable(attributeType);
  }

  public Optional<Boolean> getInheritable() {
    return Optional.ofNullable(inheritable);
  }
}
