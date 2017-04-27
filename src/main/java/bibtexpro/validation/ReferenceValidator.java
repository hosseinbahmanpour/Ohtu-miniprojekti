
package bibtexpro.validation;

import bibtexpro.domain.Reference;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import bibtexpro.validation.Field;

public class ReferenceValidator {
    
    private Map<String,TypeValidator> validators;
    
    public ReferenceValidator(){
        TypeValidator bookValidator = createBookValidator();
        TypeValidator articleValidator = createArticleValidator();
        TypeValidator inproceedingValidator = createInproceedingValidator();
        
        validators = new HashMap<>();
        validators.put("Book", bookValidator);
        validators.put("Article", articleValidator);
        validators.put("Inproceeding", inproceedingValidator);
    }
    
    public boolean validate(Reference ref){
        String type = ref.getType();
        if(!validators.containsKey(type)) return false;
        String refId = ref.getRefId();
        if(refId.trim().isEmpty()) return false;
        
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
    
    private TypeValidator createInproceedingValidator(){
        TypeValidator inproceedingValidator = new TypeValidator(
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
                "edition",
                "month",
                "year",
                "note",
                "key"}
        );
        return inproceedingValidator;
    }
    
    
}