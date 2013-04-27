package roujo.emily.commands;

public enum CommandType {
	Join(new JoinCommand()),
	Part(new PartCommand()),
	Quit(new QuitCommand()),
	
	Time(new TimeCommand()),
	Roll(new RollCommand()),
	Hats(new HatsCommand());
	
	
	private final Command command;
	
	private CommandType(Command command) {
		this.command = command;
	}
	
	public Command getCommand() {
		return command;
	}
}
