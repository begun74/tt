package tt.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tt.model.DirNomenclature;
import tt.model.DirProvider;
import tt.model.Store;
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
	public DirProvider getProvider(long id) {
		// TODO Auto-generated method stub
		return (DirProvider) getSession().get(DirProvider.class, id);
	}

	@Override
	public void delObject(Object obj) {
		// TODO Auto-generated method stub
		getSession().delete(obj);
	}

	@Override
	public Object getObject(Class clazz, long id) {
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
	public DirNomenclature getNomenclature(long id) {
		// TODO Auto-generated method stub
		return (DirNomenclature) getSession().get(DirNomenclature.class, id);
	}

	@Override
	public List<Store> getStoreList() {
		// TODO Auto-generated method stub
		return getSession().createSQLQuery("select * from store").addEntity(Store.class).list();
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
	public Store getStoreBySerVerUID(long serialVersionUID) {
		// TODO Auto-generated method stub
		return (Store) getSession().createQuery("from store s where s.serialVersionUID = :serialVersionUID").setParameter("serialVersionUID", serialVersionUID).uniqueResult();
	}


}
