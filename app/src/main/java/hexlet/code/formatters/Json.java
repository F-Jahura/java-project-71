package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.Differ;

import java.util.Map;
import java.util.TreeMap;

public class Json implements FormatStyle {
    @Override
    public String format(Map<String, Object> data1, Map<String, Object> data2) throws JsonProcessingException {
        Map<String, TreeMap<String, Object>> jsonFormat = Differ.getDiff(data1, data2);

        jsonFormat.remove("unchanged");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonFormat);
    }
}
