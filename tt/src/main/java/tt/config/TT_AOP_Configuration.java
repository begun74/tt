package tt.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
@Aspect
public class TT_AOP_Configuration {
	
	@Around("execution(* tt.controller.*.*(..)) && @annotation(tt.annotation.Loggable)")
	public Object logBefore(ProceedingJoinPoint   joinPoint) throws Throwable {
		Object result = joinPoint.proceed();
		System.out.println("logBefore() is running!");
		System.out.println("method : " + joinPoint.getSignature().getName());
		System.out.println("result : " + result);
		System.out.println("******");
		
		return result;
	}


	@Before("execution(* tt.controller.AdminCtrl.doAdmin())")
	public void logBefore2(JoinPoint joinPoint) {

		System.out.println("logBefore2() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}

}
