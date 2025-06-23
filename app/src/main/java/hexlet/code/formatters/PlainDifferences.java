package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class PlainDifferences {
    public static TreeMap<String, String> dif(Map<String, Object> data1, Map<String, Object> data2) {
        TreeMap<String, String> commonData = new TreeMap<>();

        for (Map.Entry<String, Object> entry : data1.entrySet()) {
            if (!data2.containsKey(entry.getKey())) {
                commonData.put(entry.getKey(), String.format("Property '%s' was removed", entry.getKey()));
            }

            if (data2.containsKey(entry.getKey()) && !Objects.equals(entry.getValue(), data2.get(entry.getKey()))) {
                commonData.put(entry.getKey(), String.format("Property '%s' was updated. From %s to %s",
                        entry.getKey(), formatValue(entry.getValue()), formatValue(data2.get(entry.getKey()))));
            }
        }

        for (Map.Entry<String, Object> entry : data2.entrySet()) {
            if (!data1.containsKey(entry.getKey())) {
                commonData.put(entry.getKey(), String.format("Property '%s' was added with value: %s",
                        entry.getKey(), formatValue(entry.getValue())));
            }
        }

        return commonData;
    }

    private static String formatValue(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return String.format("'%s'", value);
        }
        return String.valueOf(value);
    }
}
