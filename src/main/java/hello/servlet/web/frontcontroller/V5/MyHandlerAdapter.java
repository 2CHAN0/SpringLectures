package hello.servlet.web.frontcontroller.V5;

import hello.servlet.web.frontcontroller.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    // 핸들러 지원하는 어댑터인지?
    boolean supports(Object handler);

    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
