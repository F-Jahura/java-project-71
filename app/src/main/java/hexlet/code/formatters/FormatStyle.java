package hexlet.code.formatters;

import java.util.Map;

public interface FormatStyle {
    String format(Map<String, Object> data1, Map<String, Object> data2);
}
