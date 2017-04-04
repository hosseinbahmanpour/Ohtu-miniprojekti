package bibtexpro.controller;

import bibtexpro.domain.Reference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    private Map<String, String> extractAttributes(Map<String, Object> map){
        HashMap<String, String> attributes = new HashMap<>();
        for(String key : map.keySet()){
            attributes.put(key, map.get(key).toString());
        }
        return attributes;
    }

}
