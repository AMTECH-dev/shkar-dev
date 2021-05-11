package fox.spring.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class Aspects {

	@Before("execution(* *(..))")
	public void beforeLogAdvice() {
		System.out.println("Test aspect works");
	}
}