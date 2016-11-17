package beans;

public class UserProfile
{
	private String account;
	private String username;
	
	public UserProfile() {
		account = null;
		username = null;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return this.username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

}
