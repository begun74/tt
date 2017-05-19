package tt.config;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import tt.model.Tail;
import tt.util.CalculatePrice_Opt_Rozn_forTails;



@Configuration
@EnableAspectJAutoProxy
@Aspect
public class TT_AOP_PROCESS_Configuration {

	@Around("execution(* tt.service.*.*(..)) && @annotation(tt.annotation.ProcessTail) && args(tt.model.Tail)")
	public void processTail(ProceedingJoinPoint   pjp) throws Throwable {
		
		Object[] args = pjp.getArgs();
		
		Tail tail = CalculatePrice_Opt_Rozn_forTails.calculate((Tail)pjp.getArgs()[0]);

		pjp.getArgs()[0] = tail;
		pjp.proceed(args);
		//return result;
	}
}
