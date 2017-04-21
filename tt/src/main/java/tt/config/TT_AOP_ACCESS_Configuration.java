package tt.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import tt.access.Access;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class TT_AOP_ACCESS_Configuration {

	@Around("execution(* tt.service.*.*(..)) && @annotation(tt.annotation.CheckAccess)")
	public Object checkAccessAround(ProceedingJoinPoint   joinPoint) throws Throwable {
		Object result = joinPoint.proceed();
		System.out.println("checkAccess() is running!");
		System.out.println("method : " + joinPoint.getSignature().getName());
		Access.createAccess(result.getClass());
		System.out.println("result : " + result.getClass());
		System.out.println("******");
		
		return result;
	}

}
