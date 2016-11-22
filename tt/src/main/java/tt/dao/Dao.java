package tt.dao;

import java.math.BigInteger;
import java.util.List;

import tt.model.DirNomenclature;
import tt.model.DirProvider;
import tt.model.Store;
import tt.model.User;

public interface Dao {
	
	public List<User> getUserList();
	public void addUser(User user);


	public List<DirProvider> getProviderList();
	public void addProvider(DirProvider dirProvider);
	//public DirProvider getProvider(BigInteger id);
	

	public List<DirNomenclature> getNomenclatureList();
	public void addNomenclature(DirNomenclature dirNomenclature);
	//public DirNomenclature getNomenclature(BigInteger id);
	

	public List<Store> getStoreList();
	public void addStore(Store store);
	//public Store getStore(long id);
	public Store getStoreBySerVerUID(long serialVersionUID);
	
	
	public Object getObject(Class clazz,BigInteger id);
	public void delObject(Object obj);
	
	
}
