package roujo.emily.commands;

import org.pircbotx.User;

import roujo.emily.Context;
import roujo.emily.util.InternalUser;
import roujo.emily.util.UserHelper;

public abstract class Command {
	private final String name;
	private final String description;
	private final String usage;
	private final boolean isSuperUserOnly;
	
	protected Command(String name, String description, String usage, boolean isSuperUserOnly) {
		this.name = name;
		this.description = description;
		this.usage = usage;
		this.isSuperUserOnly = isSuperUserOnly;
	}

	public String getName() {
		return name;
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
	
	public abstract boolean execute(Context context, String arguments);
	
	protected boolean isValidSender(User user) {
		return isValidSender(user, UserHelper.getUserByNick(user.getNick()));
	}
	
	protected boolean isValidSender(User user, InternalUser internalUser) {
		if(isSuperUserOnly) {
			return internalUser != null && internalUser.isSuper() && user.isVerified();
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
		if(user != null && user.isOwner())
			message += " <3";
		
		if(context.isPrivateMessage())
			context.getBot().sendMessage(context.getUser(), message);
		else
			context.getBot().sendMessage(context.getChannel(), context.getUser() + ": " + message);
	}
	
	protected void sendUsageBack(Context context) {
		sendMessageBack(context, usage);
	}
}
