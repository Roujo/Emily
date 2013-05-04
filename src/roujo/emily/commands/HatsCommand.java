package roujo.emily.commands;

import roujo.emily.Context;

public class HatsCommand extends Command {

	protected HatsCommand() {
		super("hats", ".*hats.*", "Who knows?", "hats", false);
	}

	@Override
	public boolean execute(Context context) {
		sendMessageBack(context, "no u");
		return true;
	}
	
	
}
