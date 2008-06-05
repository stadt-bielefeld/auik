/*
 * Datei:
 * $Id: MD5Password.java,v 1.1 2008-06-05 11:38:33 u633d Exp $
 * 
 * Erstellt am 15.08.2005 von David Klotz
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/08/24 08:42:52  u633d
 * - Auswertungen und anderes
 *
 *
 */
package de.bielefeld.umweltamt.aui.utils;

/**
 * Simple Methods for converting and testing MD5-Encoded
 * Passwords.
 * @author Trygve Isaacson. <http://www.bombaydigital.com>
 */
import java.security.*;

public class MD5Password
{
	/**
	 * Encodes a password into a md5-String.
	 * @param clearTextPassword The clear-text password.
	 * @return A hexadecimal md5-encoded representation of the password.
	 */
	public static String getEncodedPassword(String clearTextPassword) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			md.update(clearTextPassword.getBytes());
			
			return HexString.bufferToHex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Cant use MD5-algorithm!", e);
		}
	}
	
	/**
	 * Checks if a given clear-text password matches a stored md5-password.
	 * @param clearTextTestPassword The clear-text password to try.
	 * @param encodedActualPassword The stored, md5-encoded password.
	 * @return <code>true</code>, if the passwords match, <code>false</code> otherwise.
	 */
	public static boolean testPassword(String clearTextTestPassword,
			String encodedActualPassword) {
		String encodedTestPassword = MD5Password.getEncodedPassword(
				clearTextTestPassword);
		
		return (encodedTestPassword.equals(encodedActualPassword));
	}
}
