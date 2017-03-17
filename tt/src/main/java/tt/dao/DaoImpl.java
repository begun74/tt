package tt.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tt.model.ContactUsMessages;
import tt.model.DirGender;
import tt.model.DirNomenclGroup;
import tt.model.DirNomenclGroupRoot;
import tt.model.DirNomenclature;
import tt.model.DirProvider;
import tt.model.OrderItems;
import tt.model.Store;
import tt.model.Tail;
import tt.model.User;




@Repository("dao")
public class DaoImpl implements Dao {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	
	protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

	@SuppressWarnings("unchecked")
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return getSession().createSQLQuery("select * from tt_user order by name").addEntity(User.class).list();
	}

	public void addUser(User user) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(user);
	}

	@Override
	public List<DirProvider> getProviderList() {
		// TODO Auto-generated method stub
		return getSession().createSQLQuery("select * from dir_provider order by name").addEntity(DirProvider.class).list();
	}

	@Override
	public void addProvider(DirProvider dirProvider) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(dirProvider);
	}


	@Override
	public void delObject(Object obj) {
		// TODO Auto-generated method stub
		getSession().delete(obj);
	}

	@Override
	public Object getObject(Class clazz, Long id) {
		// TODO Auto-generated method stub
		return getSession().get(clazz, id);
	}

	@Override
	public List<DirNomenclature> getNomenclatureList() {
		// TODO Auto-generated method stub
		return getSession().createSQLQuery("select * from dir_nomenclature order by name").addEntity(DirNomenclature.class).list();
	}

	@Override
	public void addNomenclature(DirNomenclature dirNomenclature) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(dirNomenclature);
	}


	@Override
	public List<Store> getStoreList() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Store").list();
	}

	@Override
	public void addStore(Store store) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(store);
	}

	/* @Override
	public Store getStore(long id) {
		// TODO Auto-generated method stub
		return (Store) getSession().get(Store.class, id);
	}
	*/

	@Override
	public Store getStoreBySerVerUID(Long serialVersionUID) {
		// TODO Auto-generated method stub
		return (Store) getSession().createQuery("from Store s where s.serialVersionUID = :serialVersionUID").setParameter("serialVersionUID", serialVersionUID).uniqueResult();
	}

	@Override
	public List<Tail> getTailsList() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Tail.class).add(Restrictions.isNull("destruction_date")).addOrder(Order.asc("id")).list();
	}

	@Override
	public void addTail(Tail tail) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(tail);
	}

	@Override
	public DirProvider getProviderByCode(Integer code) {
		// TODO Auto-generated method stub
		return (DirProvider) getSession().createQuery("from dir_provider dp where dp.code = :code").setParameter("code", code).uniqueResult();
	}

	@Override
	public List<DirNomenclGroup> getNomenclGroupList() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from DirNomenclGroup order by name").list();
	}

	@Override
	public void addNomenclGroup(DirNomenclGroup dirNomenclGroup) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(dirNomenclGroup);
	}

	@Override
	public List<DirGender> getGenderList() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from DirGender order by name").list();
	}

	@Override
	public List<DirNomenclGroupRoot> getNomenclGroupRootList() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from DirNomenclGroupRoot order by name").list();
	}

	@Override
	public void addNomenclGroupRoot(DirNomenclGroupRoot dirNomenclGroupRoot) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(dirNomenclGroupRoot);
	}

	@Override
	public List<Tail> getTailsList(Tail tail_example, Collection<Criterion> criterions, int p) {
		// TODO Auto-generated method stub
		
		List<Tail> lTail = null;
		
		
		Criteria criteria = getSession().createCriteria(Tail.class).add(Restrictions.isNull("destruction_date"));
		
		
		if(criterions != null) {
			criterions.forEach(c -> criteria.add(c));
		}

		
		lTail = criteria.createCriteria("dirNomenclature").addOrder(Order.asc("name")).list();
		//criteria.setFirstResult(p-1);
		//criteria.setMaxResults(p*9);
		

			
		return lTail;
			
	}

	@Override
	public Set<DirNomenclature> getTailsNomenclature(Tail tail_example, Collection<Criterion> criterions, int p) {
		
		LinkedHashSet<DirNomenclature> dirNomSet = new LinkedHashSet<DirNomenclature>();
		
		Criteria criteria = getSession().createCriteria(Tail.class).add(Restrictions.isNull("destruction_date"));
		
		
		if(criterions != null) {
			criterions.forEach(c -> criteria.add(c));
		}
		
		Set<Long> lDirNomenctls = new HashSet<Long>();
		Map<Long,Double> fkTailsMap = new HashMap<Long,Double>();
		
		List<Tail> tails = criteria.list();
				
		for(Tail tail: tails)
		{
			lDirNomenctls.add(tail.getDirNomenclature().getId());
			fkTailsMap.put(tail.getDirNomenclature().getId(), tail.getFirstPrice());
		}
		
		Criteria criteriaDN = getSession().createCriteria(DirNomenclature.class).addOrder(Order.asc("name"));
		
		if(lDirNomenctls.size() >0) 
		{
			criteriaDN.add(Restrictions.in("id", lDirNomenctls));
			dirNomSet.addAll(criteriaDN.list());
			
			for(DirNomenclature dn: dirNomSet)
				dn.setTempPrice(fkTailsMap.get(dn.getId()));
				
		}

		return dirNomSet;
	}


	
	@Override
	public DirGender getGenderByName(String name) {
		// TODO Auto-generated method stub
		return (DirGender) getSession().createQuery("from DirGender where name = :name").setParameter("name", name.toLowerCase()).uniqueResult();
	}

	@Override
	public List<DirNomenclature> getNomenclatureList(Collection<Criterion> criterions) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(DirNomenclature.class).addOrder(Order.asc("name"));
		
		if(criterions != null)
			criterions.forEach(c -> criteria.add(c));
		
		return new LinkedList<DirNomenclature>(criteria.list());
	}

	@Override
	public List<Tail> getTailsList(long id_dirNomenclature) {
		// TODO Auto-generated method stub
		//!========= Statistic per nomenclature ==========
		getSession().createSQLQuery("insert into statistic_nomencl values (nextval('seq_statistic'),"+id_dirNomenclature+")").executeUpdate();
		//!========= Statistic per nomenclature ==========
		
		return getSession().createQuery("from Tail where fk_id_nomenclature = :id_dirNomenclature and destruction_date is null order by size, amountTail").setParameter("id_dirNomenclature", id_dirNomenclature).list();
	}


	@Override
	public void addOrder(tt.model.Order order) {
		// TODO Auto-generated method stub
		getSession().persist(order);
	}

	@Override
	public List<tt.model.Order> getOrdersList() {

		return getSession().createSQLQuery("select distinct o.* from orders o inner join order_items oi on o.id_orders=oi.fk_id_orders where oi.destruction_date is null order by o.creation_date").addEntity(tt.model.Order.class).list();
		
		//return getSession().createQuery("from Order order by creation_date").list();
	}

	@Override
	public List<OrderItems> getOrderItems(Long orderId) {
		// TODO Auto-generated method stub
		Session sess =  getSession();
		
		tt.model.Order order = getOrder(orderId);
		
		List<OrderItems> orderItems = (List<OrderItems>)sess.createQuery("from OrderItems where fk_id_orders = :orderId").setParameter("orderId", orderId).list(); 

		for(OrderItems oi: orderItems)
			oi.setOrder(order);
		
		return orderItems;
	}

	@Override
	public tt.model.Order getOrder(Long orderId) {
		// TODO Auto-generated method stub
		tt.model.Order order = (tt.model.Order) getSession().createQuery("from Order where id = :orderId").setParameter("orderId", orderId).uniqueResult();
		order.getOrderItems();
		return  order;
	}

	@Override
	public void saveOrderItems(List<OrderItems> listOI) {
		// TODO Auto-generated method stub
		Session sess = getSession();
				
		for(OrderItems oi: listOI)
			sess.saveOrUpdate(oi);
		
		
			
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		
		users = getSession()
				.createQuery("from User where name = :name")
				.setParameter("name", username)
				.list();
		
		if(users.size() > 0)
			return users.get(0);
		else
			return null;
	}

	@Override
	public void addContactUsMessages(ContactUsMessages contactUsMessages) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(contactUsMessages);
	}

	@Override
	public List<ContactUsMessages> getContactUsMessagesList() {
		// TODO Auto-generated method stub
		return  getSession().createQuery("from ContactUsMessages").list();
	}

	@Override
	public void updateTails() {
		// TODO Auto-generated method stub
		getSession().createSQLQuery("update tails set destruction_date = now() where destruction_date is null").executeUpdate();
	}


	

}
