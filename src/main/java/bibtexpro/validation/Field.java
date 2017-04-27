
package bibtexpro.validation;

public class Field {
    
    private String name;
    private boolean required;
    
    public Field(String name, boolean required){
        this.name = name;
        this.required = required;
    }
    
    public String getName(){
        return name;
    }
    
    public boolean isRequired(){
        return required;
    }
}