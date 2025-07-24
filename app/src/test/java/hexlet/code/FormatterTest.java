package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.FormatStyle;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FormatterTest {
    private static final FormatStyle STYLISH = new Stylish();
    private static final FormatStyle PLAIN = new Plain();
    private static final FormatStyle JSON = new Json();
    @Test
    void testRemovedStylish() throws JsonProcessingException {
        TreeMap<String, Map<String, Object>> dif = new TreeMap<>();
        Map<String, Object> details = new HashMap<>();
        details.put("status", "removed");
        details.put("value", "SomeValue");
        dif.put("propertyKey", details);

        String result = STYLISH.format(dif);
        assertTrue(result.contains("- propertyKey: SomeValue"));
    }

    @Test
    void testAddedStylish() throws JsonProcessingException {
        TreeMap<String, Map<String, Object>> dif = new TreeMap<>();
        Map<String, Object> details = new HashMap<>();
        details.put("status", "added");
        details.put("value", "SomeValue");
        dif.put("propertyKey", details);

        String result = STYLISH.format(dif);
        assertTrue(result.contains("+ propertyKey: SomeValue"));
    }

    @Test
    void testUnknownStatus() {
        TreeMap<String, Map<String, Object>> dif = new TreeMap<>();
        Map<String, Object> details = new HashMap<>();
        details.put("status", "unknown");
        dif.put("propertyKey", details);

        assertThrows(RuntimeException.class, () -> STYLISH.format(dif), "Error value");
    }

    @Test
    void testRemovedPlain() throws JsonProcessingException {
        TreeMap<String, Map<String, Object>> dif = new TreeMap<>();
        Map<String, Object> details = new HashMap<>();
        details.put("status", "removed");
        details.put("value", "SomeValue");
        dif.put("key", details);

        String result = PLAIN.format(dif);

        assertTrue(result.contains("Property 'key' was removed"));
    }

    @Test
    void testAddedPlain() throws JsonProcessingException {
        TreeMap<String, Map<String, Object>> dif = new TreeMap<>();
        Map<String, Object> details = new HashMap<>();
        details.put("status", "added");
        details.put("value", "SomeValue");
        dif.put("key", details);

        String result = PLAIN.format(dif);

        assertTrue(result.contains("Property 'key' was added with value: 'SomeValue'"));
    }

    @Test
    void testFormatValidData() throws Exception {
        TreeMap<String, Map<String, Object>> diff = new TreeMap<>();
        Map<String, Object> details = Map.of("status", "updated", "oldValue", "old", "newValue", "new");
        diff.put("someKey", details);

        String result = JSON.format(diff);

        assertTrue(result.contains("\"someKey\""));
        assertTrue(result.contains("\"status\" : \"updated\""));
    }
}
