package roujo.emily;

public class Context {
	private final Emily emily;
	private final String channel, sender, message;

	public Context(Emily emily, String channel, String sender, String message) {
		this.emily = emily;
		this.channel = channel;
		this.sender = sender;
		this.message = message;
	}

	public Context(Emily emily, String sender, String message) {
		this(emily, "", sender, message);
	}
	
	public Emily getEmily() {
		return emily;
	}

	public String getChannel() {
		return channel;
	}

	public String getSender() {
		return sender;
	}

	public String getMessage() {
		return message;
	}
	
	public boolean isPrivateMessage() {
		return channel.equals("");
	}
}
