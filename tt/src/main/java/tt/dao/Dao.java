package tt.dao;

import java.util.List;

import tt.model.DirNomenclature;
import tt.model.DirProvider;
import tt.model.User;

public interface Dao {
	
	public List<User> getUserList();
	public void addUser(User user);


	public List<DirProvider> getProviderList();
	public void addProvider(DirProvider dirProvider);
	public DirProvider getProvider(long id);
	

	public List<DirNomenclature> getNomenclatureList();
	public void addNomenclature(DirNomenclature dirNomenclature);
	public DirNomenclature getNomenclature(long id);
	
	
	public Object getObject(Class clazz,long id);
	public void delObject(Object obj);
	
	
}
