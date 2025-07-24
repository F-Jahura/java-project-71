package hexlet.code.formatters;

import java.util.Map;
import java.util.TreeMap;

public final class Stylish implements FormatStyle {
    private static final String VALUE = "value";
    private static final String STATUS = "status";
    @Override
    @SuppressWarnings("java:S112")
    public String format(TreeMap<String, Map<String, Object>> dif) {
        StringBuilder builder = new StringBuilder("{\n");

        dif.forEach((key, details) -> {
            String status = (String) details.get(STATUS);
            switch (status) {
                case "updated" -> builder.append(String.format("  - %s: %s%n  + %s: %s%n",
                        key, details.get("oldValue"), key, details.get("newValue")));
                case "added" -> builder.append(String.format("  + %s: %s%n", key, details.get(VALUE)));
                case "removed" -> builder.append(String.format("  - %s: %s%n", key, details.get(VALUE)));
                case "unchanged" -> builder.append(String.format("    %s: %s%n", key, details.get(VALUE)));
                default ->
                    throw new RuntimeException("Error value" + status);
            }
        });
        builder.append("}");
        return builder.toString();
    }
}
