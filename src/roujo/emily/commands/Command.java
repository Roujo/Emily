package roujo.emily.commands;

public abstract class Command {
	private final String name;
	private final String description;
	private final boolean isSuperUserOnly;
	
	protected Command(String name, String description, boolean isSuperUserOnly) {
		this.name = name;
		this.description = description;
		this.isSuperUserOnly = isSuperUserOnly;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isSuperUserOnly() {
		return isSuperUserOnly;
	}
	
	public abstract void execute(String sender, String arguments);
}
