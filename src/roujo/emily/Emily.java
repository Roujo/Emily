package roujo.emily;

import java.util.Random;

import org.jibble.pircbot.PircBot;

public class Emily extends PircBot {
	private State state;
	private String password;

	public Emily(String nickname, String password) {
		this.password = password;
		
		setName(nickname);
		setAutoNickChange(true);
		setLogin("Emily");
		setVersion("Emily v0.1 | IRC Bot based on PircBot 1.5.0");
	}

	@Override
	public void onMessage(String channel, String sender, String login,
			String hostname, String message) {
		if (state.shouldQuit())
			return;

		boolean isValidMessage = false;
		if (message.startsWith("%")) {
			// Emily was called by prefix
			isValidMessage = true;
			message = message.substring(1);
		} else if (message.startsWith(getNick())) {
			// Emily was reffered to by name
			int nickLength = getNick().length();
			// NickLength + 2, so that messages like "Emily, q" or "Emily: d"
			// work as intended
			if (message.length() > nickLength + 2) {
				if (message.charAt(nickLength) == ','
						|| message.charAt(nickLength) == ':') {
					isValidMessage = true;
					message = message.substring(nickLength + 2);
				}
			}
		}

		if (isValidMessage) {
			String answer = processMessage(sender, message, false);
			if (!answer.equals(""))
				sendMessage(channel, answer);
			postProcessMessage();
		}
	}

	@Override
	protected void onPrivateMessage(String sender, String login,
			String hostname, String message) {
		if (state.shouldQuit())
			return;

		String answer = processMessage(sender, message, true);
		if (!answer.equals(""))
			sendMessage(sender, answer);
		postProcessMessage();
	}

	public void onConsoleMessage(String message) {
		String answer = processMessage("Console", message, true);
		if (!answer.equals(""))
			System.out.println("Emily: " + answer);
		postProcessMessage();
	}
	
	public State getState() {
		return state;
	}
	
	@Override
	protected void onConnect() {
		if(!password.equals(""))
			identify(password);
	};

	private String processMessage(String sender, String message,
			boolean isPrivate) {
		String response = "";

		int firstSpace = message.indexOf(' ');
		boolean hasArgs = firstSpace != -1 && message.length() > firstSpace + 2;
		String command, restOfMessage;
		if (hasArgs) {
			command = message.substring(0, firstSpace);
			restOfMessage = message.substring(firstSpace + 1);
		} else {
			command = message;
			restOfMessage = "";
		}
		command = command.toLowerCase();

		boolean isSuperUser = sender.equals("Console")
				|| sender.equals("Skynet")
				|| sender.equals("Skynet|Work");
		// SuperUser commands only
		if (isSuperUser) {
			switch (command) {
			case "join":
				response = "Sounds like a plan!";
				joinChannel(restOfMessage);
				break;
			case "part":
				response = "Alright";
				partChannel(restOfMessage);
				break;
			case "say":
				int separator = restOfMessage.indexOf(' ');
				response = "I'll just go and do that!";
				sendMessage(restOfMessage.substring(0, separator), restOfMessage.substring(separator + 1));
				break;
			}
		}
		if(response.equals("")){
			switch (command) {
			case "help":
				response = "Here are a few commands you can try: time, hats, roll";
				break;
			case "time":
				String time = new java.util.Date().toString();
				response = "The time is now " + time;
				break;
			case "hats":
				response = "no u";
				break;
			case "roll":
				if (!hasArgs || !restOfMessage.matches("[0-9]+d[0-9]+")) {
					response = "Usage: roll 2d6";
				} else {
					String[] diceParts = restOfMessage.split("d");
					int diceNumber = Integer.parseInt(diceParts[0]);
					int diceType = Integer.parseInt(diceParts[1]);
					Random random = new Random();
					int total = 0;
					for (int i = 0; i < diceNumber; ++i) {
						total += random.nextInt(diceType) + 1;
					}
					response = "Results of " + restOfMessage + ": " + total;
				}
				break;
			case "quit":
				if (!isSuperUser) {
					response = "Nice try. ;)";
				} else {
					response = "Alright!";
					state.setShouldQuit(true);
				}
				break;
			default:
				response = "I don't know how to do that. Try \"help\"";
				break;
			}
		}

		// A little bit of love
		if (isSuperUser)
			response += " <3";

		return response;
	}

	private void postProcessMessage() {
		if (state.shouldQuit()) {
			for (String channel : getChannels())
				sendMessage(channel, "Cya later, boys!");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			quitServer();
		}
	}
}
