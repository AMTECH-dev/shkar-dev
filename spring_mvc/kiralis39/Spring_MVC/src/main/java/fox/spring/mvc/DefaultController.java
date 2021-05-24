package fox.spring.mvc;


import fox.spring.models.CardForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class DefaultController {
    String tmp = "Hello from DefaultController by Spring MVC Framework!";

    @RequestMapping(value = "/")
    public String getResource() {
        return "default-view";
    }

    @RequestMapping(value = "/index")
    public String showIndexPage() {
        return "index";
    }

    @RequestMapping(value = "/card")
    public String showCardPage(Model model) {
//        CardForm defaultCardModel = new CardForm();
//        defaultCardModel.setFio("Романенко Кирилл Олегович");

        model.addAttribute("cardForm", new CardForm());
        return "card";
    }

    @RequestMapping(value = "/cardReady")
    public String resultCardPage(@Valid @ModelAttribute("cardForm") CardForm card, BindingResult result) {
        if (result.hasErrors() || result.hasFieldErrors() || result.hasGlobalErrors()) {
            return "card";
        } else {
            System.out.println("WITHOUT ERRORS (?)");
//            card.setFio("A new card was created by '" + card.getFio() + "'!");
            return "cardReady";
        }
    }

    @RequestMapping(value = "/errPage")
    public String showErrPage() {
        return "errPage";
    }
}