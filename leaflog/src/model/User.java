package model;

public class User {
	public int id;
	public String nama;
	public String username;
	public String password;
	
	public User() {};
	
	public User(int id, String nama, String password) {
		this.id = id;
		this.nama = nama;
		this.username = username;
		this.password = password;		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
