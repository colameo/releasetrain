/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 * and to You under the Apache License, Version 2.0.
 */
package ch.sbb.releasetrain.action;

import org.springframework.stereotype.Component;

import ch.sbb.releasetrain.state.model.ActionState;

/**
 * A Interface for a Action (Ex: Runnable Jenkins Job)
 *
 * @author u203244 (Daniel Marthaler)
 * @since 0.0.1, 2016
 */
@Component
public interface Action {

    String getName();

    void run(ActionState state);

}
