package hexlet.code.formatters;

import java.util.Map;
import java.util.TreeMap;

public final class Stylish implements FormatStyle {
    @Override
    @SuppressWarnings("squid:S125")// Данный метод принят в тестировании и поддерживает необходимые случаи
    public String format(TreeMap<String, Map<String, Object>> dif) {
        StringBuilder builder = new StringBuilder("{");
        builder.append(System.lineSeparator());

        dif.forEach((key, details) -> {
            String status = (String) details.get("status");
            switch (status) {
                case "updated" -> builder.append(String.format("  - %s: %s%n  + %s: %s%n",
                        key, details.get("oldValue"), key, details.get("newValue")));
                case "added" -> builder.append(String.format("  + %s: %s%n", key, details.get("value")));
                case "removed" -> builder.append(String.format("  - %s: %s%n", key, details.get("value")));
                case "unchanged" -> builder.append(String.format("    %s: %s%n", key, details.get("value")));
                default -> {
                    throw new RuntimeException("Error value" + status);
                }
            }
        });

        builder.append("}");
        return builder.toString();
    }
}
