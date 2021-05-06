package com.tranquyet.cipher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CipherGeneration {

	private Map<String, String> MAPWORD;

	private List<String> listWord;

	private List<String> listWordTemp;

	private List<String> listWordDecryption;

	private List<String> listWordTempDecrypt;

	private String messageEncryption;

	private String messageDecryption;

	public CipherGeneration() {
		super();
		this.listWordTempDecrypt = new ArrayList<String>();
		this.listWordDecryption = new ArrayList<String>();
		this.listWord = new ArrayList<String>();
		this.listWordTemp = new ArrayList<String>();
		this.MAPWORD = new HashMap<String, String>();
		createCipher();
	}

	public void createCipher() {
		MAPWORD.put("00", " ");
		MAPWORD.put("011", "a");
		MAPWORD.put("022", "b");
		MAPWORD.put("03", "c");
		MAPWORD.put("045", "d");
		MAPWORD.put("05", "e");
		MAPWORD.put("06", "f");
		MAPWORD.put("074", "g");
		MAPWORD.put("089", "h");
		MAPWORD.put("09", "i");
		MAPWORD.put("100", "j");
		MAPWORD.put("117", "k");
		MAPWORD.put("12", "l");
		MAPWORD.put("137", "m");
		MAPWORD.put("143", "n");
		MAPWORD.put("159", "o");
		MAPWORD.put("16", "p");
		MAPWORD.put("171", "q");
		MAPWORD.put("18", "r");
		MAPWORD.put("191", "s");
		MAPWORD.put("20", "t");
		MAPWORD.put("21", "u");
		MAPWORD.put("221", "v");
		MAPWORD.put("231", "w");
		MAPWORD.put("24", "x");
		MAPWORD.put("25", "y");
		MAPWORD.put("26", "z");
		MAPWORD.put("27", ",");
		MAPWORD.put("28", ".");
		MAPWORD.put("29", "/");
		MAPWORD.put("30", "\\");
		MAPWORD.put("31", "<");
		MAPWORD.put("322", ">");
		MAPWORD.put("33", "?");
		MAPWORD.put("34", "|");
		MAPWORD.put("353", ";");
		MAPWORD.put("36", "'");
		MAPWORD.put("37", ":");
		MAPWORD.put("38", "[");
		MAPWORD.put("39", "]");
		MAPWORD.put("40", "{");
		MAPWORD.put("41", "}");
		MAPWORD.put("42", "-");
		MAPWORD.put("43", "_");
		MAPWORD.put("44", "=");
		MAPWORD.put("45", "+");
		MAPWORD.put("46", "(");
		MAPWORD.put("47", ")");
		MAPWORD.put("48", "!");
		MAPWORD.put("49", "@");
		MAPWORD.put("50", "#");
		MAPWORD.put("51", "$");
		MAPWORD.put("52", "%");
		MAPWORD.put("53", "^");
		MAPWORD.put("54", "&");
		MAPWORD.put("55", "*");
		MAPWORD.put("555", "A");
		MAPWORD.put("777", "B");
		MAPWORD.put("888", "C");
		MAPWORD.put("999", "D");
		MAPWORD.put("111", "E");
		MAPWORD.put("82", "F");
		MAPWORD.put("83", "G");
		MAPWORD.put("85", "H");
		MAPWORD.put("65", "I");
		MAPWORD.put("70", "J");
		MAPWORD.put("71", "K");
		MAPWORD.put("723", "L");
		MAPWORD.put("73", "M");
		MAPWORD.put("74", "N");
		MAPWORD.put("75", "O");
		MAPWORD.put("76", "P");
		MAPWORD.put("77", "Q");
		MAPWORD.put("78", "R");
		MAPWORD.put("79", "S");
		MAPWORD.put("80", "T");
		MAPWORD.put("81", "U");
		MAPWORD.put("82", "V");
		MAPWORD.put("83", "W");
		MAPWORD.put("844", "X");
		MAPWORD.put("812", "Y");
		MAPWORD.put("85679", "Z");
		MAPWORD.put("1", "1");
		MAPWORD.put("2", "2");
		MAPWORD.put("3", "3");
		MAPWORD.put("4", "4");
		MAPWORD.put("5", "5");
		MAPWORD.put("6", "6");
		MAPWORD.put("7", "7");
		MAPWORD.put("8", "8");
		MAPWORD.put("9", "9");
		MAPWORD.put("0", "0");

	}

	/*
	 * @purpose: split message to List<String> which will be compare to MAPWORD
	 *
	 * @result: listWord will contain the list number responding to MAPWORD
	 * 
	 */

	public void encryption(String message) {
		String[] arrTemp = message.split("");
		listWordTemp = Arrays.asList(arrTemp);
		for (String word : this.listWordTemp) {
			MAPWORD.forEach((k, v) -> {
				if (v.equals(word)) {
					listWord.add(k);
				}
			});
		}

		System.out.println("Tranfer to number: " + listWord.toString() + "Size: " + listWord.size());
	}
	/*
	 * @purpose: tranfer listWord to String message which is encrypted by number
	 * 
	 */

	public String tranformMessageEncryption(String message) {

		encryption(message);

		listWord.forEach(p -> {
			if (messageEncryption == null) {
				messageEncryption = p;
			} else {
				messageEncryption = messageEncryption + " " + p;
			}
		});

		System.out.println("Test: MessageEncryption: " + messageEncryption + " Length: " + messageEncryption.length());
		return messageEncryption;

	}
	/*
	 * 
	 * 
	 * @??
	 * 
	 * 
	 * 
	 */

	public void decryption(String messageEncryptionTemp) {
		String[] arrTempEncryption = messageEncryptionTemp.split(" ");

		listWordTempDecrypt = Arrays.asList(arrTempEncryption);
		System.out.println("Text: " + listWordTempDecrypt.toString());
		for (String word : this.listWordTempDecrypt) {
			MAPWORD.forEach((k, v) -> {
				if (k.equals(word)) {
					listWordDecryption.add(v);
				}
			});

		}
		System.out.println("Conert to sentence: " + listWordDecryption.toString());

	}

	public String tranformDecryptionMessage(String messageEncryptionTemp) {
		decryption(messageEncryptionTemp);
		listWordDecryption.forEach(p -> {
			if (messageDecryption == null) {
				messageDecryption = p;
			} else {
				messageDecryption = messageDecryption + p;
			}
		});

		removeAll(listWordDecryption);

		return messageDecryption;
	}

	public void removeAll(List<String> temp) {
		Iterator<String> i = temp.iterator();
		while (i.hasNext()) {
			@SuppressWarnings("unused")
			String tempWord = i.next();
			i.remove();
		}
	}

	public static void main(String[] args) {
		String hello = new CipherGeneration().tranformDecryptionMessage(
				"31 022 00 191 20 25 12 05 00 44 00 36 03 159 12 159 18 37 00 074 18 05 05 143 353 36 322 83 05 12 03 159 137 05 191 00 80 159 00 80 089 05 00 74 05 231 00 83 159 18 12 045 37 00 31 29 022 322 00 31 21 322 31 191 16 011 143 00 191 20 25 12 05 44 36 03 159 12 159 18 37 00 18 05 045 36 322 80 18 011 143 80 18 011 143 31 29 191 16 011 143 322 31 29 21 322 31 022 00 191 20 25 12 05 00 44 00 36 03 159 12 159 18 37 00 074 18 05 05 143 353 36 322 83 05 12 03 159 137 05 191 00 80 159 00 80 089 05 00 74 05 231 00 83 159 18 12 045 37 00 31 29 022 322 00 31 21 322 31 191 16 011 143 00 191 20 25 12 05 44 36 03 159 12 159 18 37 00 18 05 045 36 322 80 18 011 143 80 18 011 143 31 29 191 16 011 143 322 31 29 21 322 38 31 21 322 31 191 16 011 143 00 191 20 25 12 05 44 36 03 159 12 159 18 37 00 18 05 045 36 322 80 18 011 143 80 18 011 143 31 29 191 16 011 143 322 31 29 21 322 39 31 022 00 191 20 25 12 05 00 44 00 36 03 159 12 159 18 37 00 074 18 05 05 143 353 36 322 83 05 12 03 159 137 05 191 00 80 159 00 80 089 05 00 74 05 231 00 83 159 18 12 045 37 00 31 29 022 322 00 31 21 322 31 191 16 011 143 00 191 20 25 12 05 44 36 03 159 12 159 18 37 00 18 05 045 36 322 80 18 011 143 80 18 011 143 31 29 191 16 011 143 322 31 29 21 322 38 31 21 322 31 191 16 011 143 00 191 20 25 12 05 44 36 03 159 12 159 18 37 00 18 05 045 36 322 80 18 011 143 80 18 011 143 31 29 191 16 011 143 322 31 29 21 322 39 31 21 322 31 191 16 011 143 00 191 20 25 12 05 44 36 03 159 12 159 18 37 00 18 05 045 36 322 80 18 011 143 80 18 011 143 31 29 191 16 011 143 322 31 29 21 322 46 25 159 21 47 37 00 80 18 011 143 80 18 011 143 51 1 2 3 80 18 011 143 80 18 011 143 51 1 2 3 089 05 25");
		System.out.println(hello);
	}
}
