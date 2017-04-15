package tt.config;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import tt.service.TTServiceImpl;


@Configuration
@EnableAspectJAutoProxy
@Aspect
public class TT_AOP_Configuration {
	
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
		System.out.println("******");
		
		return result;
	}

	@Before("execution(* tt.service.*.*(..)) && @annotation(tt.annotation.Loggable)")
	public Object logBeforeService(JoinPoint   joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		System.out.println("logBeforeService() ");
		System.out.println("method : " + joinPoint.getSignature().getName());
		System.out.println("Args : " +Arrays.toString(joinPoint.getArgs()));
		System.out.println("gatTarget() : " + joinPoint.getTarget());
		System.out.println("******");
		
		TTServiceImpl ttService = (TTServiceImpl)joinPoint.getTarget();
		ttService.test();
		
		return args;
	}
	
	@Before("execution(* tt.controller.AdminCtrl.doAdmin())")
	public void logBefore2(JoinPoint joinPoint) {

		System.out.println("logBefore2() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}

}
