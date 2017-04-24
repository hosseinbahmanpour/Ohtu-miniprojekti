package bibtexpro.controller;

import bibtexpro.domain.Reference;
import bibtexpro.repository.ReferenceRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ReferenceRepository referenceRepository;

    @RequestMapping(value = "/addreference", method = RequestMethod.POST)
    public String createNewReference(@RequestParam Map<String, String> allRequestParams) {
        
        Reference newRef = new Reference(allRequestParams);
        referenceRepository.save(newRef);
        return "redirect:/";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String addBook(Model model) {
        return "addbook";
    }

    @RequestMapping(value = "/addarticle", method = RequestMethod.GET)
    public String addArticle(Model model) {
        return "addarticle";
    }

    @RequestMapping(value = "/addinproceeding", method = RequestMethod.GET)
    public String addInproceeding(Model model) {
        return "addinproceeding";
    }
   
    @RequestMapping("/list")
    public String listReferences(Model model) {
        model.addAttribute("references", referenceRepository.findAll());
        return "list";
    }

    @RequestMapping("/list/{id}")
    public String show(@PathVariable int id) {
        //show a single reference with all info, search by id.
        return "redirect:/list";
    }

    @RequestMapping(value = "/bibtex", method = RequestMethod.GET)
    public ResponseEntity<byte[]> viewFile() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.add("Content-Disposition", "attachment; filename=references.bib");
        String s = "";
        List<Reference> references = referenceRepository.findAll();
        for (Reference reference : references) {
            s += reference.toBibTex();
        }
        headers.setContentLength(s.getBytes().length);
        return new ResponseEntity<>(s.getBytes(), headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/reset", method=RequestMethod.GET)
    public String deleteAll(){
        referenceRepository.deleteAll();
        return "redirect:/";
    }
    
    
}
