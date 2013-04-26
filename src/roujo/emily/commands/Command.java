package roujo.emily.commands;

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
	
	public abstract void execute(String sender, String arguments);
	
	public boolean isValidSender(String sender) {
		User user = User.getUserByNick(sender);
		if(isSuperUserOnly) {
			return user != null && user.getStatus() == User.Status.Super;
		} else {
			return user == null || user.getStatus() != User.Status.BlackListed;
		}
	}
}
