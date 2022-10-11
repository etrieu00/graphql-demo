package com.etrieu00.digidex.config;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class LoggingConfig extends SimpleInstrumentation {

  private static final Logger LOGGER = LogManager.getLogger(LoggingConfig.class);

  @Override
  public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {
    var start = Instant.now();
    var executionId = parameters.getExecutionInput().getExecutionId();
    LOGGER.info("[{}] The start time {}", executionId, start);
    return SimpleInstrumentationContext.whenCompleted((result, throwable) ->
      LOGGER.info("[{}] The time to complete:  {}ms", executionId, Duration.between(start, Instant.now()).toMillis()));
  }
}
