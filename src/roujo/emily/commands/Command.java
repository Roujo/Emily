package roujo.emily.commands;

import roujo.emily.Context;

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
	
	public abstract boolean execute(Context context);
	
	protected boolean isValidSender(String sender) {
		User user = User.getUserByNick(sender);
		if(isSuperUserOnly) {
			return user != null && user.isSuper();
		} else {
			return user == null || !user.isBlackListed();
		}
	}
	
	protected void logError(Context context, String message) {
		context.getEmily().sendMessage(context.getSender(), message);
	}
	
	protected void sendMessageBack(Context context, String message) {
		// Add a little love
		User user = context.getUser();
		if(user != null && user.isOwner())
			message += " <3";
		
		context.getEmily().sendMessage(context.isPrivateMessage() ? context.getSender() : context.getChannel(), message);
	}
}
