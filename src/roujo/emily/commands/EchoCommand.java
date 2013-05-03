package roujo.emily.commands;

import roujo.emily.Context;

public class EchoCommand extends Command {
	
	protected EchoCommand() {
		super("echo", "Repeats after you.",
				"echo something", false);
	}

	@Override
	public boolean execute(Context context, String arguments) {
		if(context.isPrivateMessage())
			context.getBot().sendMessage(context.getUser(), arguments);
		else
			context.getBot().sendMessage(context.getChannel(), arguments);
		return true;
	}

}
