
package bibtexpro.validation;

import bibtexpro.domain.Reference;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import bibtexpro.validation.Field;

public class TypeValidator {
    
    private Set<String> requiredFields;
    private Set<String> validFields;
    private List<String> errors;
    
    public TypeValidator(String[] required, String[] optional){
        requiredFields = new HashSet<>();
        validFields = new HashSet<>();
        
        requiredFields.addAll(Arrays.asList(required));
        validFields.addAll(Arrays.asList(required));
        validFields.addAll(Arrays.asList(optional));
    }
    
    public List<String> validate(Reference ref){
        errors = new ArrayList<>();
        Collection<String> fields = ref.getAttributes().keySet();
        
        if(ref.getRefId().trim().isEmpty()){
            errors.add("The field Id cannot be empty.");
        }
        errors.addAll(containsAdditionalFields(fields));
        errors.addAll(missingRequiredFields(fields));
        
        return errors;
    }
    
    private List<String> containsAdditionalFields(Collection<String> fields){
        List<String> cafErrors = new ArrayList<>();
        for(String field : fields){
            if(!validFields.contains(field)){
                cafErrors.add("Reference contains a field that isn't allowed: "+field);
            }
        }
        return cafErrors;
    }
    
    private List<String> missingRequiredFields(Collection<String> fields){
        List<String> mrfErrors = new ArrayList<>();
        for(String requiredField : requiredFields){
            if(!fields.contains(requiredField)){
                mrfErrors.add("Reference is missing a required field :"+requiredField);
            }
        }
        return mrfErrors;
    }
}