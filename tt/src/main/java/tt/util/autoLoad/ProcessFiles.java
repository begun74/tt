package tt.util.autoLoad;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

@Service
public class ProcessFiles implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3365768209076569422L;


	private static ScheduledExecutorService service ;
	private static ExecutorService photoFileService ;

	private static int pool_size = 1;


	@PostConstruct
	void init() {
		
		photoFileService = Executors.newFixedThreadPool(pool_size);
		
		System.out.println("ProcessFiles @PostConstruct ");
	}

	
	public static void startAutoLoad(List<Handler> pool)
	{
		pool_size = pool.size() == 0?pool_size:pool.size();
		
		service = Executors.newScheduledThreadPool(pool_size);
		for(Handler handler: pool)
			service.scheduleWithFixedDelay(handler, 5, 5, TimeUnit.SECONDS);
		
		
	}
	
	public static void stopAutoLoad()
	{
		service.shutdown();
	}
	
	
	public static void startPhotoFileService(HashMap<Long,String> hmPhotoFile) 
	{
		//photoFileService = Executors.newSingleThreadExecutor();
		
		for(Long code : hmPhotoFile.keySet())
			photoFileService.submit(new FileHandler(code, hmPhotoFile.get(code)));
	}

	public static void startPhotoFileService2(HashMap<Long,List<String>> hmPhotoFile) 
	{	
		//Загрузка фото
		//photoFileService = Executors.newFixedThreadPool(3);
		Future<Long> future = null;
		
		for(Long code : hmPhotoFile.keySet())
		{
			future = photoFileService.submit(new FileHandler(code, hmPhotoFile.get(code)));
			try {
				future.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(future.isDone())
		{
			stopPhotoFileService();
			//System.out.println("startPhotoFileService2.isDown() - " + future.isDone());
		}
	}

	
	public static void stopPhotoFileService() 
	{
		try {
			//System.out.println("=========== stopPhotoFileService =========");
			photoFileService.awaitTermination(3, TimeUnit.SECONDS);
			System.out.println("=========== photoFileService.awaitTermination ! =========");
		} catch (InterruptedException e) {
			photoFileService.shutdownNow();
			System.out.println("===========photoFileService.shutdownNow ! =========");
		}
	}
	
	
	
	
	
	@PreDestroy
	void destr() {
		
		stopPhotoFileService();
		
		System.out.println("ProcessFiles @PreDestroy ");
	}

}
