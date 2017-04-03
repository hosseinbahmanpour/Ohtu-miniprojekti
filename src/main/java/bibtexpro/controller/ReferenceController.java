package bibtexpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReferenceController {

    @RequestMapping("/addreference")
    public String addReference(Model model) {
        return "addreference";
    }

}
