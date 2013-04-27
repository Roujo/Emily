package roujo.emily.commands;

import roujo.emily.Context;
import roujo.emily.Emily;

public class QuitCommand extends Command {

	protected QuitCommand() {
		super("quit", "Tells Emily to disconnect from the server.", "quit", true);
	}

	@Override
	public boolean execute(Context context, String arguments) {
		Emily emily = context.getEmily();
		sendMessageBack(context, "Alright, off I go!");
		emily.getState().setShouldQuit(true);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		emily.quitServer("Later!");
		return true;
	}

}
