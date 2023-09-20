package com.sirma.javacourse.networking.clientserver.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientServerTest {

	private Server server;

	@Before
	public void initializeServer() {
		server = new Server();
		server.start();
		new Thread(() -> server.listen()).start();
	}

	@Test
	public void testClientReceiveMessage() {
		Client client = new Client();
		client.startConnection();
		assertTrue(client.receiveMessage().startsWith("Hello! Current time: "));
		client.stopConnection();
	}

	@Test
	public void testServerMultipleClients() {
		for (int i = 0; i < 5; i++) {
			Client client = new Client();
			client.startConnection();
			assertNotNull(client.receiveMessage());
			client.stopConnection();
		}
	}

	@After
	public void stopServer() {
		server.stop();
	}
}
