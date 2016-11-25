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

@Component(value = "appBean")
public class AppBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7749935089438907692L;
	
	
	@Autowired
	private TTServiceImpl ttService;  //Service which will do all data retrieval/manipulation work
	
	private HashMap<Long,Object> mapStore = new HashMap<Long,Object>();
	
	private double version = 1.0;
	
	
	
	
	public double getVersion() {
		return version;
	}


	public void setVersion(double version) {
		this.version = version;
	}


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
	
	
	public void addToMapStore(IMAmodel IMAmodel) throws Exception {
		
			 
			Store s = ttService.getStoreBySerVerUID(IMAmodel.getSerialversionuid()) == null? new Store() : ttService.getStoreBySerVerUID(IMAmodel.getSerialversionuid());
			s.setSerialVersionUID(IMAmodel.getSerialversionuid());
			s.setBytearray(objectToBytes(IMAmodel));
			
			ttService.addStore(s);
			
			mapStore.put(s.getSerialVersionUID(), bytesToObject(s.getBytearray()));
			
		
	}

	public Object findBySerialVerUID(Long serialVersionUID)
	{
		try {
			return mapStore.get(serialVersionUID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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

	private byte[] objectToBytes(IMAmodel IMAmodel) throws IOException{
		// TODO Auto-generated method stub

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
		objOut.writeObject(IMAmodel);
		objOut.close();
		byteOut.close();
		byte[] bytes = byteOut.toByteArray();
		

		return bytes;
	}



	@PreDestroy
	void destr() {
		System.out.println("AppBean @PreDestroy ");
	}



}
