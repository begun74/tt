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
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
				//System.out.println(s.getSerialVersionUID()+"  "+bytesToObject(s.getBytearray()));
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("mapStore - "+mapStore);
		System.out.println("AppBean @PostConstruct ");
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



	@PreDestroy
	void destr() {
		System.out.println("AppBean @PreDestroy ");
	}




	@Override
	public String toString() {
		return "AppBean [t=" + t + "]";
	}

	
	
}
