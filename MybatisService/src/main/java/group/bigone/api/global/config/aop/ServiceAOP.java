package group.bigone.api.global.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Slf4j
@Component
public class ServiceAOP {
    @Pointcut("within(group.bigone.api.domain..controller.*)")
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object controllerBefore(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 메서드 실행 전
        log.debug("-------------------------------------------------- BEFORE POINT --------------------------------------------------");
        log.debug("INFO : {}/{}", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());

        StringBuilder stringBuilder = new StringBuilder();
        int intArgs = proceedingJoinPoint.getArgs().length;

        for(Object object : proceedingJoinPoint.getArgs()) {
            stringBuilder.append(object);
            stringBuilder.append(--intArgs != 0 ? ", " : "");
        }

        log.debug("ARGS : {}", stringBuilder.toString());

        // 메서드 실행 후
        Object objResult = proceedingJoinPoint.proceed();

        log.debug("AFTER POINT : {}", objResult);
        log.debug("-----------------------------------------------------------------------------------------------------------------\n\n");

        return objResult;
    }
}
