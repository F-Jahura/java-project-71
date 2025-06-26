package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class JsonDifferences {
    public static String dif(Map<String, Object> data1, Map<String, Object> data2) throws JsonProcessingException {
        Map<String, Map<String, Object>> commonData = new TreeMap<>();

        Map<String, Object> removedData = new TreeMap<>();
        Map<String, Object> addedData = new TreeMap<>();
        Map<String, Object> updatedData = new TreeMap<>();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        for (Map.Entry<String, Object> entry : data1.entrySet()) {
            if (!data2.containsKey(entry.getKey())) {
                removedData.put(entry.getKey(), entry.getValue());
            }

            if (data2.containsKey(entry.getKey()) && !Objects.equals(entry.getValue(), data2.get(entry.getKey()))) {
                Object oldValue = entry.getValue();
                Object newValue = data2.get(entry.getKey());
                updatedData.put(entry.getKey(), createValueMap(oldValue, newValue));
            }
        }

        for (Map.Entry<String, Object> entry : data2.entrySet()) {
            if (!data1.containsKey(entry.getKey())) {
                addedData.put(entry.getKey(), entry.getValue());
            }
        }

        commonData.put("removed", removedData);
        commonData.put("added", addedData);
        commonData.put("updated", updatedData);

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commonData);
    }

    private static Map<String, Object> createValueMap(Object oldValue, Object newValue) {
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("oldValue", oldValue);
        valueMap.put("newValue", newValue);
        return valueMap;
    }
}
