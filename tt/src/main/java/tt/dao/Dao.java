package tt.dao;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;

import tt.model.DirGender;
import tt.model.DirNomenclGroup;
import tt.model.DirNomenclGroupRoot;
import tt.model.DirNomenclature;
import tt.model.DirProvider;
import tt.model.OrderItems;
import tt.model.Store;
import tt.model.Tail;
import tt.model.User;

public interface Dao {
	
	public List<User> getUserList();
	public void addUser(User user);


	public List<DirProvider> getProviderList();
	public void addProvider(DirProvider dirProvider);
	public DirProvider getProviderByCode(Integer code);
	

	public List<DirNomenclature> getNomenclatureList();
	public List<DirNomenclature> getNomenclatureList(Collection<Criterion> criterions);
	public void addNomenclature(DirNomenclature dirNomenclature);


	public List<DirNomenclGroup> getNomenclGroupList();
	public void addNomenclGroup(DirNomenclGroup dirNomenclGroup);

	public List<DirNomenclGroupRoot> getNomenclGroupRootList();
	public void addNomenclGroupRoot(DirNomenclGroupRoot dirNomenclGroupRoot);

	public List<DirGender> getGenderList();
	public DirGender getGenderByName(String name);

	
	public void addTail(Tail tail);
	public List<Tail> getTailsList();
	public List<Tail> getTailsList(Tail tail_example, Collection<Criterion> criterions );
	public List<Tail> getTailsList(long id_dirNomenclature);

	public List<Store> getStoreList();
	public void addStore(Store store);
	//public Store getStore(long id);
	public Store getStoreBySerVerUID(Long serialVersionUID);
	
	
	public void addOrder(OrderItems order);
	
	
	
	public Object getObject(Class clazz,Long id);
	public void delObject(Object obj);
	
	
}
