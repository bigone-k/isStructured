package group.bigone.api;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ServiceAOP {
    private static final Logger logger = LoggerFactory.getLogger("AOP");

    @Pointcut("within(group.bigone.api.controller.v1.*)")
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object controllerBefore(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 메서드 실행 전
        if (logger.isDebugEnabled()) {
            logger.info("-------------------------------------------------- BEFORE POINT --------------------------------------------------");
            logger.info("INFO : {}/{}", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());

            StringBuilder stringBuilder = new StringBuilder();
            int intArgs = proceedingJoinPoint.getArgs().length;

            for(Object object : proceedingJoinPoint.getArgs()) {
                stringBuilder.append(object);
                stringBuilder.append(--intArgs != 0 ? ", " : "");
            }

            logger.info("ARGS : {}", stringBuilder.toString());
        }

        // 메서드 실행 후
        Object objResult = proceedingJoinPoint.proceed();

        if (logger.isDebugEnabled()) {
            logger.info("AFTER POINT : {}", objResult);
            logger.info("-----------------------------------------------------------------------------------------------------------------\n\n");
        }

        return objResult;
    }
}
