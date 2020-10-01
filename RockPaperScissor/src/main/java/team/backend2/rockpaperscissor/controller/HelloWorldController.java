package team.backend2.rockpaperscissor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET)
    public String hello(Model model) {
        
        model.addAttribute("greeting", "Hello Spring MVC");
        
        return "helloworld";
    }
	
}
