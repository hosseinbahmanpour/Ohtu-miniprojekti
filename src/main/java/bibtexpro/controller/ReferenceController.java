package bibtexpro.controller;

import bibtexpro.domain.Reference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReferenceController {
    List<Reference> references;
    
    
    @RequestMapping(value = "/addreference", method = RequestMethod.GET)
    public String addReference(Model model) {
        return "addreference";
    }
    
    @RequestMapping(value = "/addreference", method = RequestMethod.POST)
    public String createNewReference(@RequestParam Map<String,String> allRequestParams){
        if(references == null){
            this.references = new ArrayList<>();
        }
        Reference newRef = new Reference(allRequestParams);
        references.add(newRef);
        return "redirect:/";
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
