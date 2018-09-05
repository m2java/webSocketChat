package dbService.entity;

public class UserProfile {
	private final String login;
	private final String pass;

	public UserProfile(String login, String pass) {
		this.login = login;
		this.pass = pass;
	}

	public String getLogin() {
		return login;
	}

	public String getPass() {
		return pass;
	}
	
	@Override
	public String toString(){
		return "LOGIN- "+login+"\n"+"PASSWORD- "+pass;
	}
	
}
