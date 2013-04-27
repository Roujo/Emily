package roujo.emily;

import org.jibble.pircbot.PircBot;

import roujo.emily.commands.CommandHandler;

public class Emily extends PircBot {
	private State state;
	private String password;

	public Emily(String nickname, String password) {
		this.password = password;
		this.state = new State();
		
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

		message = formatInboundMessage(message);

		if (!message.equals("")) {
			Context context = new Context(this, channel, sender, message);
			CommandHandler.processMessage(context);
		}
	}

	@Override
	protected void onPrivateMessage(String sender, String login,
			String hostname, String message) {
		if (state.shouldQuit())
			return;

		Context context = new Context(this, sender, message);
		CommandHandler.processMessage(context);
	}

	public void onConsoleMessage(String message) {
		Context context = new Context(this, getNick(), message);
		CommandHandler.processMessage(context);
	}
	
	@Override
	protected void onConnect() {
		if(!password.equals(""))
			identify(password);
	};
	
	public State getState() {
		return state;
	}
	
	private String formatInboundMessage(String message) {
		boolean isInboundMessage = false;
		if (message.startsWith("%")) {
			// Emily was called by prefix
			isInboundMessage = true;
			message = message.substring(1);
		} else if (message.startsWith(getNick())) {
			// Emily was reffered to by name
			int nickLength = getNick().length();
			// NickLength + 2, so that messages like "Emily, q" or "Emily: d"
			// work as intended
			if (message.length() > nickLength + 2) {
				if (message.charAt(nickLength) == ','
						|| message.charAt(nickLength) == ':') {
					isInboundMessage = true;
					message = message.substring(nickLength + 2);
				}
			}
		}
		return isInboundMessage ? message : "";
	}
}
