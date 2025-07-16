package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.FindDiffer;

import java.util.Map;
import java.util.TreeMap;

public final class Json implements FormatStyle {
    @Override
    public String format(TreeMap<String, Map<String, Object>> dif) throws JsonProcessingException {
        Map<String, Object> added = new TreeMap<>();
        Map<String, Object> removed = new TreeMap<>();
        Map<String, Object> updated = new TreeMap<>();

        dif.forEach((key, details) -> {
            String status = (String) details.get("status");
            switch (status) {
                case "added" ->
                        added.put(key, details.get("value"));
                case "removed" ->
                        removed.put(key, details.get("value"));
                case "updated" ->
                        updated.put(key, FindDiffer.createValueMap(details.get("oldValue"), details.get("newValue")));
                default -> {

                }
            }
        });

        Map<String, Object> finalFormat = new TreeMap<>();
        finalFormat.put("added", added);
        finalFormat.put("removed", removed);
        finalFormat.put("updated", updated);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(finalFormat);
    }
}

