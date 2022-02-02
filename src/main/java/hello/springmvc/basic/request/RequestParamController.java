package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    /**
     * 서블릿 기능을 이용해 HTTP 요청 쿼리 파라미터를 받아서 출력하는 메서드
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    /**
     * RequestParam 애노테이션을 이용해 쿼리 파라미터를 받고 출력하는 버젼 V2
     * @param memberName
     * @param age
     * @return
     */
    @ResponseBody // 리턴할 때, "ok" 라는 문자열을 HTTP Response Body 에 넣어서 리턴
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
        @RequestParam("username") String memberName,
        @RequestParam("age") int age) {
        log.info("username={}, age={}", memberName, age);

        return "ok";
    }


    /**
     * RequestParam 애노테이션을 이용해 쿼리 파라미터를 받고 출력하는 버젼 V3
     * V2 와 다른 점은, request 의 쿼리 파라미터와 이름을 같게 하면 애노테이션 안에 있는 "username" 을 쓰지 않아도 됨
     * @param username
     * @param age
     * @return
     */
    @ResponseBody // 리턴할 때, "ok" 라는 문자열을 HTTP Response Body 에 넣어서 리턴
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    /**
     * 필수 파라미터를 지정하는 required 를 포함한 HTTP 요청 데이터 받기.
     * @param username
     * @param age
     * @return
     */
    @ResponseBody // 리턴할 때, "ok" 라는 문자열을 HTTP Response Body 에 넣어서 리턴
    @RequestMapping("/request-param-required")
    public String requestParamV4(
        @RequestParam(required = true) String username,
        @RequestParam(required = false) Integer age) {
            log.info("username={}, age={}", username, age);

            return "ok";
    }

    /**
     * 요청 데이터가 없는 경우 기본값을 지정하는 메서드
     * @param username
     * @param age
     * @return
     */
    @ResponseBody // 리턴할 때, "ok" 라는 문자열을 HTTP Response Body 에 넣어서 리턴
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true) String username,
            @RequestParam(required = false, defaultValue = "1000") Integer age) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    /**
     * 요청 파라미터를 Map 으로 받는 메서드
     * @param paramMap
     * @return
     */
    @ResponseBody // 리턴할 때, "ok" 라는 문자열을 HTTP Response Body 에 넣어서 리턴
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

}
