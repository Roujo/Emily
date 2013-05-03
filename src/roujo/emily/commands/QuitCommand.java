package roujo.emily.commands;

import org.pircbotx.PircBotX;

import roujo.emily.Context;

public class QuitCommand extends Command {

	protected QuitCommand() {
		super("quit", "Tells Emily to disconnect from the server.", "quit", true);
	}

	@Override
	public boolean execute(Context context, String arguments) {
		PircBotX bot = context.getBot();
		sendMessageBack(context, "Alright, off I go!");
		context.getState().setShouldQuit(true);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		bot.quitServer("Later!");
		return true;
	}

}
