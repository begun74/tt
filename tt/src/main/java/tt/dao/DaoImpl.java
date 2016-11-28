package tt.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tt.model.DirGender;
import tt.model.DirNomenclGroup;
import tt.model.DirNomenclature;
import tt.model.DirProvider;
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
	public Object getObject(Class clazz, BigInteger id) {
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
		return getSession().createQuery(" from Tail").list();
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
		return getSession().createQuery("from DirGender order by gender").list();
	}


}
