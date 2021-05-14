package fox.spring.mvc;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Controller
public class ResourceController {

    @RequestMapping(value = "cardStyle.css")
    public ModelAndView getCardStyle() {
        return new ModelAndView("cardStyle");
    }

    @RequestMapping(value = "nameIcon.png")
    public ModelAndView getNameIcon() {
        return new ModelAndView("nameIcon");
    }
}
