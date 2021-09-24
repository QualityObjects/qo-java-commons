package com.qualityobjects.commons.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashHelper {

	/**
	 * Constructor
	 */
	private HashHelper() {
	}

	/**
	 * Genera el hash del string pasado por parámetro con el algoritmo SHA-256.
	 * 
	 * @param text
	 * @return código hash en hexadecimal o null si hay una excepción.
	 */
	public static String hashSHA256(CharSequence text) {
		return hash(text, "SHA-256");
	}

	/**
	 * Genera el hash del string pasado por parámetro con el algoritmo pasado por
	 * parámetro
	 * 
	 * @param text
	 * @return código hash en hexadecimal o null si hay una excepción.
	 */
	public static String hash(CharSequence text, String alg) {
		if (text == null) {
			return null;
		}
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(alg);
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
			return null;
		}
		return bytesToHex(digest.digest(text.toString().getBytes(StandardCharsets.UTF_8)));
	}

	/**
	 * Genera el hash del string pasado por parámetro con el algoritmo MD5
	 * 
	 * @param text
	 * @return código hash en hexadecimal o null si hay una excepción.
	 */
	public static String hashMD5(String text) {
		return hash(text, "MD5");
	}

	/**
	 * Convierte un arry binario como byte[] en una representación hexadecimal en un
	 * String.
	 * 
	 * @param hash
	 * @return
	 */
	public static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder();
		for (byte byteData : hash) {
			hexString.append(String.format("%02X", byteData));
		}
		return hexString.toString();
	}

	/**
	 * Return the 
	 * 
	 * @param hash
	 * @return
	 */
	public static String calculateHexChecksum(MessageDigest md) {
		return bytesToHex(md.digest());
	}
}
