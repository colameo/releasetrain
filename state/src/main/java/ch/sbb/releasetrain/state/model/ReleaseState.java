/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 * and to You under the Apache License, Version 2.0.
 */
package ch.sbb.releasetrain.state.model;

import ch.sbb.releasetrain.config.model.releaseconfig.ActionConfig;
import ch.sbb.releasetrain.utils.model.Recognizable;

import java.util.List;

import com.google.common.collect.ImmutableList;
import lombok.*;

/**
 * The state of a release event.
 *
 * @author u206123 (Florian Seidl)
 * @since 0.0.6, 2016.
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ReleaseState implements Recognizable<ReleaseState> {

	@Getter
	@Setter
	private List<ActionState> actionState;

	@Getter
	@Setter
	private String releaseName;

	public ReleaseState(String releaseName, List<ActionConfig> configs) {
		this.releaseName = releaseName;
		ImmutableList.Builder<ActionState> actionStatus = new ImmutableList.Builder<>();
		for (ActionConfig actionName : configs) {
			actionStatus.add(new ActionState(actionName));
		}
		this.actionState = actionStatus.build();
	}

	public String getState() {
		String ret = "-";
		for (ActionState state : actionState) {

			if (state.getActionResult() == ActionResult.NONE) {
				ret = "NONE";
			}

			if (state.getActionResult() == ActionResult.SUCCESS && !ret.equals("NONE")) {
				return "SUCCESS";
			}

			if (state.getActionResult() == ActionResult.FAILED) {
				return "FAILED";
			}
		}
		return ret;
	}

	@Override
	public String retreiveIdentifier() {
		return releaseName.replace(":", "").replace(" ", "_");
	}

	@Override
	public int compareTo(ReleaseState releaseState) {
		return releaseState.retreiveIdentifier().compareTo(this.retreiveIdentifier());
	}
}
