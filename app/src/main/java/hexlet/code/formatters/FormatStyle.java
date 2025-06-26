package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface FormatStyle {
    String format(Map<String, Object> data1, Map<String, Object> data2) throws JsonProcessingException;
}
