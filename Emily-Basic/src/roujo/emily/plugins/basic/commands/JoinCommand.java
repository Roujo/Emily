package roujo.emily.plugins.basic.commands;

import org.pircbotx.PircBotX;

import roujo.emily.core.MessageContext;
import roujo.emily.core.commands.Command;
import roujo.emily.core.util.StringHelper;

public class JoinCommand extends Command {

	public JoinCommand() {
		super("join", "join (?<args>.*)", "Joins the given channel.", "join #channel", true);
	}
	
	@Override
	public boolean execute(MessageContext context) {
		// Validating arguments
		String[] channels = StringHelper.keepChannels(getArguments(context).split(" "));
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
