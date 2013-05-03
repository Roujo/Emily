package roujo.emily.commands;

import roujo.emily.Context;

public class CommandHandler {
	public static boolean processMessage(Context context) {
		String message = context.getMessage();
		if(!context.isPrivateMessage())
			message = removeMessageFlags(context, message);
		String[] messageParts = splitCommandFromArguments(message);
		
		Command command = CommandType.getByName(messageParts[0]);
		
		if(command == null || !command.isValidSender(context.getUser()))
			return false;
		else
			return command.execute(context, messageParts[1]);
	}

	private static String removeMessageFlags(Context context, String message) {
		String botNick = context.getBot().getNick();
		if (message.startsWith("%")) {
			// Emily was called by prefix
			message = message.substring(1);
		} else if (message.startsWith(botNick)) {
			// Emily was reffered to by name
			int nickLength = botNick.length();
			// NickLength + 2, so that messages like "Emily, q" or "Emily: d"
			// work as intended
			if (message.length() > nickLength + 2) {
				if (message.charAt(nickLength) == ','
						|| message.charAt(nickLength) == ':') {
					message = message.substring(nickLength + 2);
				}
			}
		}
		return message;
	}

	private static String[] splitCommandFromArguments(String message) {
		int firstSpace = message.indexOf(' ');
		boolean hasArgs = firstSpace != -1 && message.length() > firstSpace;
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
