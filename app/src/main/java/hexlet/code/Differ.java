package hexlet.code;


import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Objects;
import java.util.HashMap;

public class Differ {
    public static TreeMap<String, Map<String, Object>> getDiff(Map<String, Object> data1,
                                                                Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        TreeMap<String, Map<String, Object>> result = new TreeMap<>();

        for (String key : keys) {
            Map<String, Object> diffDetails = new HashMap<>();
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (!data2.containsKey(key)) {
                diffDetails.put("status", "removed");
                diffDetails.put("value", value1);
            } else if (!data1.containsKey(key)) {
                diffDetails.put("status", "added");
                diffDetails.put("value", value2);
            } else if (!Objects.equals(value1, value2)) {
                diffDetails.put("status", "updated");
                diffDetails.put("oldValue", value1);
                diffDetails.put("newValue", value2);
            } else {
                diffDetails.put("status", "unchanged");
                diffDetails.put("value", value1);
            }

            result.put(key, diffDetails);
        }

        return result;
    }

    public static TreeMap<String, Object> createValueMap(Object oldValue, Object newValue) {
        TreeMap<String, Object> valueMap = new TreeMap<>();
        valueMap.put("oldValue", oldValue);
        valueMap.put("newValue", newValue);
        return valueMap;
    }
}
