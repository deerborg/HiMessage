package art.deerborg.hichat.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
