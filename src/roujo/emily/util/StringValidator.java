package roujo.emily.util;

public class StringValidator {
	public static boolean isChannel(String string) {
		return string.matches("#[#A-z0-9]+");
	}
}
