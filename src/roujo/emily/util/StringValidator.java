package roujo.emily.util;

import java.util.ArrayList;
import java.util.List;

public class StringValidator {
	public static boolean isChannel(String string) {
		return string.matches("#[#A-z0-9]+");
	}
	
	public static String[] keepChannels(String[] strings) {
		List<String> channels = new ArrayList<String>();
		for(String string : strings) {
			if(isChannel(string))
				channels.add(string);
		}
		String[] channelArray = new String[channels.size()];
		return channels.toArray(channelArray);
	}
}
