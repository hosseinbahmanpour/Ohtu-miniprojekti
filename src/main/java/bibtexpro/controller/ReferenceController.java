package bibtexpro.controller;

import bibtexpro.domain.Reference;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReferenceController {

    @RequestMapping("/addreference")
    public String addReference(Model model) {
        return "addreference";
    }
    
    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        Map<String, String> map = new HashMap<>();
        map.put("type", "Book");
        map.put("id", "testiId");
        map.put("title", "testiKirja");
        map.put("author", "Meikalainen, Matti");
        map.put("year", "2017");
        Reference ref = new Reference(map);
        return ref.toBibTex();
    }

}
