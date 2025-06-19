package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Differences {
    public static TreeMap<String, String> differ(Map<String, Object> data1, Map<String, Object> data2) {
        TreeMap<String, String> commonData = new TreeMap<>();

        for (Map.Entry<String, Object> entry : data1.entrySet()) {
            if (!data2.containsKey(entry.getKey())) {
                commonData.put(entry.getKey(), String.format("- %s: %s", entry.getKey(), entry.getValue()));
            }

            if (data2.containsKey(entry.getKey()) && Objects.equals(entry.getValue(), data2.get(entry.getKey()))) {
                commonData.put(entry.getKey(), String.format("  %s: %s", entry.getKey(), entry.getValue()));
            }

            if (data2.containsKey(entry.getKey()) && !Objects.equals(entry.getValue(), data2.get(entry.getKey()))) {
                commonData.put(entry.getKey(), String.format("- %s: %s\n+ %s: %s",
                        entry.getKey(), entry.getValue(), entry.getKey(), data2.get(entry.getKey())));
            }
        }

        for (Map.Entry<String, Object> entry : data2.entrySet()) {
            if (!data1.containsKey(entry.getKey())) {
                commonData.put(entry.getKey(), String.format("+ %s: %s", entry.getKey(), entry.getValue()));
            }
        }

        return commonData;
    }
}
