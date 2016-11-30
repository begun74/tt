package tt.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tt.dao.Dao;
import tt.dao.DaoImpl;
import tt.model.DirGender;
import tt.model.DirNomenclGroup;
import tt.model.DirNomenclGroupRoot;
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
	public Object getObject(Class clazz, Long id) {
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
	public void addNomenclGroupRoot(DirNomenclGroupRoot dirNomenclGroupRoot) {
		// TODO Auto-generated method stub
		dao.addNomenclGroupRoot(dirNomenclGroupRoot);
	}

	
	public List<Tail> getTailsList(List<Long> providers, List<Long> genders) {
		// TODO Auto-generated method stub
		List<DirProvider> lProvs = new ArrayList<DirProvider>();
		List<DirGender> lGndrs = new ArrayList<DirGender>();
		
		Tail tail = new Tail();
		Collection<Criterion> criterions = new LinkedList<Criterion>();
		Collection<Criterion> criterDN = new LinkedList<Criterion>();
		
		for(Long idProv: providers)
			lProvs.add((DirProvider) dao.getObject(DirProvider.class, idProv));
		
		for(Long idGndr: genders)
			lGndrs.add((DirGender) dao.getObject(DirGender.class, idGndr));
		
		if(lGndrs.size() > 0)
			criterDN.add( Restrictions.in("dirGender", lGndrs.toArray()));

		if(lProvs.size() > 0)
			criterions.add( Restrictions.in("dirProvider", lProvs));

		if(getNomenclatureList(criterDN).size() > 0)
			criterions.add( Restrictions.in("dirNomenclature", getNomenclatureList(criterDN)) );

		return criterions.size() >0 ? getTailsList(tail,criterions): getTailsList();
	}


	@Override
	public List<Tail> getTailsList(Tail tail_example, Collection<Criterion> criterions) {
		// TODO Auto-generated method stub
		return dao.getTailsList(tail_example, criterions);
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
	
	
	public Map<DirNomenclature,DirProvider> tailSetNomenclature(List<Long> providers, List<Long> genders) 
	{
		List<Tail> tails = getTailsList(providers, genders);
		Map<DirNomenclature,DirProvider> hmDN = new HashMap<DirNomenclature,DirProvider>();
		
		for(Tail t: tails)
			hmDN.put(t.getDirNomenclature(),t.getDirProvider());
		
		return hmDN;
	}

}
