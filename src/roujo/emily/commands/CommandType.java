package roujo.emily.commands;

public enum CommandType {
	Join(new JoinCommand()),
	Part(new PartCommand()),
	Quit(new QuitCommand()),
	Tell(new TellCommand()),
	
	Time(new TimeCommand()),
	Roll(new RollCommand()),
	Hats(new HatsCommand()),
	Echo(new EchoCommand()),
	
	Voice(new VoiceCommand());
	
	public static Command getByName(String name) {
		for(CommandType type : CommandType.values())
			if(type.getCommand().getName().equals(name))
				return type.getCommand();
		return null;
	}
	
	private final Command command;
	
	private CommandType(Command command) {
		this.command = command;
	}
	
	public Command getCommand() {
		return command;
	}
}
