package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;
import java.util.TreeMap;

public interface FormatStyle {
    String format(TreeMap<String, Map<String, Object>> dif) throws JsonProcessingException;
}
