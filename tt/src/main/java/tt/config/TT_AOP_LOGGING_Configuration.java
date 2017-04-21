package tt.config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import tt.access.Access;
import tt.dao.DaoImpl;
import tt.service.TTServiceImpl;


@Configuration
@EnableAspectJAutoProxy
@Aspect
public class TT_AOP_LOGGING_Configuration {
	

	
	@Around("execution(* tt.controller.*.*(..)) && @annotation(tt.annotation.Loggable)")
	public Object logAroundController(ProceedingJoinPoint   joinPoint) throws Throwable {
		Object result = joinPoint.proceed();
		System.out.println("logBefore() is running!");
		System.out.println("method : " + joinPoint.getSignature().getName());
		System.out.println("result : " + result);
		System.out.println("******");
		
		return result;
	}

	@Around("execution(* tt.service.*.*(..)) && @annotation(tt.annotation.Loggable)")
	public Object logAroundService(ProceedingJoinPoint   joinPoint) throws Throwable {
		Object result = joinPoint.proceed();
		joinPoint.getKind();
		System.out.println("logAroundService()");
		System.out.println("method : " + joinPoint.getSignature().getName());
		System.out.println("Args : " +Arrays.toString(joinPoint.getArgs()));
		System.out.println("result : " + result +"  getKind - "+joinPoint.getKind());

		TTServiceImpl ttService = (TTServiceImpl)joinPoint.getTarget();
		
		//ttService.test();
		System.out.println("******");
		
		return result;
	}

	/*
	@Before("execution(* tt.service.*.*(..)) && @annotation(tt.annotation.Loggable)")
	public Object logBeforeService(JoinPoint   joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		System.out.println("logBeforeService() ");
		System.out.println("method : " + joinPoint.getSignature().getName());
		System.out.println("Args : " +Arrays.toString(joinPoint.getArgs()));
		System.out.println("gatTarget() : " + joinPoint.getTarget());
		System.out.println("******");
		
		TTServiceImpl ttService = (TTServiceImpl)joinPoint.getTarget();
		
		Class<TTServiceImpl> clazz_ttService = (Class<TTServiceImpl>) ttService.getClass();
		Field fields[] = clazz_ttService.getDeclaredFields();
		
		for (Field field : fields) {
			  //if ( !Modifier.isPublic(field.getModifiers())) {
			    //field.setAccessible(true);
			  //}
			  System.out.print("Field: " + field.getName());
			  //System.out.println(", value: " + field.get(someObject));
			}
		clazz_ttService.getAnnotations();
		Method method = clazz_ttService.getMethod("test",null);
		method.setAccessible(false);
		method.invoke(ttService, null);
		
		//ttService.test();
		
		return args;
	}
	*/
	
	@Before("execution(* tt.controller.AdminCtrl.doAdmin())")
	public void logBefore2(JoinPoint joinPoint) {

		System.out.println("logBefore2() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}

}
