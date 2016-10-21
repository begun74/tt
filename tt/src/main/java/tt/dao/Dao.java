package tt.dao;

import java.util.List;

import tt.model.User;

public interface Dao {
	
	public List<User> getUserList();
	public void addUser(User user);

}
