package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Objects;
import java.util.HashMap;

public final class FindDiffer {
    private static final String VALUE = "value";
    private static final String STATUS = "status";

    private FindDiffer() {
    }
    @SuppressWarnings("java:S1319")
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
                diffDetails.put(STATUS, "removed");
                diffDetails.put(VALUE, value1);
            } else if (!data1.containsKey(key)) {
                diffDetails.put(STATUS, "added");
                diffDetails.put(VALUE, value2);
            } else if (!Objects.equals(value1, value2)) {
                diffDetails.put(STATUS, "updated");
                diffDetails.put("oldValue", value1);
                diffDetails.put("newValue", value2);
            } else {
                diffDetails.put(STATUS, "unchanged");
                diffDetails.put(VALUE, value1);
            }

            result.put(key, diffDetails);
        }

        return result;
    }
}
