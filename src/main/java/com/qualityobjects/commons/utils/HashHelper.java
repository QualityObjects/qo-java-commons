package com.qualityobjects.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashHelper {
	
	private static final Logger LOG = LoggerFactory.getLogger(HashHelper.class);

	
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
	 * Genera el hash del string pasado por parámetro con el algoritmo pasado por parámetro
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
			LOG.error(e.getMessage());
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
	 * Convierte un arry binario como byte[] en una representación hexadecimal en un String.
	 * 
	 * @param hash
	 * @return
	 */
	public static String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder();
	    for (int i = 0; i < hash.length; i++)
	    {
		    String hex = Integer.toHexString(0xff & hash[i]);
		    if(hex.length() == 1) 
		    {	
		    	hexString.append('0');
		    }
		        hexString.append(hex);
	    }
	    return hexString.toString();
	}
}
