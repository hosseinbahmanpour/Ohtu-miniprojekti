package bibtexpro.controller;

import bibtexpro.domain.Reference;
import bibtexpro.validation.ReferenceValidator;
import bibtexpro.repository.ReferenceRepository;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createNewReference(@RequestParam Map<String, String> allRequestParams, Model model) {
        Reference newRef = new Reference(allRequestParams);
        ReferenceValidator referenceValidator = new ReferenceValidator();
        if (referenceValidator.validate(newRef)) {
            referenceRepository.save(newRef);
            return "redirect:/list";
        } else {
            if(allRequestParams.containsKey("type")){
                String type = allRequestParams.get("type");
                allRequestParams.remove("type");
                Map<String,String> params = new HashMap<>();
                for(Map.Entry<String,String> e : allRequestParams.entrySet()){
                    params.put(e.getKey(), e.getValue());
                }
                model.addAttribute(type, params);
            }
            return "addReferenceForm";
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showForm(){
        return "addReferenceForm";
    }
    
    @RequestMapping(value = "list/{id}", method = RequestMethod.DELETE)
    public String deleteReference(@PathVariable String id) {
        referenceRepository.delete(id);
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String listReferences(Model model) {
        model.addAttribute("references", referenceRepository.findAll());
        return "list";
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable String id) {
        model.addAttribute("reference", referenceRepository.findByRefId(id));
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

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String deleteAll() {
        referenceRepository.deleteAll();
        return "redirect:/";
    }

}
