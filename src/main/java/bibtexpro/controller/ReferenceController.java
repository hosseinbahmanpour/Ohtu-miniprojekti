package bibtexpro.controller;

import bibtexpro.domain.Reference;
import bibtexpro.validation.ReferenceValidator;
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
        ReferenceValidator referenceValidator = new ReferenceValidator();
        if(referenceValidator.validate(newRef)){
            referenceRepository.save(newRef);
            return "redirect:/";
        } else {
            return "redirect:/error";
        }
    }

    @RequestMapping(value = "list/{id}", method = RequestMethod.DELETE)
    public String deleteReference(@PathVariable String id) {
        referenceRepository.delete(id);
        return "redirect:/list";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String addBook(Model model) {
        return "addbook";
    }

    @RequestMapping(value = "/addarticle", method = RequestMethod.GET)
    public String addArticle(Model model) {
        return "addarticle";
    }

    @RequestMapping(value = "/addinproceedings", method = RequestMethod.GET)
    public String addInproceedings(Model model) {
        return "addinproceedings";
    }
   
    @RequestMapping("/list")
    public String listReferences(Model model) {
        model.addAttribute("references", referenceRepository.findAll());
        return "list";
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable String id) {
        model.addAttribute("reference", referenceRepository.findByRefId(""+id));
        //show a single reference with all info, search by id.
        return "referenceView";
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
