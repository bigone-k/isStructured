package group.bigone.api;


import group.bigone.api.common.annotation.AnnotationProcessStep;
import group.bigone.api.common.constants.StateCode;
import group.bigone.api.common.constants.Step;
import group.bigone.api.config.security.JwtTokenProvider;
import group.bigone.api.entity.ProcessStep;
import group.bigone.api.service.ProcessStepService;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ProcessStepIntercepter extends HandlerInterceptorAdapter {

    private final ProcessStepService processStepService;
    private final JwtTokenProvider jwtTokenProvider;


    public ProcessStepIntercepter(ProcessStepService processStepService, JwtTokenProvider jwtTokenProvider)
    {
        this.processStepService =  processStepService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AnnotationProcessStep annotationProcessStep = handlerMethod.getMethodAnnotation(AnnotationProcessStep.class);

        if (annotationProcessStep == null){
            return true;
        }

        // 프로세스 시작 시점
        if (annotationProcessStep.step().equals(Step.START))
        {
            processStepService.InsertProcessStep(annotationProcessStep.processStepCode().getCode());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AnnotationProcessStep annotationProcessStep = handlerMethod.getMethodAnnotation(AnnotationProcessStep.class);

        // 실패 프로세스 경우
        // TODO : 실패에 대해서 찾을 수 없음. Exception ex > DispatcherServlet이나 그외 부분에서 발생하는 예외, ExceptionResolver 처리 예외가 아님..
        if (ex != null)
            processStepService.UpdateProcessStep(StateCode.FAILURE.getCode());

        // 정상적인 프로세스 종료 인경우
        if (annotationProcessStep != null)
            if (annotationProcessStep.step().equals(Step.END))
                processStepService.UpdateProcessStep(StateCode.SUCCESS.getCode());
    }
}
