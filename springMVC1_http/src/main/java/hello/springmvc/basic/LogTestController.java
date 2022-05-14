package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//기본적으로 @Controller면 View가 반환이 됨
//RestController는 문자열이 body로 String이 반영됨 , RestAPI 만들 때 핵심적인 컨트롤러
@Slf4j //Lombok이 Logger를 자동으로 주입해줌.
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @GetMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        //trace와 degug는 application.properties에서 로그 레벨을 설정해줘야 보이기 시작
        log.trace("trace log={}", name);
        log.debug("debug log={}", name); //개발서버
        log.info("info log={}", name); //운영
        log.warn("warn log={}", name); //경고
        log.error("error log={}", name); //에러-바로 확인

        return "ok";
    }
}
