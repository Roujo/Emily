package roujo.emily.commands;

import java.util.HashMap;
import java.util.Map;

public enum User {
	Skynet(Status.Super, new String[] { "Skynet", "Skynet|Work" });

	public enum Status {
		Owner(10), Super(5), Regular(0), BlackListed(-1);
		
		private int rank;
		
		private Status(int rank) {
			this.rank = rank;
		}
		
		public int getRank() {
			return rank;
		}
	}

	public static Map<String, User> NICK_MAP = new HashMap<String, User>();

	public static User getUserByNick(String nick) {
		return NICK_MAP.get(nick);
	}

	public static boolean isSuper(String nick) {
		User user = getUserByNick(nick);
		return user != null && user.isSuper();
	}
	
	public static boolean isOwner(String nick) {
		User user = getUserByNick(nick);
		return user != null && user.isOwner();
	}
	
	public static boolean isBlackListed(String nick) {
		User user = getUserByNick(nick);
		return user != null && user.isBlackListed();
	}

	public static void registerNicks(User user, String[] nicks) {
		for (String nick : nicks)
			NICK_MAP.put(nick, user);
	}

	private final Status status;
	private final String[] nicks;

	private User(Status status, String[] nicks) {
		this.status = status;
		this.nicks = nicks;
		registerNicks(this, nicks);
	}

	private User(Status status, String nick) {
		this(status, new String[] { nick });
	}

	public Status getStatus() {
		return status;
	}

	public String[] getAliases() {
		return nicks;
	}
	
	public boolean isOwner() {
		return status.rank >= Status.Owner.rank;
	}
	
	public boolean isSuper() {
		return status.rank >= Status.Super.rank;
	}
	
	public boolean isBlackListed() {
		return status.rank <= Status.BlackListed.rank;
	}
}
