package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class Plain implements FormatStyle {
    private static String formatValue(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return String.format("'%s'", value);
        }
        return String.valueOf(value);
    }

    @Override
    public String format(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, TreeMap<String, Object>> plainFormat = Differ.getDiff(data1, data2);
        Map<String, String> resultMap = new TreeMap<>();

        plainFormat.forEach((changeType, changes) -> {
            changes.forEach((key, value) -> {
                String result;
                if ("removed".equals(changeType)) {
                    result = String.format("Property '%s' was removed", key);
                } else if ("added".equals(changeType)) {
                    result = String.format("Property '%s' was added with value: %s", key, formatValue(value));
                } else if ("updated".equals(changeType)) {
                    Map<String, Object> changeDetails = (Map<String, Object>) value;
                    result = String.format("Property '%s' was updated. From %s to %s",
                            key, formatValue(changeDetails.get("oldValue")),
                            formatValue(changeDetails.get("newValue")));
                } else {
                    return;
                }
                resultMap.put(key, result);
            });
        });

        return String.join("\n", resultMap.values());
    }
}
