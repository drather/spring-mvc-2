package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username": "hello", "age": 20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * V1
     * 서블릿 기능을 활용한 HTTP Body 읽기
     * 문자열을 읽어서 파싱한 뒤, HelloData 클래스로 변환
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBod={}", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age = {}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }

    /**
     * V2
     * ResponseBody 애노테이션을 이용해서, HTTP Request Body 에 접근
     * 서블릿 기능을 직접 쓰지 않는다는 점에서, 편리
     * 그러나, 객체로 만들어주는 부분은 개발자가 직접 함
     * 이것 또한 스프링이 해줄 수 있을까?
     * @param messageBody
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBod={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    /**
     * V3
     * 있다.
     * HttpEntity, @RequestBody 등을 사용하면, HttpMessageConverter 가 Body 를 객체로 바꿔줌.
     * RequestBody 애노테이션은 생략 불가!
     * 생략하면 ModelAttribute 가 되어버림.
     * @param helloData
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("username={}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }


    /**
     * HttpEntitiy 를 이용한 Body 접근 및 JSON 데이터 객체화
     * @param httpEntity
     * @return
     */
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
        HelloData helloData = httpEntity.getBody();
        log.info("username={}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    /**
     * HttpMessageConverter 가, 입력을 받을 때도 객체로 만들어 주고 ,나갈 때도 객체를 문자열로 바꿔줌
     * @param helloData
     * @return
     */
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        log.info("username={}, age = {}", helloData.getUsername(), helloData.getAge());

        return helloData;
    }
}
