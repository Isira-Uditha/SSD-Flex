package com.example.Flexserver.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@Component
@Slf4j(topic = "c.o.b.i.AccessLogger")
public class LogsHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final Date date = new Date();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            String fullUrl = request.getRequestURI() + Optional.ofNullable(request.getQueryString())
                    .map(qs -> "?" + qs)
                    .orElse("");
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String controllerInfo = handlerMethod.getBeanType().getSimpleName() + "#" + handlerMethod.getMethod().getName();
            log.info("[{}] [{}] --- {} [{}]", request.getMethod(), response.getStatus(), fullUrl, controllerInfo);
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        this.removeMDC();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        this.removeMDC();
    }

    private void removeMDC() {
        MDC.remove("TRACE_ID");
        MDC.remove("USER");
    }

}