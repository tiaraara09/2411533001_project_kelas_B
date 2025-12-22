package DAO;

import model.User;

public interface UserDao {
	void save (User user);
	public void update(User user);
	boolean check(User user);
}
