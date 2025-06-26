package hexlet.code.formatters;

import java.util.Map;
import java.util.TreeMap;

public class PlainSylish implements FormatStyle {
    @Override
    public String format(Map<String, Object> data1, Map<String, Object> data2) {
        TreeMap<String, Object> commonData = PlainDifferences.dif(data1, data2);
        StringBuilder builder = new StringBuilder();
        for (Object value : commonData.values()) {
            builder.append(value).append("\n");
        }

        return builder.toString().trim();
    }
}
