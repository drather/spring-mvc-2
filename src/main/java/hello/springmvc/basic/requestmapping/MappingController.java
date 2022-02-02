package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * value 사용
     * URL 과 컨트롤러를 매핑
     * @return
     */
    @RequestMapping(value = {"/hello-basic", "/hello-basic-2"}, method = RequestMethod.GET)
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * @PathVariable 사용
     * 경로 변수를 컨트롤러에 파라미터로 넘김
     * 식별자를 URL 에 넣는 요즘 HTTP API 설계 방식에 적합
     * 변수명이 같으면 생략 가능
     * @return
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath: {}", userId);
        return "ok";
    }

    /**
     * 여러개의 PathVariable 사용
     * @param userId
     * @param orderId
     * @return
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId = {}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * 특정 파라미터(mode=debug) 가 있어야만 호출됨
     * 없으면 호출되지 않음 -> Bad Request 를 return
     *
     * params="!mode" 등 다양한 표현 사용 가능
     * @return
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * 원하는 헤더가 있어야만 호출되는 메서드
     * headers="mode"
     *
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!appilcation/json"
     */
    @GetMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * ...
     * @return
     */
    @GetMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces(){
        log.info("mappingProduces");
        return "ok";
    }
}
