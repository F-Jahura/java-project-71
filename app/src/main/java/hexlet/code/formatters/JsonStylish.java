package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public class JsonStylish implements FormatStyle {
    @Override
    public String format(Map<String, Object> data1, Map<String, Object> data2) throws JsonProcessingException {
        return JsonDifferences.dif(data1, data2);
    }
}
