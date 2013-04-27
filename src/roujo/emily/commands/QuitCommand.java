package roujo.emily.commands;

import roujo.emily.Context;
import roujo.emily.Emily;

public class QuitCommand extends Command {

	protected QuitCommand() {
		super("quit", "Tells Emily to disconnect from the server.", "quit", true);
	}

	@Override
	public boolean execute(Context context) {
		if(!isValidSender(context.getSender()))
			return false;
		
		Emily emily = context.getEmily();
		sendMessageBack(context, "Alright, off I go!");
		emily.getState().setShouldQuit(true);
		return true;
	}

}