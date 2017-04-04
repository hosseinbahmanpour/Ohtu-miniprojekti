package bibtexpro.domain;

import java.util.Map;

public class Reference {
    
    private Map<String, String> attributes;
    private String type;
    private String id;

    public Reference(Map<String, String> inputFields) {
        this.type = inputFields.get("type");
        inputFields.remove("type");
        this.id = inputFields.get("id");
        inputFields.remove("id");
        this.attributes = inputFields;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttribute(String key, String value) {
        this.attributes.put(key, value);
    }
    
    public String toBibTex() {
        return BibTexParser.toBibTex(this);
    }
    
}