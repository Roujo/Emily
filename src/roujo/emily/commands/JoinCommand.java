package roujo.emily.commands;

import roujo.emily.Context;
import roujo.emily.Emily;
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
		
		Emily emily = context.getEmily();
		sendMessageBack(context, "Sounds like a plan!");
		for(String channel : channels)
			emily.joinChannel(channel);
		
		return true;
	}

}
