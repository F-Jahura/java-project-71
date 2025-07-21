package hexlet.code.formatters;

import java.util.Map;
import java.util.TreeMap;

public final class Stylish implements FormatStyle {
    private final String value = "value";
    private final String status = "status";
    @Override
    @SuppressWarnings("java:S112")
    public String format(TreeMap<String, Map<String, Object>> dif) {
        StringBuilder builder = new StringBuilder("{\n");

        dif.forEach((key, details) -> {
            switch ((String) details.get(status)) {
                case "updated" -> builder.append(String.format("  - %s: %s%n  + %s: %s%n",
                        key, details.get("oldValue"), key, details.get("newValue")));
                case "added" -> builder.append(String.format("  + %s: %s%n", key, details.get(value)));
                case "removed" -> builder.append(String.format("  - %s: %s%n", key, details.get(value)));
                case "unchanged" -> builder.append(String.format("    %s: %s%n", key, details.get(value)));
                default ->
                    throw new RuntimeException("Error value");
            }
        });
        builder.append("}");
        return builder.toString();
    }
}
