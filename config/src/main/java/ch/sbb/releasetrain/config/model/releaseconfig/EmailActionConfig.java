/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 * and to You under the Apache License, Version 2.0.
 */
package ch.sbb.releasetrain.config.model.releaseconfig;

import ch.sbb.releasetrain.utils.model.Recognizable;

import lombok.Data;

/**
 * Representation of a Email Action Config retreived from a storage provider
 * (Ex: GIT Repo)
 *
 * @author u203244 (Daniel Marthaler)
 * @since 0.0.1, 2016
 */
@Data
public class EmailActionConfig extends ActionConfig implements Recognizable<EmailActionConfig> {

	private String smtpServer;

	private String subject;

	private String text;

	private String receiver;

	private String sender;

	@Override
	public int compareTo(EmailActionConfig config) {
		return super.compareTo(config);
	}

	@Override
	public String getName() {
		return "EmailAction";
	}

}
