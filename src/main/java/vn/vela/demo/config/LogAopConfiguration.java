package vn.vela.demo.config;


import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LogAopConfiguration {

  private final Environment environment;

  public LogAopConfiguration(Environment environment) {
    this.environment = environment;
  }

  private String patternLog(JoinPoint joinPoint) {
    return String.format("%s%s%s%s%s%s%s", joinPoint.getSignature().getName(),
        "() with params: ",
        Arrays.toString(joinPoint.getArgs()),
        " in class: ",
        joinPoint.getTarget().getClass(),
        " base class: ",
        joinPoint.getSignature().getDeclaringType());
  }

  @Pointcut(value = "within(*vn.vela.*.controller..*) || within(*vn.vela.*.service..*) "
      + "|| within(*vn.vela.*.entity..*) || within(*vn.vela.*.repository..*)")
  public void applicationpackagePointcut() {
  }

  @Before(value = "applicationpackagePointcut()")
  public void logBefore(JoinPoint joinPoint) {
    log.info(String.format("%s%s","Enter method: ",patternLog(joinPoint)));
  }

  @AfterReturning("applicationpackagePointcut()")
  public void logAfter(JoinPoint joinPoint) {
    log.info(String.format("%s%s","Finish method : ",patternLog(joinPoint)));
  }

  @AfterThrowing(value = "applicationpackagePointcut()", throwing = "e")
  public void logAfterThrow(JoinPoint joinPoint, Throwable e) {
    log.error(String.format("%s%s%s%s", "Exception in method : ",
        patternLog(joinPoint),
        " and exception = ", Arrays.toString(e.getStackTrace())));
  }

  @Around(value = "applicationpackagePointcut()")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info(String.format("%s%s", "Enter method: ", patternLog(joinPoint)));
    try {
      Object result = joinPoint.proceed();
      log.info(String.format("%s%s", "Finish method : ", patternLog(joinPoint)));
      return result;
    } catch (Exception e) {
      log.error(String.format("%s%s%s%s","Exception in : ", patternLog(joinPoint), "exception = ",
          Arrays.toString(e.getStackTrace())));
      throw e;
    }
  }
}
