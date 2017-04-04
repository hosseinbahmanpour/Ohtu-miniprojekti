package bibtexpro.controller;

import bibtexpro.domain.Reference;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
=======
import java.util.HashMap;
>>>>>>> 2373bfef2cfc72073613469c98687340672c129e
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReferenceController {
    List<Reference> references;
    
    
    @RequestMapping("/addreference")
    public String addReference(Model model) {
        if(references == null){
            this.references = new ArrayList<>();
        }
        Reference newRef = new Reference(this.extractAttributes(model.asMap()));
        references.add(newRef);
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

    private Map<String, String> extractAttributes(Map<String, Object> map){
        HashMap<String, String> attributes = new HashMap<>();
        for(String key : map.keySet()){
            attributes.put(key, map.get(key).toString());
        }
        return attributes;
    }
    
}
