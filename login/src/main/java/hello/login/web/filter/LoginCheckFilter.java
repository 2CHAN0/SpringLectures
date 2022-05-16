package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {
    private static final String[] whitelist = {"/", "members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작 {}", requestURI);

            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    //로그인 페이지로 리다이렉트
                    //로그인으로 리다이렉트하는데, 만약 로그인 성공하면 다시 돌아올 수 있도록 도와줌. login 컨트롤러에서 requestParam에서 꺼내다가 Redirect 처리해줘얗.ㅁ
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return; //다음 서블릿이나 컨트롤러 호출 안하겠다는 의미임. sendRedirect만 하게됨.
                }
            }
            //로그인 된 상태면 뒤에 이어서 진행
            chain.doFilter(request, response);

        } catch(Exception e ){
            throw e; //예외로깅 가능하지만, 톰캣(서블릿 컨테이너, WAS) 까지 예외를 보내주어야함. 여기서 예외를 먹어버리면 정상처럼 동작함
        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }

    }

    /**
     * 화이트 리스트의 경우 인증체크 x
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}

