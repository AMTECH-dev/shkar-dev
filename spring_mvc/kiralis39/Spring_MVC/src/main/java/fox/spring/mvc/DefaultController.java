package fox.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getResource() {
        return "default-view";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showIndexPage() {
        return "index";
    }

    @RequestMapping(value = "/card", method = RequestMethod.GET)
    public String showCardPage() {
        return "card";
    }
}