package hexlet.code.formatters;


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
    public String format(TreeMap<String, Map<String, Object>> dif) {
        StringBuilder builder = new StringBuilder();

        dif.forEach((key, details) -> {
            switch ((String) details.get("status")) {
                case "removed" -> builder.append(String.format("Property '%s' was removed\n", key));
                case "added" -> builder.append(String.format("Property '%s' was added with value: %s\n", key,
                        formatValue(details.get("value"))));
                case "updated" -> builder.append(String.format("Property '%s' was updated. From %s to %s\n",
                        key, formatValue(details.get("oldValue")), formatValue(details.get("newValue"))));
                case "unchanged" -> builder.append("");
                default -> {
                    throw new RuntimeException("Error value");
                }
            }
        });

        return builder.toString().trim();
    }
}
