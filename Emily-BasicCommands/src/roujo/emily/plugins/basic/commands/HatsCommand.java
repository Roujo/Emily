package roujo.emily.plugins.basic.commands;

import roujo.emily.MessageContext;
import roujo.emily.commands.Command;

public class HatsCommand extends Command {

	protected HatsCommand() {
		super("hats", ".*hats.*", "Who knows?", "hats", false);
	}

	@Override
	public boolean execute(MessageContext context) {
		sendMessageBack(context, "no u");
		return true;
	}
	
	
}
