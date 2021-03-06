/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 * and to You under the Apache License, Version 2.0.
 */
package ch.sbb.releasetrain.action.jenkins;

import static org.junit.Assert.*;

import ch.sbb.releasetrain.action.JenkinsAction;
import ch.sbb.releasetrain.config.ConfigAccessorImpl;
import ch.sbb.releasetrain.config.model.email.MailReceiver;
import ch.sbb.releasetrain.state.model.ActionResult;
import ch.sbb.releasetrain.state.model.ActionState;
import ch.sbb.releasetrain.utils.http.HttpUtilImpl;
import ch.sbb.releasetrain.utils.yaml.YamlModelAccessor;

import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author u203244 (Daniel Marthaler)
 * @since 0.0.1, 2016
 **/

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class JenkinsActionTest {

	@Mock
	private ConfigAccessorImpl config;

	@Mock
	private MailReceiver mailRec;

	private ActionState jenkinsState;

	private JenkinsAction action;

	@org.junit.Before
	public void setUp() throws Exception {

		String in = IOUtils.toString(getClass().getResourceAsStream("/test-jenkins-action-state.yml"));
		YamlModelAccessor<ActionState> yaml = new YamlModelAccessor<>();
		jenkinsState = yaml.convertEntry(in);
		action = new JenkinsAction();

	}

	@org.junit.Test
	public void testGetName() throws Exception {
		assertEquals("jenkinsAction", action.getName());
	}

	@org.junit.Test
	public void testDoWork() throws Exception {

		HttpUtilImpl http = new HttpUtilImpl();
		http.setUser("releasetrain");
		http.setPassword("releasetrain11");

		String jenkins = "http://87.230.15.247:8080/";
		action.setHttp(http);

		HashMap<String, String> map = new HashMap<>();
		map.put("releaseVersion", "releaseVersion");
		map.put("snapshotVersion", "snapshotVersion");
		map.put("maintenanceVersion", "maintenanceVersion");

		ActionResult result = action.doWork(jenkinsState, map);
		assertNotNull(result);
		assertNotNull(result == ActionResult.SUCCESS);

		String user = action.getJenkinsThread().getLatestUserForJob();
		assertTrue(user.contains("Started by remote host"));
	}
}