package com.tranquyet.test;

public class Main {
	public static void main(String[] args) {
		String name = "hello$kim";
		String username = name.substring( name.indexOf("$")+1, name.length());
		System.out.println(username);
	}
}
