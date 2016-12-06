package beans;

public class UserProfile
{
	private String name;
	private String username;
	
	public UserProfile() {
		name = null;
		username = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return this.username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

}
