/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 * and to You under the Apache License, Version 2.0.
 */
package ch.sbb.releasetrain.action;

import ch.sbb.releasetrain.state.model.ActionResult;
import ch.sbb.releasetrain.state.model.ActionState;

import java.util.HashMap;

import org.springframework.stereotype.Component;

/**
 * A Interface for a Action (Ex: Runnable Jenkins Job)
 *
 * @author u203244 (Daniel Marthaler)
 * @since 0.0.1, 2016
 */
@Component
public interface Action {

	String getName();

	ActionResult run(ActionState state, HashMap<String, String> properties);

}
