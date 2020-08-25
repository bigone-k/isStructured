package group.bigone.api.global.advice.intercepter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ProcessStepIntercepter extends HandlerInterceptorAdapter {

    public ProcessStepIntercepter()
    {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        AnnotationProcessStep annotationProcessStep = handlerMethod.getMethodAnnotation(AnnotationProcessStep.class);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        AnnotationProcessStep annotationProcessStep = handlerMethod.getMethodAnnotation(AnnotationProcessStep.class);

        // 실패 프로세스 경우
        // TODO : 실패에 대해서 찾을 수 없음. Exception ex > DispatcherServlet이나 그외 부분에서 발생하는 예외, ExceptionResolver 처리 예외가 아님..
    }
}
