package roujo.emily.commands;

import org.pircbotx.PircBotX;

import roujo.emily.Context;
import roujo.emily.util.StringHelper;

public class JoinCommand extends Command {

	protected JoinCommand() {
		super("join", "Joins the given channel.", "join #channel", true);
	}
	
	@Override
	public boolean execute(Context context, String arguments) {
		// Validating arguments
		String[] channels = StringHelper.keepChannels(arguments.split(" "));
		if(channels.length == 0) {
			logError(context, "No valid channels were passed.");
			return false;
		}
		
		PircBotX bot = context.getBot();
		sendMessageBack(context, "Sounds like a plan!");
		for(String channel : channels)
			bot.joinChannel(channel);
		
		return true;
	}

}
