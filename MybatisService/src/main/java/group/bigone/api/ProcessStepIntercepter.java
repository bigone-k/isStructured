package group.bigone.api;


import group.bigone.api.common.annotation.AnnotationProcessStep;
import group.bigone.api.common.constants.StateCode;
import group.bigone.api.common.constants.Step;
import group.bigone.api.config.security.JwtTokenProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ProcessStepIntercepter extends HandlerInterceptorAdapter {

    private final JwtTokenProvider jwtTokenProvider;


    public ProcessStepIntercepter(JwtTokenProvider jwtTokenProvider)
    {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AnnotationProcessStep annotationProcessStep = handlerMethod.getMethodAnnotation(AnnotationProcessStep.class);

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
    }
}
