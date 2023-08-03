package pe.edu.cibertec.aw1.farmacia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;

// http://localhost:8080/

@Controller
public class HomeController {
    
    // @RequestMapping(path = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @GetMapping("/nosotros")
    public String about() {
        return "about";
    }
}
