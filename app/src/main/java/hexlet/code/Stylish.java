package hexlet.code;

import java.util.TreeMap;

public class Stylish implements Formatter {
    @Override
    public String format(TreeMap<String, String> commonData) {
        StringBuilder builder = new StringBuilder("{\n");
        for (String value : commonData.values()) {
            builder.append(value).append("\n");
        }
        builder.append("}");

        return builder.toString();
    }
}
