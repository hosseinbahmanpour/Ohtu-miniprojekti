
package bibtexpro.validation;

import bibtexpro.domain.Reference;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class ReferenceValidator {
    
    private Map<String,TypeValidator> validators;
    
    public ReferenceValidator(){
        TypeValidator bookValidator = createBookValidator();
        TypeValidator articleValidator = createArticleValidator();
        TypeValidator inproceedingsValidator = createInproceedingsValidator();
        
        validators = new HashMap<>();
        validators.put("Book", bookValidator);
        validators.put("Article", articleValidator);
        validators.put("Inproceedings", inproceedingsValidator);
    }
    
    public List<String> validate(Reference ref){
        
        String type = ref.getType();
        if(!validators.containsKey(type)){
            List<String> errors = new ArrayList<>();
            errors.add("Invalid type: "+type);
            return errors;
        }
        
        return validators.get(type).validate(ref);
    }
    
    private TypeValidator createBookValidator(){
        TypeValidator bookValidator = new TypeValidator(
            new String[]{
                "author",
                "title",
                "publisher",
                "year"},
            new String[]{
                "volume",
                "series",
                "address",
                "edition",
                "month",
                "note",
                "key"}
        );
        return bookValidator;
    }
    
    private TypeValidator createArticleValidator(){
        TypeValidator articleValidator = new TypeValidator(
            new String[]{
                "author",
                "title",
                "journal",
                "year",
                "volume"},
            new String[]{
                "number",
                "pages",
                "month",
                "note",
                "key"}
        );
        return articleValidator;
    }
    
    private TypeValidator createInproceedingsValidator(){
        TypeValidator inproceedingsValidator = new TypeValidator(
            new String[]{
                "author",
                "title",
                "booktitle",
                "year"},
            new String[]{
                "editor",
                "volume",
                "series",
                "pages",
                "address",
                "month",
                "organization",
                "publisher",
                "note",
                "key"}
        );
        return inproceedingsValidator;
    }
    
    
}