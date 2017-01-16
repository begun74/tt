package tt.dao;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;

import tt.model.DirGender;
import tt.model.DirNomenclGroup;
import tt.model.DirNomenclGroupRoot;
import tt.model.DirNomenclature;
import tt.model.DirProvider;
import tt.model.Order;
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
	public Set<DirNomenclature> getTailsNomenclatureSet(Tail tail_example, Collection<Criterion> criterions ,int p);


	public List<DirNomenclGroup> getNomenclGroupList();
	public void addNomenclGroup(DirNomenclGroup dirNomenclGroup);

	public List<DirNomenclGroupRoot> getNomenclGroupRootList();
	public void addNomenclGroupRoot(DirNomenclGroupRoot dirNomenclGroupRoot);

	public List<DirGender> getGenderList();
	public DirGender getGenderByName(String name);

	
	public void addTail(Tail tail);
	public List<Tail> getTailsList();
	public List<Tail> getTailsList(Tail tail_example, Collection<Criterion> criterions, int p );
	public List<Tail> getTailsList(long id_dirNomenclature);

	public List<Store> getStoreList();
	public void addStore(Store store);
	//public Store getStore(long id);
	public Store getStoreBySerVerUID(Long serialVersionUID);
	
	
	public List<OrderItems> getOrderItems(Long order);
	
	public void addOrder(Order order);
	public List<Order>getOrdersList();
	
	public Object getObject(Class clazz,Long id);
	public void delObject(Object obj);
	
	
}
