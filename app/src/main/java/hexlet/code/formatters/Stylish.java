package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Differ;

import java.util.Map;
import java.util.TreeMap;

public final class Stylish implements FormatStyle {
    @Override
    public String format(Map<String, Object> data1, Map<String, Object> data2) throws JsonProcessingException {
        TreeMap<String, TreeMap<String, Object>> diff = Differ.getDiff(data1, data2);

        TreeMap<String, String> allChanges = new TreeMap<>();

        diff.forEach((changeType, changes) -> changes.forEach((key, value) -> {
            String prefix = " ";
            if ("updated".equals(changeType)) {
                Map<String, Object> changeDetails = (Map<String, Object>) value;
                allChanges.put(key, String.format("  - %s: %s\n  + %s: %s",
                        key, changeDetails.get("oldValue"), key, changeDetails.get("newValue")));
            } else {
                if ("added".equals(changeType)) {
                    prefix = "+";
                } else if ("removed".equals(changeType)) {
                    prefix = "-";
                }
                allChanges.put(key, String.format("  %s %s: %s", prefix, key, value));
            }
        }));

        StringBuilder builder = new StringBuilder("{\n");
        allChanges.values().forEach(change -> builder.append(change).append("\n"));
        builder.append("}");
        return builder.toString();
    }
}
