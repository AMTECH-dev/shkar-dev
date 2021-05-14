package spring_MVC;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    @RequestMapping("/")
    public String showFirstView() {

        return "first-view";
    }

//    @RequestMapping("/askDetails")
//    public String ask(){
//
//        return "ask-emp-details-view";
//    }

    @RequestMapping("/askDetails")
    public String askHumanDetails(Model model) {
        model.addAttribute( "human", new Human());
        return "ask-human-details-view";
    }

    @RequestMapping("/showDetails")
    public String showHumanDetails(@ModelAttribute ("human") Human human) {

        return "show-emp-details-view";
    }

}
