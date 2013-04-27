package roujo.emily.commands;

import java.util.Random;

import roujo.emily.Context;
import roujo.emily.util.StringHelper;

public class RollCommand extends Command {

	protected RollCommand() {
		super("roll", "Rolls a certain amount of dice and gives the result.",
				"dice 2d20 [#channel]", false);
	}

	@Override
	public boolean execute(Context context, String arguments) {
		if (arguments.equals("")) {
			sendUsageBack(context);
			return false;
		}

		String[] args = arguments.split(" ");
		if (!args[0].matches("[0-9]+d[0-9]+")) {
			sendUsageBack(context);
			return false;
		}

		String[] diceParts = args[0].split("d");
		int diceNumber = Integer.parseInt(diceParts[0]);
		int diceType = Integer.parseInt(diceParts[1]);
		Random random = new Random();
		int total = 0;
		for (int i = 0; i < diceNumber; ++i) {
			total += random.nextInt(diceType) + 1;
		}
		String response = "Results of " + args[0] + ": " + total;
		
		if(args.length > 1) {
			if(!StringHelper.isChannel(args[1])) {
				sendUsageBack(context);
				return false;
			}
			context.getEmily().sendMessage(args[1], response);
		} else {
			sendMessageBack(context, response);
		}
		return true;
	}

}
