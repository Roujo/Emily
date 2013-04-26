package roujo.emily.commands;

import java.util.HashMap;
import java.util.Map;

public enum User {
	Skynet(Status.Super, new String[]{"Skynet", "Skynet|Work"});
	
	public enum Status { Super, Regular, BlackListed }
	
	public static Map<String, User> NICK_MAP = new HashMap<String, User>();
	
	public static User getUserByNick(String nick) {
		return NICK_MAP.get(nick);
	}
	
	public static void registerNicks(User user, String[] nicks) {
		for(String nick : nicks)
			NICK_MAP.put(nick, user);
	}
	
	private Status status;
	private String[] nicks;
	
	private User(Status status, String[] nicks) {
		this.status = status;
		this.nicks = nicks;
		registerNicks(this, nicks);
	}
	
	private User(Status status, String nick) {
		this(status, new String[]{ nick });
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String[] getAliases() {
		return nicks;
	}

	public void setAliases(String[] aliases) {
		this.nicks = aliases;
	}
}
