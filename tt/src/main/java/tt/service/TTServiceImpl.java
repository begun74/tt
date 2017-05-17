package tt.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tt.annotation.CheckAccess;
import tt.annotation.Loggable;
import tt.dao.Dao;
import tt.dao.DaoImpl;
import tt.model.ContactUsMessages;
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



@Service("ttService")
@Transactional()
public class TTServiceImpl implements Dao {

	@Autowired
	private DaoImpl dao;
	
	private  static  final  Log  LOG  =  LogFactory.getLog(TTServiceImpl.class);
	
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
		List<DirProvider> list = dao.getProviderList();
		
		return list;
	}

	@Override
	public List<DirProvider> getProviderListInTails() {
		// TODO Auto-generated method stub
		return dao.getProviderListInTails();
	}


	@Override
	public void addProvider(DirProvider dirProvider) {
		// TODO Auto-generated method stub
		dao.addProvider(dirProvider);
	}



	//@CheckAccess
	@Override
	public Object getObject(Class clazz, Long id) {
		// TODO Auto-generated method stub
		return dao.getObject(clazz, id);
	}


	@Override
	public void delObject(Object obj) {
		// TODO Auto-generated method stub
		dao.delObject(obj);
		LOG.info(this.getClass() +".delObject('"+obj+"')");
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



	@Override
	public Store getStoreBySerVerUID(Long serialVersionUID) {
		// TODO Auto-generated method stub
		return dao.getStoreBySerVerUID(serialVersionUID);
	}

	//@Loggable
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
	public List<DirNomenclGroup> getNomenclGroupListInTails() {
		// TODO Auto-generated method stub
		return dao.getNomenclGroupListInTails();
	}

	@Override
	public void addNomenclGroup(DirNomenclGroup dirNomenclGroup) {
		dao.addNomenclGroup(dirNomenclGroup);
	}


	@Override
	public List<DirGender> getGenderList() {
		// TODO Auto-generated method stub
		return dao.getGenderList();
	}


	@Override
	public List<DirNomenclGroupRoot> getNomenclGroupRootList() {
		// TODO Auto-generated method stub
		return dao.getNomenclGroupRootList();
	}

	@Override
	public List<DirNomenclGroupRoot> getNomenclGroupRootListInTails() {
		// TODO Auto-generated method stub
		return dao.getNomenclGroupRootListInTails();
	}

	@Override
	public void addNomenclGroupRoot(DirNomenclGroupRoot dirNomenclGroupRoot) {
		// TODO Auto-generated method stub
		dao.addNomenclGroupRoot(dirNomenclGroupRoot);
	}

	
	public List<Tail> getTailsList(List<Long> providers, List<Long> genders,  List<Long> categories, int p) {
		// TODO Auto-generated method stub
		List<DirProvider> lProvs = new LinkedList<DirProvider>();
		List<DirNomenclGroup> lNomGroup = new LinkedList<DirNomenclGroup>();
		List<DirGender> lGens = new LinkedList<DirGender>();
		
		
		Tail tail = new Tail();
		Collection<Criterion> criterions = new LinkedList<Criterion>();
		Collection<Criterion> criterDN = new LinkedList<Criterion>();
		
		for(Long idNomGroup: categories)
			lNomGroup.add((DirNomenclGroup)dao.getObject(DirNomenclGroup.class, idNomGroup));
					
					
		for(Long idProv: providers)
			lProvs.add((DirProvider) dao.getObject(DirProvider.class, idProv));
		
		for(Long idGndr: genders)
		{
			DirGender DG = (DirGender) dao.getObject(DirGender.class, idGndr);
			criterDN.add( Restrictions.eq("dirGender", DG));
		}
		
		
		if(!lNomGroup.isEmpty())
			criterDN.add(Restrictions.in("dirNomenclGroup", lNomGroup));
		//if(!lGndrs.isEmpty())
		//	criterDN.add( Restrictions.in("dirGender", lGndrs.toArray()));

		if(!lProvs.isEmpty())
			criterions.add( Restrictions.in("dirProvider", lProvs));

		if(!getNomenclatureList(criterDN).isEmpty())
			criterions.add( Restrictions.in("dirNomenclature", getNomenclatureList(criterDN)) );
		else
			return new LinkedList<Tail>();
		
		return criterions.size() >0 ? getTailsList(tail,criterions, p): new LinkedList<Tail>();
	}


	@Override
	public List<Tail> getTailsList(Tail tail_example, Collection<Criterion> criterions, int p) {
		// TODO Auto-generated method stub
		return dao.getTailsList(tail_example, criterions, p);
	}


	@Override
	public DirGender getGenderByName(String name) {
		// TODO Auto-generated method stub
		return dao.getGenderByName(name);
	}


	@Override
	public List<DirNomenclature> getNomenclatureList(Collection<Criterion> criterions) {
		// TODO Auto-generated method stub
		return dao.getNomenclatureList(criterions);
	}
	
	
	public Map<DirNomenclature,DirProvider> tailSetNomenclature(List<Long> providers, List<Long> genders, List<Long> categories , int p , int perPage) 
	{
		List<Tail> tails = getTailsList(providers, genders, categories, p);
		
		Map<DirNomenclature,DirProvider> hmDN = new LinkedHashMap<DirNomenclature,DirProvider>();
		
		//for(Tail t: tails)
		//	hmDN.put(t.getDirNomenclature(),t.getDirProvider());
		
		return hmDN;
	}


	public Set<DirNomenclature> tailNomenclatureSet(List<Long> providers, List<Long> genders, List<Long> categories , int p , int perPage) 
	{
		//Set<DirNomenclature> DNset = new LinkedHashSet<DirNomenclature>();
		
		List<DirProvider> lProvs = new LinkedList<DirProvider>();
		List<DirNomenclGroup> lNomGroup = new LinkedList<DirNomenclGroup>();
		//List<DirGender> lGens = new LinkedList<DirGender>();
		
		
		Collection<Criterion> criterions = new LinkedList<Criterion>();
		Collection<Criterion> criterDN = new LinkedList<Criterion>();
		
		for(Long idNomGroup: categories)
			lNomGroup.add((DirNomenclGroup)dao.getObject(DirNomenclGroup.class, idNomGroup));
					
					
		for(Long idProv: providers)
			lProvs.add((DirProvider) dao.getObject(DirProvider.class, idProv));
		
		for(Long idGndr: genders)
		{
			DirGender DG = (DirGender) dao.getObject(DirGender.class, idGndr);
			criterDN.add( Restrictions.eq("dirGender", DG));
		}
		
		
		if(!lNomGroup.isEmpty())
			criterDN.add(Restrictions.in("dirNomenclGroup", lNomGroup));
		//if(!lGndrs.isEmpty())
		//	criterDN.add( Restrictions.in("dirGender", lGndrs.toArray()));

		if(!lProvs.isEmpty())
			criterDN.add( Restrictions.in("dirProvider", lProvs));

		if(!getNomenclatureList(criterDN).isEmpty())
			criterions.add( Restrictions.in("dirNomenclature", getNomenclatureList(criterDN)) );
		else
			return new HashSet();
		
		return criterions.size() >0 ? getTailsNomenclature(new Tail(),criterions, p): new HashSet<DirNomenclature>();
	}
	
	
	
	//@Loggable
	@Override
	public List<Tail> getTailsList(long id_dirNomenclature) {
		// TODO Auto-generated method stub
		return dao.getTailsList(id_dirNomenclature);
	}

	//@Loggable
	public List<Tail> getTailsList(long id_dirNomenclature, String ip_address) {
		// TODO Auto-generated method stub
		//System.out.println("From - " +ip_address);
		return dao.getTailsList(id_dirNomenclature,ip_address);
	}


	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		dao.addOrder(order);
	}


	@Override
	public List<Order> getOrdersList() {
		// TODO Auto-generated method stub
		return dao.getOrdersList();
	}


	@Override
	public List<OrderItems> getOrderItems(Long orderId) {
		// TODO Auto-generated method stub
		return dao.getOrderItems(orderId);
	}


	@Override
	public Set<DirNomenclature> getTailsNomenclature(Tail tail_example, Collection<Criterion> criterions, int p) {
		// TODO Auto-generated method stub
		return dao.getTailsNomenclature(tail_example, criterions, p);
	}


	@Override
	public Order getOrder(Long orderId) {
		// TODO Auto-generated method stub
		return dao.getOrder(orderId);
	}


	@Override
	public void saveOrderItems(List<OrderItems> listOI) {
		// TODO Auto-generated method stub
		dao.saveOrderItems(listOI);
	}


	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addContactUsMessages(ContactUsMessages contactUsMessages) {
		// TODO Auto-generated method stub
		dao.addContactUsMessages(contactUsMessages);
	}


	@Override
	public List<ContactUsMessages> getContactUsMessagesList() {
		// TODO Auto-generated method stub
		return dao.getContactUsMessagesList();
	}


	@Override
	public void updateTails() {
		// TODO Auto-generated method stub
		dao.updateTails();
	}
	


	@Override
	public List<DirNomenclature> getPopularDirNomenclature() {
		// TODO Auto-generated method stub
		return dao.getPopularDirNomenclature();
	}
	
	
	public void test() {
		System.out.println("=====  test() =====");
	}


	@Override
	@Transactional(readOnly=true)
	public List<DirNomenclature> findByText(String text) {
		// TODO Auto-generated method stub
		LOG.info(this.getClass() +".findByText('"+text+"')");
		return dao.findByText(text);
	}


	@Override
	public BigInteger countGender(Long id_gender) {
		// TODO Auto-generated method stub
		return dao.countGender(id_gender);
	}


	@Override
	public BigInteger countCategory(Long id_dir_nomencl_group) {
		// TODO Auto-generated method stub
		return dao.countCategory(id_dir_nomencl_group);
	}


	@Override
	public BigInteger countProvider(Long id_dir_provider) {
		// TODO Auto-generated method stub
		return dao.countProvider(id_dir_provider);
	}


	@Override
	public BigInteger countType(Long id_dir_nomencl_group_root) {
		// TODO Auto-generated method stub
		return dao.countType(id_dir_nomencl_group_root);
	}


	@Override
	public Set<DirNomenclature> getNomenclInTails(List<Long> types, List<Long> providers, List<Long> genders, List<Long> categories , int p) {
		// TODO Auto-generated method stub
		return dao.getNomenclInTails(types, providers, genders, categories , p);
	}


}
