package bibtexpro.domain;

import java.util.Map;

class BibTexParser {

    public static String toBibTex(Reference ref) {
        StringBuilder b = new StringBuilder("@");
        b.append(ref.getType() + '{' + ref.getRefId());
        Map<String, String> attributes = ref.getAttributes();
        for (String key : attributes.keySet()) {
            String value = attributes.get(key).trim();
            if (value.isEmpty()) {
                continue;
            }
            b.append(",\n");
            b.append(key + " = {" + stringToBibTex(value) + "}");
        }
        b.append(",\n}\n\n");
        return b.toString();
    }

    private static String stringToBibTex(String s) {
        StringBuilder b = new StringBuilder("");
        int j;
        for (int i = 0; i < s.length(); i++) {
            j = i;
            if (Character.isUpperCase(s.charAt(i))) {
                while (j + 1 < s.length() && Character.isUpperCase(s.charAt(j + 1))) {
                    j++;
                }
            }
            if (i < j) {
                while (i < j) {
                    b.append(toBibTexUpperCase(s.charAt(i)));
                    i++;
                }
                b.append(toBibTexUpperCase(s.charAt(i)));
            } else {
                b.append(charToBibTex(s.charAt(i)));
            }
        }
        return b.toString();
    }

    private static String toBibTexUpperCase(char charAt) {
        return "{" + charToBibTex(charAt) + "}";
    }

    private static String charToBibTex(char charAt) {
        switch (charAt) {
            case 'ä':
                return "\\\"{a}";
            case 'ö':
                return "\\\"{o}";
            case 'å':
                return "\\\'{a}";
            case 'Ä':
                return "\\\"{A}";
            case 'Ö':
                return "\\\"{O}";
            case 'Å':
                return "\\\'{A}";
            default:
                return Character.toString(charAt);
        }
    }
}
