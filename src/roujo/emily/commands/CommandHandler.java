package roujo.emily.commands;

import roujo.emily.Context;

public class CommandHandler {
	public static boolean processMessage(Context context) {
		String[] messageParts = splitCommandFromArguments(context.getMessage());
		
		Command command = CommandType.getByName(messageParts[0]);
		
		if(!command.isValidSender(context.getSender()))
			return false;
		else
			return command.execute(context);
	}

	private static String[] splitCommandFromArguments(String message) {
		int firstSpace = message.indexOf(' ');
		boolean hasArgs = firstSpace != -1 && message.length() > firstSpace + 2;
		String command, arguments;
		if (hasArgs) {
			command = message.substring(0, firstSpace);
			arguments = message.substring(firstSpace + 1);
		} else {
			command = message;
			arguments = "";
		}
		command = command.toLowerCase();
		return new String[]{command, arguments};
	}
}
