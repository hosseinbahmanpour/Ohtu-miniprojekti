
package bibtexpro.validation;

import bibtexpro.domain.Reference;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;
import bibtexpro.validation.Field;

public class TypeValidator {
    
    private Set<String> requiredFields;
    private Set<String> validFields;
    
    public TypeValidator(String[] required, String[] optional){
        requiredFields = new HashSet<>();
        validFields = new HashSet<>();
        
        requiredFields.addAll(Arrays.asList(required));
        validFields.addAll(Arrays.asList(required));
        validFields.addAll(Arrays.asList(optional));
    }
    
    public boolean validate(Reference ref){
        Collection<String> fields = ref.getAttributes().keySet();
        
        if(containsAdditionalFields(fields)) return false;
        if(missingRequiredFields(fields)) return false;
        
        return true;
    }
    
    private boolean containsAdditionalFields(Collection<String> fields){
        for(String field : fields){
            if(!validFields.contains(field)){
                return true;
            }
        }
        return false;
    }
    
    private boolean missingRequiredFields(Collection<String> fields){
        for(String requiredField : requiredFields){
            if(!fields.contains(requiredField)){
                System.out.println(requiredField);
                for(String f : fields){
                    System.out.print(","+f);
                }
                return true;
            }
        }
        return false;
    }
}