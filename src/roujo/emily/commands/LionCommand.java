package roujo.emily.commands;

import org.pircbotx.PircBotX;

import roujo.emily.Context;

public class LionCommand extends Command {
	protected LionCommand() {
		super(">:3", ".*>:3.*", "...is that a lion?",
				">:3]", false);
	}

	@Override
	public boolean execute(Context context) {
		String target = context.isPrivateMessage() ? context.getUser().getNick() : context.getChannel().getName();
		PircBotX bot = context.getBot();
		bot.sendMessage(target, "OMG IT'S A LION GET IN THE CAR!");
		bot.sendAction(target, "gets in the car.");
		return true;
	}
}
