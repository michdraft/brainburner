/*
 * Messages.java, Package: helper
 * Misc function like checking regexes, md5 hash creating, debug infos etc.
 */

package helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Helpers {
	private static final boolean debug = true;

	public static boolean checkRegex(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public static String stringToMD5(String str) {
		try {
			byte[] bytes = str.getBytes();

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(bytes);

			byte[] md5_bytes = md.digest();

			StringBuffer md5_string = new StringBuffer();

			for (int i = 0; i < md5_bytes.length; i++) {
				 md5_string.append(Integer.toHexString(0xFF & md5_bytes[i]));
			}

			return md5_string.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static void debug(String str) {
		if (debug)
			System.err.println(str);
	}
}
