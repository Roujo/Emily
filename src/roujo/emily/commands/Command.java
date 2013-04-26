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
	
	public boolean isValidSender(String sender) {
		User user = User.getUserByNick(sender);
		if(isSuperUserOnly) {
			return user != null && user.getStatus() == User.Status.Super;
		} else {
			return user == null || user.getStatus() != User.Status.BlackListed;
		}
	}
	
	public void LogError(Context context, String message) {
		context.getEmily().sendMessage(context.getSender(), message);
	}
}
