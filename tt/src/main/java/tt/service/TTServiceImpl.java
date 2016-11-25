package tt.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tt.dao.Dao;
import tt.dao.DaoImpl;
import tt.model.DirNomenclGroup;
import tt.model.DirNomenclature;
import tt.model.DirProvider;
import tt.model.Store;
import tt.model.Tail;
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


	@Override
	public List<DirProvider> getProviderList() {
		// TODO Auto-generated method stub
		return dao.getProviderList();
	}


	@Override
	public void addProvider(DirProvider dirProvider) {
		// TODO Auto-generated method stub
		dao.addProvider(dirProvider);
	}




	@Override
	public Object getObject(Class clazz, BigInteger id) {
		// TODO Auto-generated method stub
		return dao.getObject(clazz, id);
	}


	@Override
	public void delObject(Object obj) {
		// TODO Auto-generated method stub
		dao.delObject(obj);
	}


	@Override
	public List<DirNomenclature> getNomenclatureList() {
		// TODO Auto-generated method stub
		return dao.getNomenclatureList();
	}


	@Override
	public void addNomenclature(DirNomenclature dirNomenclature) {
		// TODO Auto-generated method stub
		dao.addNomenclature(dirNomenclature);
	}



	@Override
	public List<Store> getStoreList() {
		// TODO Auto-generated method stub
		return dao.getStoreList();
	}


	@Override
	public void addStore(Store store) {
		// TODO Auto-generated method stub
		dao.addStore(store);
	}


	/*@Override
	public Store getStore(long id) {
		// TODO Auto-generated method stub
		return dao.getStore(id);
	}
	*/

	@Override
	public Store getStoreBySerVerUID(Long serialVersionUID) {
		// TODO Auto-generated method stub
		return dao.getStoreBySerVerUID(serialVersionUID);
	}


	@Override
	public List<Tail> getTailsList() {
		// TODO Auto-generated method stub
		return dao.getTailsList();
	}


	@Override
	public void addTail(Tail tail) {
		// TODO Auto-generated method stub
		dao.addTail(tail);
	}


	@Override
	public DirProvider getProviderByCode(Integer code) {
		// TODO Auto-generated method stub
		return dao.getProviderByCode(code);
	}


	@Override
	public List<DirNomenclGroup> getNomenclGroupList() {
		// TODO Auto-generated method stub
		return dao.getNomenclGroupList();
	}


	@Override
	public void addNomenclGroup(DirNomenclGroup dirNomenclGroup) {
		dao.addNomenclGroup(dirNomenclGroup);
	}

}
