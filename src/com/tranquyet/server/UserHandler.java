package com.tranquyet.server;

import java.util.Scanner;

public class UserHandler extends Thread {
	private Server server;
	private User user;

	public UserHandler(Server server, User user) {
		super();
		this.server = server;
		this.user = user;
		this.server.boardcastUser();
	}

	@Override
	public void run() {
		String message;
		Scanner scanner = new Scanner(this.user.getStreamIn());
		while (scanner.hasNextLine()) {
			message = scanner.nextLine();

			System.out.println("Test message: " + message);
			if (message.charAt(0) == '@') {
				if (message.contains(": ")) {
					int firstColon = message.indexOf(":");
					String nameUser = message.substring(1, firstColon);

					server.sendMessageToUser(message, user, nameUser);
				}
			} else {
				server.boardcastMessage(message, user);
			}
		}
		server.removeUser(user);
		server.boardcastUser();
		scanner.close();
	}

}
