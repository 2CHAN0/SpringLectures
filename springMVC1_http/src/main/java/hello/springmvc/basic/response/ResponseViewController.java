package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello! v1");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "Hello! v2");
        return "response/hello";
    }

    @RequestMapping("/response/hello") //스트링을 생략하면 request url을 암묵적으로 view경로로 넣어줌 비추!
    public void responseViewV3(Model model) {
        model.addAttribute("data", "Hello! v3");
    }
}
