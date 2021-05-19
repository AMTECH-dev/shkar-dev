package fox.spring.mvc;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {
    String tmp = "Hello from DefaultController by Spring MVC Framework!";

    @RequestMapping(value = "/")
    public String getResource(ModelMap model) {
        model.addAttribute("message", tmp);
//        return "default-view";
        return "card";
    }

    @RequestMapping(value = "/index")
    public String showIndexPage() {
        return "index";
    }

    @RequestMapping(value = "/card")
    public String showCardPage() {
        return "card";
    }

    @RequestMapping(value = "/errPage")
    public String showErrPage() {
        return "errPage";
    }
}