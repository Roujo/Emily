package roujo.emily.commands;
import roujo.emily.Context;
import roujo.emily.Emily;


public class TellCommand extends Command {
	protected TellCommand() {
		super("tell", "Tells something to a certain channel or user.",
				"tell [#channel|user] something", true);
	}

	@Override
	public boolean execute(Context context, String arguments) {
		int firstSpace = arguments.indexOf(' ');
		if(firstSpace == -1) {
			sendUsageBack(context);
		}
		
		sendMessageBack(context, "Alright!");
		
		String target = arguments.substring(0, firstSpace);
		String message = arguments.substring(firstSpace + 1).trim();
		
		Emily emily = context.getEmily();
		emily.sendMessage(target, message);
		
		return true;
	}

}
