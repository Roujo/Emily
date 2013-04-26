package roujo.emily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		Emily emily;
		if(args.length == 0) {
			String name;
			System.out.println("Emily: How would you like to name me? [Emily]");
			try {
				name = reader.readLine();
				if(name.equals(""))
					name = "Emily";
			} catch (IOException e) {
				logError(e);
				System.out.println("Emily: I'll just call myself Emily, mmkay? =)");
				name = "Emily";
			}
			emily = new Emily(name);
		} else {
			emily = new Emily(args[0]);
		}
		emily.setVerbose(true);

		if (args.length < 2) {
			System.out.println("Emily: Where should I connect? =)");
			String address;
			try {
				address = reader.readLine();
				System.out.println("Emily: Alright! =D");
				emily.connect(address);
			} catch (Exception e) {
				logError(e);
			}
		} else {
			System.out.println("Emily: Connecting to " + args[1]);
			try {
				emily.connect(args[1]);
			} catch (Exception e) {
				logError(e);
			}
			if (emily.isConnected() && args.length > 2) {
				String[] channels = Arrays.copyOfRange(args, 2, args.length);
				for (String channel : channels)
					emily.joinChannel(channel);
			}
		}

		while (emily.isConnected()) {
			try {
				emily.onConsoleMessage(reader.readLine());
			} catch (IOException e) {
				logError(e);
			}
		}
	}

	private static void logError(Exception e) {
		System.out.println("Emily: Ouch! =(");
		e.printStackTrace();
	}

}
