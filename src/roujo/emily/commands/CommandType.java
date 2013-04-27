package roujo.emily.commands;

import roujo.emily.Context;

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

	public boolean execute(Context context) {
		if(command.isValidSender(context.getSender()))
			return false;
		else
			return command.execute(context);
	}
}
