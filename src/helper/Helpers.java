/*
 * Messages.java, Package: helper
 * Several functions for checking inputs from the user.
 */

package helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {
	public static boolean checkRegex(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		boolean flag = matcher.matches();
		return flag;
	}
}
