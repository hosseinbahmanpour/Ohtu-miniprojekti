package bibtexpro.controller;

import bibtexpro.domain.Reference;
import bibtexpro.validation.ReferenceValidator;
import bibtexpro.repository.ReferenceRepository;
import java.util.List;
import java.util.ArrayList;
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

@Controller
public class ReferenceController {

    @Autowired
    private ReferenceRepository referenceRepository;
    private List<String> successes;
    private List<String> errors;

    private void flashError(String error) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(error);
    }

    private void flashMessage(String message) {
        if (successes == null) {
            successes = new ArrayList<>();
        }

        successes.add(message);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createNewReference(@RequestParam Map<String, String> allRequestParams, Model model) {
        Reference newRef = new Reference(allRequestParams);
        String refId = newRef.getRefId();

        if (referenceRepository.findByRefId(refId) != null) {
            flashError("Reference with id " + refId + " already exists in database.");
        } else {
            ReferenceValidator referenceValidator = new ReferenceValidator();
            errors = referenceValidator.validate(newRef);
        }

        if (errors.isEmpty()) {
            referenceRepository.save(newRef);
            flashMessage("Reference " + refId + " successfully added!");
            errors = null;
            return "redirect:/list";
        } else {
            if (allRequestParams.containsKey("type")) {
                String type = allRequestParams.get("type");
                allRequestParams.remove("type");
                model.addAttribute(type, allRequestParams);
            }
            model.addAttribute("errors", errors);
            successes = null;
            errors = null;
            return "addReferenceForm";
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showForm() {
        return "addReferenceForm";
    }

    @RequestMapping(value = "list/{id}", method = RequestMethod.DELETE)
    public String deleteReference(@PathVariable String id) {
        Reference ref = referenceRepository.findByRefId(id);
        if (ref == null) {
            errors = new ArrayList<>();
            flashError("Removal failed: Could not find reference with id " + id + ".");
        } else {
            flashMessage("Successfully removed reference " + id + ".");
            referenceRepository.delete(id);
        }
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String listReferences(Model model) {
        model.addAttribute("references", referenceRepository.findAll());
        model.addAttribute("successes", successes);
        model.addAttribute("errors", errors);
        successes = null;
        errors = null;
        return "list";
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable String id) {
        Reference ref = referenceRepository.findByRefId(id);
        errors = new ArrayList<>();
        if (ref == null) {
            flashError("Could not find reference with id " + id + ".");
            model.addAttribute("errors", errors);
            return "redirect:/list";
        }

        model.addAttribute("reference", ref);
        errors = null;
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
