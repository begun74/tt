package tt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tt.dao.Dao;
import tt.dao.DaoImpl;
import tt.model.User;


@Service("ttService")
@Transactional()
public class TTServiceImpl implements Dao {

	@Autowired
	private DaoImpl dao;
	
	
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return dao.getUserList();
	}


	public void addUser(User user) {
		// TODO Auto-generated method stub
		dao.addUser(user);
	}

}
