package roujo.emily.commands;

import roujo.emily.Context;

public class EchoCommand extends Command {

	protected EchoCommand() {
		super("Echo", "echo (?<args>.*)", "Repeats after you.", "echo something or other", false);
	}

	@Override
	public boolean execute(Context context) {
		String echoMessage = getArguments(context);
		if (context.isPrivateMessage())
			context.getBot().sendMessage(context.getUser(), echoMessage);
		else
			context.getBot().sendMessage(context.getChannel(), echoMessage);
		return true;
	}

}
