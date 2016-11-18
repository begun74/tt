package tt.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tt.model.*;
import tt.modelattribute.IMAmodel;
import tt.service.TTServiceImpl;

@Component
public class AppBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7749935089438907695L;
	
	private long t = System.currentTimeMillis();

	
	@Autowired
	private TTServiceImpl ttService;  //Service which will do all data retrieval/manipulation work
	
	private HashMap<Long,Object> mapStore = new HashMap<Long,Object>();
	
	@PostConstruct
	void init() {
		
		Iterator<Store> iterStore = ttService.getStoreList().iterator();
		
		while(iterStore.hasNext())
		{
			Store s = (Store)iterStore.next();
			try {
				mapStore.put(s.getSerialVersionUID(), bytesToObject(s.getBytearray()));
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("mapStore - "+mapStore);
		System.out.println("AppBean @PostConstruct ");
	}
	
	
	public void addToMapStore(IMAmodel IMAmodel) throws IOException {
		Store s = objectToBytes(IMAmodel);
		ttService.addStore(s);
		
		mapStore.put(s.getSerialVersionUID(), s);
		
	}

	public HashMap<Long, Object> getMapStore() {
		return this.mapStore;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	private Object bytesToObject(byte[] binary) throws IOException, ClassNotFoundException{
		// TODO Auto-generated method stub
		Object obj = new Object();

		ByteArrayInputStream byteIn = new ByteArrayInputStream(binary);
		ObjectInputStream objIn = new ObjectInputStream(byteIn);
		obj = objIn.readObject();
		objIn.close();
		byteIn.close();

		return obj;
	}

	private Store objectToBytes(IMAmodel IMAmodel) throws IOException{
		// TODO Auto-generated method stub
		Store store = new Store();

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
		objOut.writeObject(IMAmodel);
		objOut.close();
		byteOut.close();
		byte[] bytes = byteOut.toByteArray();
		
		store.setSerialVersionUID(IMAmodel.getSerialversionuid());
		store.setBytearray(bytes);

		return store;
	}



	@PreDestroy
	void destr() {
		System.out.println("AppBean @PreDestroy ");
	}




	@Override
	public String toString() {
		return "AppBean [t=" + t + "]";
	}

	
	
}
