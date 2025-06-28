package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AppTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    private static String formatValue(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {



            return String.format("'%s'", value);
        }
        return String.valueOf(value);
    }

    @Test
    public void testJson() throws Exception {
        //var filePath1 = Paths.get("src", "test", "resources", "file1.json");
        //var filePath2 = Paths.get("src", "test", "resources", "file2.json");
        File filePath1 = Paths.get("src", "test", "resources", "file1.json").toFile();
        File filePath2 = Paths.get("src", "test", "resources", "file2.json").toFile();

        App app = new App();
        app.filepath1 = filePath1; //new File(filePath1.toString());
        app.filepath2 = filePath2; //new File(filePath2.toString());
        app.call();

        String expectedOutput = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}\n";
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    public void testYaml() throws Exception {
        var filePath1 = Paths.get("src", "test", "resources", "file1.yaml").toFile();
        var filePath2 = Paths.get("src", "test", "resources", "file2.yaml").toFile();

        App app = new App();
        app.filepath1 = filePath1; //new File(filePath1.toString());
        app.filepath2 = filePath2; //new File(filePath2.toString());
        app.call();

        String expectedOutput = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}\n";
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    public void testObjArrayJson() throws Exception {
        var filePath1 = Paths.get("src", "test", "resources", "filepath1.json").toFile();
        var filePath2 = Paths.get("src", "test", "resources", "filepath2.json").toFile();

        App app = new App();
        app.filepath1 = filePath1; //new File(filePath1.toString());
        app.filepath2 = filePath2; //new File(filePath2.toString());
        app.call();

        String expectedOutput = "{\n"
                + "  chars1: [a, b, c]\n"
                + "- chars2: [d, e, f]\n"
                + "+ chars2: false\n"
                + "- checked: false\n"
                + "+ checked: true\n"
                + "- default: null\n"
                + "+ default: [value1, value2]\n"
                + "- id: 45\n"
                + "+ id: null\n"
                + "- key1: value1\n"
                + "+ key2: value2\n"
                + "  numbers1: [1, 2, 3, 4]\n"
                + "- numbers2: [2, 3, 4, 5]\n"
                + "+ numbers2: [22, 33, 44, 55]\n"
                + "- numbers3: [3, 4, 5]\n"
                + "+ numbers4: [4, 5, 6]\n"
                + "+ obj1: {nestedKey=value, isNested=true}\n"
                + "- setting1: Some value\n"
                + "+ setting1: Another value\n"
                + "- setting2: 200\n"
                + "+ setting2: 300\n"
                + "- setting3: true\n"
                + "+ setting3: none\n"
                + "}\n";
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    public void testObjArrayYaml() throws Exception {
        var filePath1 = Paths.get("src", "test", "resources", "filepath1.yaml").toFile();
        var filePath2 = Paths.get("src", "test", "resources", "filepath2.yaml").toFile();

        App app = new App();
        app.filepath1 = filePath1; //new File(filePath1.toString());
        app.filepath2 = filePath2; //new File(filePath2.toString());
        app.call();

        String expectedOutput = "{\n"
                + "  chars1: [a, b, c]\n"
                + "- chars2: [d, e, f]\n"
                + "+ chars2: false\n"
                + "- checked: false\n"
                + "+ checked: true\n"
                + "- default: null\n"
                + "+ default: [value1, value2]\n"
                + "- id: 45\n"
                + "+ id: null\n"
                + "- key1: value1\n"
                + "+ key2: value2\n"
                + "  numbers1: [1, 2, 3, 4]\n"
                + "- numbers2: [2, 3, 4, 5]\n"
                + "+ numbers2: [22, 33, 44, 55]\n"
                + "- numbers3: [3, 4, 5]\n"
                + "+ numbers4: [4, 5, 6]\n"
                + "+ obj1: {nestedKey=value, isNested=true}\n"
                + "- setting1: Some value\n"
                + "+ setting1: Another value\n"
                + "- setting2: 200\n"
                + "+ setting2: 300\n"
                + "- setting3: true\n"
                + "+ setting3: none\n"
                + "}\n";
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    public void testPlainJson() throws Exception {
        var filePath1 = Paths.get("src", "test", "resources", "filepath1.json").toFile();
        var filePath2 = Paths.get("src", "test", "resources", "filepath2.json").toFile();

        App app = new App();
        app.format = "plain";
        app.filepath1 = filePath1; //new File(filePath1.toString());
        app.filepath2 = filePath2; //new File(filePath2.toString());
        app.call();

        String expectedOutput = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'\n";

        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    public void testJsonJson() throws Exception {
        var filePath1 = Paths.get("src", "test", "resources", "filepath1.json").toFile();
        var filePath2 = Paths.get("src", "test", "resources", "filepath2.json").toFile();

        App app = new App();
        app.format = "json";
        app.filepath1 = filePath1; //new File(filePath1.toString());
        app.filepath2 = filePath2; // new File(filePath2.toString());
        app.call();

        String expectedJsonString = "{\n"
                + "  \"added\" : {\n"
                + "    \"key2\" : \"value2\",\n"
                + "    \"numbers4\" : [4, 5, 6],\n"
                + "    \"obj1\" : {\n"
                + "      \"nestedKey\" : \"value\",\n"
                + "      \"isNested\" : true\n"
                + "    }\n"
                + "  },\n"
                + "  \"removed\" : {\n"
                + "    \"key1\" : \"value1\",\n"
                + "    \"numbers3\" : [ 3, 4, 5 ]\n"
                + "  },\n"
                + "  \"updated\" : {\n"
                + "    \"chars2\" : {\n"
                + "      \"newValue\" : false,\n"
                + "      \"oldValue\" : [ \"d\", \"e\", \"f\" ]\n"
                + "    },\n"
                + "    \"checked\" : {\n"
                + "      \"newValue\" : true,\n"
                + "      \"oldValue\" : false\n"
                + "    },\n"
                + "    \"default\" : {\n"
                + "      \"newValue\" : [ \"value1\", \"value2\" ],\n"
                + "      \"oldValue\" : null\n"
                + "    },\n"
                + "    \"id\" : {\n"
                + "      \"newValue\" : null,\n"
                + "      \"oldValue\" : 45\n"
                + "    },\n"
                + "    \"numbers2\" : {\n"
                + "      \"newValue\" : [ 22, 33, 44, 55 ],\n"
                + "      \"oldValue\" : [ 2, 3, 4, 5 ]\n"
                + "    },\n"
                + "    \"setting1\" : {\n"
                + "      \"newValue\" : \"Another value\",\n"
                + "      \"oldValue\" : \"Some value\"\n"
                + "    },\n"
                + "    \"setting2\" : {\n"
                + "      \"newValue\" : 300,\n"
                + "      \"oldValue\" : 200\n"
                + "    },\n"
                + "    \"setting3\" : {\n"
                + "      \"newValue\" : \"none\",\n"
                + "      \"oldValue\" : true\n"
                + "    }\n"
                + "  }\n"
                + "}";

        /*ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> expectedJson = objectMapper.readValue(expectedJsonString,
                new TypeReference<Map<String, Object>>() { });

        Map<String, Object> actualJson = objectMapper.readValue(outputStream.toString(),
                new TypeReference<Map<String, Object>>() { });

        assertEquals(expectedJson, actualJson);*/

        //assertEquals(expectedJsonString.trim(), outputStream.toString().trim());

        String normalizedActualOutput = normalizeJson(outputStream.toString().trim());
        String normalizedExpectedOutput = normalizeJson(expectedJsonString.trim());

        assertEquals(normalizedExpectedOutput, normalizedActualOutput, "Данные различий не совпадают");
    }

    public String normalizeJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() { });
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
    }

}
