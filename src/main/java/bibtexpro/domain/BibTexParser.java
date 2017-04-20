package bibtexpro.domain;

import java.util.Map;

class BibTexParser {

    public static String toBibTex(Reference ref) {
        StringBuilder b = new StringBuilder("@");
        b.append(ref.getType() + '{' + ref.getId());
        Map<String, String> attributes = ref.getAttributes();
        for (String key : attributes.keySet()) {
            String value = attributes.get(key).trim();
            if(value.isEmpty()) continue;
            b.append(",\n");
            b.append(key + " = " + StringToBibTex(value));
        }
        b.append(",\n}\n\n");
        return b.toString();
    }

    private static String StringToBibTex(String s) {
        return "{"+s+"}";
    }
}
