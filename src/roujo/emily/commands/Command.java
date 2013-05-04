package roujo.emily.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import roujo.emily.Context;
import roujo.emily.util.InternalUser;
import roujo.emily.util.StringHelper;

public abstract class Command {
	private final String name;
	private final Pattern pattern;
	private final String description;
	private final String usage;
	private final boolean isSuperUserOnly;

	protected Command(String name, String pattern, String description,
			String usage, boolean isSuperUserOnly) {
		this.name = name;
		this.pattern = Pattern.compile(pattern);
		this.description = description;
		this.usage = usage;
		this.isSuperUserOnly = isSuperUserOnly;
	}

	public String getName() {
		return name;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public String getDescription() {
		return description;
	}

	public String getUsage() {
		return usage;
	}

	public boolean isSuperUserOnly() {
		return isSuperUserOnly;
	}

	public abstract boolean execute(Context context);

	protected String getArguments(Context context) {
		String strippedMessage;
		if (context.hasBeenProcessed()) {
			strippedMessage = context.getProcessedMessage();
		} else {
			strippedMessage = StringHelper
					.getPatternFromNick(context.getBot().getNick())
					.matcher(context.getMessage()).group(1);
		}
		Matcher matcher = getPattern().matcher(strippedMessage);
		if(matcher.matches())
			return matcher.group("args");
		else
			return "";
	}

	protected boolean isValidSender(Context context) {
		InternalUser internalUser = context.getInternalUser();
		if (isSuperUserOnly) {
			return internalUser != null && internalUser.isSuper();
		} else {
			return internalUser == null || !internalUser.isBlackListed();
		}
	}

	protected void logError(Context context, String message) {
		context.getBot().sendMessage(context.getUser(), message);
	}

	protected void sendMessageBack(Context context, String message) {
		// Add a little love
		InternalUser user = context.getInternalUser();
		if (user != null && user.isOwner())
			message += " <3";

		if (context.isPrivateMessage())
			context.getBot().sendMessage(context.getUser(), message);
		else
			context.getBot().sendMessage(context.getChannel(),
					context.getUser().getNick() + ": " + message);
	}

	protected void sendUsageBack(Context context) {
		sendMessageBack(context, usage);
	}
}
