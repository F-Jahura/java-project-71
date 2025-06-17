package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Stylish implements Formatter {
    @Override
    public String format(Map<String, Object> data1, Map<String, Object> data2) {
        TreeMap<String, String> commonData = new TreeMap<>();

        for (Map.Entry<String, Object> entry : data1.entrySet()) {
            if (entry.getValue() instanceof List || entry.getValue() instanceof Map) {
                String stringValue = entry.getValue().toString();
                commonData.put(entry.getKey(), String.format("  %s: %s", entry.getKey(), stringValue));
            }

            if (data2.get(entry.getKey()) != null) {
                if (entry.getValue() != null && !entry.getValue().equals(data2.get(entry.getKey()))
                        || entry.getValue() == null && data2.get(entry.getKey()) != null) {
                    commonData.put(entry.getKey(), String.format("- %s: %s\n+ %s: %s",
                            entry.getKey(), entry.getValue(), entry.getKey(), data2.get(entry.getKey())));
                } else {
                    commonData.put(entry.getKey(), String.format("  %s: %s", entry.getKey(), entry.getValue()));
                }
            } else {
                commonData.put(entry.getKey(), String.format("- %s: %s", entry.getKey(), entry.getValue()));
            }
        }

        for (Map.Entry<String, Object> entry : data2.entrySet()) {
            if (!data1.containsKey(entry.getKey())) {
                String value = entry.getValue() == null ? "null" : entry.getValue().toString();
                commonData.put(entry.getKey(), String.format("+ %s: %s", entry.getKey(), value));
            }
        }

        StringBuilder builder = new StringBuilder("{\n");
        for (String value : commonData.values()) {
            builder.append(value).append("\n");
        }
        builder.append("}");

        return builder.toString();
    }

}
