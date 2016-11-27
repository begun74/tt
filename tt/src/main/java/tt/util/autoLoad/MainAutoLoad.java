package tt.util.autoLoad;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

@Service
public class MainAutoLoad {

	private static ScheduledExecutorService service ;
	

	
	public static void startAutoLoad(List<Handler> pool)
	{
		service = Executors.newScheduledThreadPool(pool.size());
		for(Handler handler: pool)
			service.scheduleWithFixedDelay(handler, 1, 5, TimeUnit.SECONDS);
	}
	
	public static void stopAutoLoad()
	{
		service.shutdown();
	}
	
	
	@PostConstruct
	void init() {
		System.out.println("MainAutoLoad @PostConstruct - " +service);
	}
	
	@PreDestroy
	void destr() {
		System.out.println("MainAutoLoad @PreDestroy - "+service);
	} 	
}
