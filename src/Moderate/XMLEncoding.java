package Moderate;

/**
 * 16.12 XML Encoding: encode xml which includes Element, Attribute, END, Tag and Value
 */
public class XMLEncoding {
    void encode(Element root,StringBuilder sb) {
        encode(root.getNameCode(), sb);

        // Solve attributes
        for (Attribute a : root.attributes) {
            encode(a, sb);
        }
        encode("0", sb); // end attribute

        // Encode value if has, other wise encode child's elements
        if (root.value != null && root.value != "") {
            encode(root.value, sb);
        } else {
            for (Element e : root.children) {
                encode(e, sb);
            }
        }

        encode("0", sb); // children
    }

    void encode(String s, StringBuilder sb) {
        sb.append(s);
        sb.append(" ");
    }

    void encode(Attribute a, StringBuilder sb) {
        encode(a.getTagCode(), sb);
        encode(a.value, sb);
    }

    String encodeToString(Element root) {
        StringBuilder sb = new StringBuilder();
        encode(root, sb);
        return sb.toString();
    }
}
