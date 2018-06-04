package packageProyecto1;

public class Usuario {
	public String username;
	public String password;
	
	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String toString() {
		return username+"  "+password;
	}
}
