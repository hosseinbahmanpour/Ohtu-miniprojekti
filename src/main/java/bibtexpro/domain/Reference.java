package bibtexpro.domain;

import java.util.Map;
import java.util.HashMap;
import org.springframework.data.annotation.Id;

public class Reference {
    
    @Id
    private String refId;
    
    private Map<String, String> attributes;
    private String type;
    
    public Reference() {}

    public Reference(Map<String, String> inputFields) {
        this.type = inputFields.get("type");
        inputFields.remove("type");
        this.refId = inputFields.get("refId");
        inputFields.remove("refId");
        
        attributes = new HashMap<>();
        for(Map.Entry<String,String> entry : inputFields.entrySet()){
            String key = entry.getKey();
            String val = entry.getValue().trim();
            if(!val.isEmpty()){
                attributes.put(key,val);
            }
        }
        
        inputFields.put("type", type);
        inputFields.put("refId", refId);
    }

    public String getRefId() {
        return refId;
    }

    public String getType() {
        return type;
    }

    public void setrefId(String refId) {
        this.refId = refId;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttribute(String key, String value) {
        this.attributes.put(key, value);
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
    
    public String toBibTex() {
        return BibTexParser.toBibTex(this);
    }
    
    public boolean valid() {
        return true;
    }
    
}