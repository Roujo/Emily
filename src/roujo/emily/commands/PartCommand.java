package roujo.emily.commands;

import roujo.emily.Context;
import roujo.emily.Emily;
import roujo.emily.util.StringHelper;

public class PartCommand extends Command {

	protected PartCommand() {
		super("part", "Parts with the given channel", "part #channel reason", true);
	}

	@Override
	public boolean execute(Context context) {
		String message = context.getMessage();
		if(message.equals("")) {
			sendUsageBack(context);
			return false;
		}
		
		String[] args = context.getMessage().split(" ");
		if(!StringHelper.isChannel(args[0])) {
			sendUsageBack(context);
			return false;
		}
		
		Emily emily = context.getEmily();
		sendMessageBack(context, "Alright!");
		emily.sendMessage(args[0], "See you later!");
		if(args.length > 1)
			emily.partChannel(args[0], args[1]);
		else
			emily.partChannel(args[0], "Off and away...");
		return true;
	}

	
}
