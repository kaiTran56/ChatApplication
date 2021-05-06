package com.tranquyet.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

import com.tranquyet.cipher.CipherGeneration;

public class User {
	private String userName;
	private String password;
	private int number;
	private int id;
	private PrintStream streamOut;
	private InputStream streamIn;
	private CipherGeneration cipher = new CipherGeneration();
	private Socket socket;

	public User(Socket socket, String userName) throws IOException {
		this.userName = userName;
		this.streamOut = new PrintStream(socket.getOutputStream());
		this.streamIn = socket.getInputStream();
		this.id = number;
		this.number += 1;
		this.setSocket(socket);
	}

	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public PrintStream getStreamOut() {
		return streamOut;
	}

	public void setStreamOut(PrintStream streamOut) {
		this.streamOut = streamOut;
	}

	public InputStream getStreamIn() {
		return streamIn;
	}

	public void setStreamIn(InputStream streamIn) {
		this.streamIn = streamIn;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void showNumberUser(int number) {
		String temp = "Number: " + number;

		this.getStreamOut().println(cipher.tranformMessageEncryption(temp));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "<u>" + "<span style='color: red'>" + this.getUsername() + "</span>" + "</u>";
	}

}
