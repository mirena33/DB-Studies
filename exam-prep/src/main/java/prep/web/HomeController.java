package prep.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import prep.service.ItemService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ItemService itemService;

    @Autowired
    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView, HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("index");
        } else {

            modelAndView.addObject("items", this.itemService.findAllItems());
            modelAndView.setViewName("home");
        }

        return modelAndView;
    }
}
