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



@Configuration
@EnableAspectJAutoProxy
@Aspect
public class TT_AOP_PROCESS_Configuration {

	@Around("execution(* tt.service.*.*(..)) && @annotation(tt.annotation.ProcessTail) && args(tt.model.Tail)")
	public void processTail(ProceedingJoinPoint   pjp) throws Throwable {
		
		Object[] args = pjp.getArgs();
		System.out.println("processTail - "+pjp.getArgs()[0]);
		Tail tail = (Tail)pjp.getArgs()[0];

		double fpice = tail.getFirstPrice();

		double nadb_opt = (tail.getNadb_opt()+100.0) / 100.0;
		double nadb_rozn = (tail.getNadb_rozn()+100.0) / 100.0;
		double nds = (tail.getNds()+100.0) / 100.0;
		
		double res = fpice * nadb_opt * nds;
		System.out.println(res +"");
		
		res = new BigDecimal(res).setScale(2, RoundingMode.HALF_UP).doubleValue();

		System.out.println(res +"");

		tail.setRozn_price(res);
		//tail.setRozn_price(new Double(formatter.format(fpice * nadb_opt * nds)));
		pjp.getArgs()[0] = tail;
		pjp.proceed(args);
		System.out.println("processTail - "+pjp.getArgs()[0]);
		//return result;
	}
}
