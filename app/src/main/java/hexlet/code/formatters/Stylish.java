package hexlet.code.formatters;

import java.util.Map;
import java.util.TreeMap;

public final class Stylish implements FormatStyle {
    @Override
    public String format(Map<String, Object> data1, Map<String, Object> data2) {
        TreeMap<String, Object> commonData = Differences.differ(data1, data2);
        StringBuilder builder = new StringBuilder("{\n");
        for (Object value : commonData.values()) {
            builder.append(value).append("\n");
        }
        builder.append("}");

        return builder.toString().trim();
    }
}
