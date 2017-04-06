package bibtexpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AddReferenceController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("reference", null);
        return "index";
    }
    
    @PostMapping("/add")
    public String add(Model model) {
        return "redirect:/";
    }
    
    @PostMapping("/export")
    public String export(Model model) {
        return "redirect:/";
    }
}
