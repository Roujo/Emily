package roujo.emily.commands;

import roujo.emily.Context;
import roujo.emily.Emily;
import roujo.emily.util.StringValidator;

public class JoinCommand extends Command {

	protected JoinCommand() {
		super("join", "Joins the given channel.", "join #channel", true);
	}
	
	@Override
	public boolean execute(Context context) {
		String sender = context.getSender();
		// Checking sender
		if(!isValidSender(sender))
			return false;
		
		// Validating arguments
		String[] channels = StringValidator.keepChannels(context.getMessage().split(" "));
		if(channels.length == 0) {
			logError(context, "No valid channels were passed.");
			return false;
		}
		
		Emily emily = context.getEmily();
		sendMessageBack(context, "Sounds like a plan!");
		for(String channel : channels)
			emily.joinChannel(channel);
		
		return true;
	}

}
