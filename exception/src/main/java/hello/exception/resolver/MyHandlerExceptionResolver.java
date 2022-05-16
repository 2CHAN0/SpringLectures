package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        if (ex instanceof IllegalArgumentException) {
            log.info("IllegalArgumentException resolves to 400");
            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage()); // send Error는 IOException이 checked로 되어있어서 잡아줘야함
                return new ModelAndView(); // ModelAndView를 빈값으로 넘기면 정상적인 return한 것처럼 에러를 먹어버림.
            } catch (IOException e) {
                log.error("resolver ex", e);
            }

        }
        return null; // null로 리턴을 하면 에러를 그대로 터진채로 넘겨버림
    }
}
