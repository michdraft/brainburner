/*
 * Messages.java, Package: helper
 * Misc function like checking regexes, md5 hash creating, debug infos etc.
 */

package helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Helpers {
	private static final boolean debug = true;

	/**
	 * Checks if a string is matching to a regex
	 * @param regex The regex to match to
	 * @param str The string the regex has to match to
	 * @return Either true or false.
	 */
	public static boolean checkRegex(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * Creates a md5 hash from a string. Used for storing passwords.
	 * @param str The plaintext string/password/whatever
	 * @return md5_string The md5 hash. Otherwise null is returned.
	 */
	public static String stringToMD5(String str) {
		try {
			byte[] bytes = str.getBytes();

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(bytes);

			byte[] md5_bytes = md.digest();

			StringBuffer md5_string = new StringBuffer();

			for (int i = 0; i < md5_bytes.length; i++) {
				if (md5_bytes[i] <= 15 && md5_bytes[i] >= 0)
					md5_string.append("0");

				 md5_string.append(Integer.toHexString(0xFF & md5_bytes[i]));
			}

			return md5_string.toString();
		} catch (NoSuchAlgorithmException e) {
			Helpers.debug("stringToMD5: Error: %s", e.getMessage());
			return null;
		}
	}

	/**
	 * Prints the string to stderr if debug is true.
	 * @param str String to be printed to stderr.
	 */
	public static void debug(String format, Object... args) {
		if (debug)
			System.err.printf(format, args);
	}

	/**
	 * Compares two char arrays to be indentical.
	 * @param password Password
	 * @param password2 Passwort repeat
	 * @return True if both passwords are identical.
	 */
	public static boolean cmpPasswords(char[] password, char[] password2) {
		return Arrays.equals(password, password2);
	}
}
