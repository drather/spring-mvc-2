package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;


@Slf4j
@Controller
public class RequestBodyStringController {

    /**
     * 서블릿 기술을 이용한 데이터 읽어오기 V1
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);
        response.getWriter().write("ok");
    }

    /**
     * Controller 가 파라미터로 inputStream 을 받을 수 있음을 이용해ㅐ서, 서블릿 기술 활용하지 않는 메서드 V2
     * @param inputStream
     * @param writer
     * @throws IOException
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer writer) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);
        writer.write("ok");
    }

    /**
     * HttpEntity 를 이용해서, HTTP header, body 를 편하게 조회할 수 있게 해주는 V3
     * 요청 파라미터를 조회하는 기능과 관계 없음(GET 메서드의 쿼리 파라미터, POST 방식의 HTML Form 데이터 과는 관계 없음)
     * @param httpEntity
     * @return
     * @throws IOException
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}", messageBody);

        return new HttpEntity<>("ok");
    }

    /**
     * HttpEntity 를 대신할 수 있는 @RequestBody, @ResponseBody 애노테이션을 사용한 메서드 V4
     * @param messageBody
     * @return
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        log.info("messageBody = {}", messageBody);

        return "ok";
    }

}
