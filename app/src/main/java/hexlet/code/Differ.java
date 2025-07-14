package hexlet.code;

import hexlet.code.formatters.FormatStyle;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import org.apache.commons.io.FilenameUtils;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Differ {
    public static TreeMap<String, TreeMap<String, Object>> getDiff(Map<String, Object> data1,
                                                                   Map<String, Object> data2) {
        TreeMap<String, Object> removed = new TreeMap<>();
        TreeMap<String, Object> added = new TreeMap<>();
        TreeMap<String, Object> updated = new TreeMap<>();
        TreeMap<String, Object> unchanged = new TreeMap<>();

        for (Map.Entry<String, Object> entry : data1.entrySet()) {
            if (!data2.containsKey(entry.getKey())) {
                removed.put(entry.getKey(), entry.getValue());
            }

            if (data2.containsKey(entry.getKey()) && !Objects.equals(entry.getValue(), data2.get(entry.getKey()))) {
                Object oldValue = entry.getValue();
                Object newValue = data2.get(entry.getKey());
                updated.put(entry.getKey(), createValueMap(oldValue, newValue));
            }

            if (data2.containsKey(entry.getKey()) && Objects.equals(entry.getValue(), data2.get(entry.getKey()))) {
                unchanged.put(entry.getKey(), entry.getValue());
            }
        }

        for (Map.Entry<String, Object> entry : data2.entrySet()) {
            if (!data1.containsKey(entry.getKey())) {
                added.put(entry.getKey(), entry.getValue());
            }
        }

        TreeMap<String, TreeMap<String, Object>> result = new TreeMap<>();
        result.put("removed", removed);
        result.put("added", added);
        result.put("updated", updated);
        result.put("unchanged", unchanged);

        return result;
    }

    private static TreeMap<String, Object> createValueMap(Object oldValue, Object newValue) {
        TreeMap<String, Object> valueMap = new TreeMap<>();
        valueMap.put("oldValue", oldValue);
        valueMap.put("newValue", newValue);
        return valueMap;
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        String type = FilenameUtils.getExtension(filePath1 + filePath2);

        Map<String, Object> data1 = Parser.parsing(filePath1, type);
        Map<String, Object> data2 = Parser.parsing(filePath2, type);

        FormatStyle formatStyle;
        if ("plain".equalsIgnoreCase(formatName)) {
            formatStyle = new Plain();
        } else if ("stylish".equalsIgnoreCase(formatName)) {
            formatStyle = new Stylish();
        } else if ("json".equalsIgnoreCase(formatName)) {
            formatStyle = new Json();
        } else {
            throw new UnsupportedOperationException("Unsupported format: " + formatName);
        }

        return formatStyle.format(data1, data2);
    }

    public static String generate(String filePath1, String  filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

}
