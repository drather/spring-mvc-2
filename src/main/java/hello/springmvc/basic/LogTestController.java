package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller 가 View 의 이름을 찾아서 리턴하는 것과는 다르게,
// @RestController 는 문자열을 그대로 HTTP Body 에 넣어서 리턴
@RestController
@Slf4j
public class LogTestController {
//    아래 코드를 대체하는 것이 Slf4j 애노테이션
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        // System.out.println("name = " + name);

        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info("info log = {}", name);
        log.warn("info log = {}", name);
        log.error("error log = {}", name);

        log.info("info log = {}", name);

        return "ok";
    }
}
