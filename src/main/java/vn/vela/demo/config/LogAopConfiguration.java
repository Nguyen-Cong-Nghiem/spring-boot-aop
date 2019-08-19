package vn.vela.demo.config;



import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAopConfiguration {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private String patternLog(JoinPoint joinPoint) {
    return String.format("%s%s%s%s%s%s%s",joinPoint.getSignature().getName(),
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
    logger.info(String.format("%s%s","Enter method: ",patternLog(joinPoint)));
  }

  @AfterReturning("applicationpackagePointcut()")
  public void logAfter(JoinPoint joinPoint) {
    logger.info(String.format("%s%s","Finish method : ",patternLog(joinPoint)));
  }

  @AfterThrowing(value = "applicationpackagePointcut()", throwing = "e")
  public void logAfterThrow(JoinPoint joinPoint, Throwable e) {
    logger.error(String.format("%s%s%s%s%s%s%s%s%s", "Exception in method : ",
        joinPoint.getSignature().getName(),
        " at class: ", joinPoint.getSignature().getDeclaringTypeName(),
        " with cause = ", e.getCause() != null ? e.getCause() : "NULL",
        " and exception = ", e.getMessage(), e.fillInStackTrace()));
  }
}
