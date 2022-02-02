package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    /**
     * ModelAndView 를 반환하는 경우
     * @return
     */
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello");
        return mav;
    }

    /**
     * String 반환하는 경우
     * ResponseBody 애노테이션이 있으면 바로 문자열을 메시지 바디에 넣고 응답
     * 없으면, templates/response/hello.html 을 리턴
     * @param model
     * @return
     */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello");
        return "response/hello";
    }

    /**
     * void 를 반환하는 경우
     * 권장 X
     * 컨트롤러와 매핑되는 URL 경로를 templates 하위에 일치하는 경로의 파일이 있다면, 그 파일을 리턴
     * @param model
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello");
    }
}
