package com.etrieu00.digidex.utility;

import com.etrieu00.digidex.model.Attack;
import com.etrieu00.digidex.model.input.AttackFilter;

@FunctionalInterface
public interface FilterRule<E, R> {
  boolean validate(E expected, R result);

  FilterRule<Attack, AttackFilter> SP_RULE = (attack, attackFilter) ->
    attackFilter.getSpCost()
      .map(conditions -> conditions.contains(attack.getSpCost()))
      .orElse(true);

  FilterRule<Attack, AttackFilter> MOVE_TYPE_RULE = (attack, attackFilter) ->
    attackFilter.getMoveType()
      .map(conditions -> conditions.contains(attack.getMoveType()))
      .orElse(true);


  FilterRule<Attack, AttackFilter> POWER_RULE = (attack, attackFilter) ->
    attackFilter.getPower()
      .map(conditions -> conditions.contains(attack.getPower()))
      .orElse(true);

  FilterRule<Attack, AttackFilter> ATTRIBUTE_TYPE_RULE = (attack, attackFilter) ->
    attackFilter.getAttributeType()
      .map(conditions -> conditions.contains(attack.getAttributeType()))
      .orElse(true);

  FilterRule<Attack, AttackFilter> INHERITABLE_RULE = (attack, attackFilter) ->
    attackFilter.getInheritable()
      .orElse(true);
}
