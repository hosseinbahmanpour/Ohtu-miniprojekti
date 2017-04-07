package bibtexpro.controller;

import bibtexpro.domain.Reference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String createNewReference(@RequestParam Map<String, String> allRequestParams) {
        if (references == null) {
            this.references = new ArrayList<>();
        }
        Reference newRef = new Reference(allRequestParams);
        references.add(newRef);
        return "redirect:/addreference";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String addBook(Model model) {
        return "addbook";
    }
    
    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public String createNewBook(@RequestParam Map<String,String> allRequestParams){
        if(references == null){
            this.references = new ArrayList<>();
        }
        Reference newRef = new Reference(allRequestParams);
        references.add(newRef);
        return "redirect:/";
    }

    @RequestMapping(value = "/addarticle", method = RequestMethod.GET)
    public String addArticle(Model model) {
        return "addarticle";
    }
    
    @RequestMapping(value = "/addarticle", method = RequestMethod.POST)
    public String createNewArticle(@RequestParam Map<String,String> allRequestParams){
        if(references == null){
            this.references = new ArrayList<>();
        }
        Reference newRef = new Reference(allRequestParams);
        references.add(newRef);
        return "redirect:/";
    }

    @RequestMapping(value = "/addinproceeding", method = RequestMethod.GET)
    public String addInproceeding(Model model) {
        return "addinproceeding";
    }
    
    @RequestMapping(value = "/addinproceeding", method = RequestMethod.POST)
    public String createNewInproceeding(@RequestParam Map<String,String> allRequestParams){
        if(references == null){
            this.references = new ArrayList<>();
        }
        Reference newRef = new Reference(allRequestParams);
        references.add(newRef);
        return "redirect:/";
    }

    @RequestMapping("/list")
    public String listReferences(Model model) {
        model.addAttribute("references", references);
        return "list";
    }

    @RequestMapping("/list/{id}")
    public String show(@PathVariable int id) {
        //show a single reference with all info, search by id.
        return "redirect:/list";
    }

    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "Book");
        map.put("id", "testiId");
        map.put("title", "testiKirja");
        map.put("author", "Meikalainen, Matti");
        map.put("year", "2017");
        Reference ref = new Reference(map);
        return ref.toBibTex();
    }

    @ResponseBody
    @RequestMapping("/test1")
    public String test1() {
        String s = "";
        if (references != null && !references.isEmpty()) {
            s = references.get(0).toBibTex();
        }
        return s;
    }

    @RequestMapping(value = "/bibtex", method = RequestMethod.GET)
    public ResponseEntity<byte[]> viewFile() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.add("Content-Disposition", "attachment; filename=references.bib");
        String s = "";
        for (Reference reference : references) {
            s += reference.toBibTex();
        }
        headers.setContentLength(s.getBytes().length);
        return new ResponseEntity<>(s.getBytes(), headers, HttpStatus.CREATED);
    }
}
