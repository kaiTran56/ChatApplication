package com.tranquyet.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tranquyet.cipher.CipherGeneration;
import com.tranquyet.dao.impl.UserDaoImpl;

public class Server {
	private ServerSocket server;
	private CipherGeneration cipher = new CipherGeneration();

	private int port;
	private List<User> listUser;
	private String temp;

	public Server(int port) {
		listUser = new ArrayList<User>();
		this.port = port;
	}

	public void run() throws IOException {

		setServer(new ServerSocket(port));

		while (true) {

			Socket socket = server.accept();

			Scanner scanner = new Scanner(socket.getInputStream());
			// come from client send to server include username + password
			String sessionTemp = scanner.nextLine();

			String session = this.cipher.tranformDecryptionMessage(sessionTemp);

			boolean checkName = false;

			String username = decodeUsername(session);
			String password = decodePassword(session);

			User account = new User(username, password);
			User userTemp = null;
			// access to jdbc to check the account!
			boolean checkLogin = new UserDaoImpl().login(account);
			if (checkLogin == false) {

				PrintStream feedback = new PrintStream(socket.getOutputStream());
				temp = "fail to login";
				feedback.println(cipher.tranformMessageEncryption(temp));

			} else {

				username = username.replace(",", "").replace(" ", "_");
				for (User user : this.listUser) {
					if (username.equals(user.getUsername())) {
						PrintStream feedback = new PrintStream(socket.getOutputStream());
						temp = "Name is exist!";
						feedback.println(cipher.tranformMessageEncryption(temp));
						checkName = true;
						break;
					} else {

					}
				}

			}
			userTemp = new User(socket, username);
			System.out.println("User access to Server: " + username);
			if (checkName == false) {
				listUser.add(userTemp);
				temp = "<b style = 'color: green;'>Welcomes To The New World: </b> " + userTemp.toString();
				userTemp.getStreamOut().println(cipher.tranformMessageEncryption(temp));

			}

			new UserHandler(this, userTemp).start();

		}
	}

	public void boardcastUser() {
		for (User userTemp : this.listUser) {
			userTemp.showNumberUser(this.listUser.size());
			temp = this.listUser.toString();
			userTemp.getStreamOut().println(cipher.tranformMessageEncryption(temp));

		}
	}

	public void boardcastMessage(String message, User sender) {
		for (User user : this.listUser) {
			if (!sender.getUsername().equals(user.getUsername())) {
				temp = sender.toString() + ": " + message;
				user.getStreamOut().println(cipher.tranformMessageEncryption(temp));
			} else {
				temp = sender.toString() + "(you)" + ": " + message;
				sender.getStreamOut().println(cipher.tranformMessageEncryption(temp));
			}

		}
	}

	public void sendMessageToUser(String message, User sender, String reciever) {
		for (User user : this.listUser) {
			if (user.getUsername().equals(reciever) && !reciever.equals(sender.getUsername())) {
				user.getStreamOut()
						.println(sender + "->" + message.replace("@", "").replace(user.getUsername(), "(you): "));
				sender.getStreamOut().println(sender + "(you) " + "-> " + message);
			}

		}
	}

	public String decodeUsername(String session) {
		int position = session.indexOf("$");
		String username = session.substring(0, position);
		return username;
	}

	public String decodePassword(String session) {
		int position = session.indexOf("$");
		String password = session.substring(position + 1, session.length());
		return password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	public void removeUser(User user) {
		for (User userTemp : this.listUser) {
			if (!userTemp.getUsername().equals(user.getUsername()))
				temp = "<br>" + "---User: " + user.toString() + " out the conventions!---" + "<br>";
			userTemp.getStreamOut().println(cipher.tranformMessageEncryption(temp));
		}

		this.listUser.remove(user);
	}

	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}

	public void stop() {
		System.exit(0);
	}

}
