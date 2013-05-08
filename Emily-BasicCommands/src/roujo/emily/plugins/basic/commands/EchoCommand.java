package roujo.emily.plugins.basic.commands;

import roujo.emily.MessageContext;
import roujo.emily.commands.Command;

public class EchoCommand extends Command {

	protected EchoCommand() {
		super("Echo", "echo (?<args>.*)", "Repeats after you.", "echo something or other", false);
	}

	@Override
	public boolean execute(MessageContext context) {
		String echoMessage = getArguments(context);
		if (context.isPrivateMessage())
			context.getBot().sendMessage(context.getUser(), echoMessage);
		else
			context.getBot().sendMessage(context.getChannel(), echoMessage);
		return true;
	}

}
