package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
public final class AppTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String pathToStylish = "src/test/resources/stylish";
    private final String pathToPlain = "src/test/resources/plain";
    private final String pathToJson = "src/test/resources/json";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testJsonParsing() throws Exception {
        var actualPath = Paths.get("src/test/resources/filepath1.json").toString();
        String type = FilenameUtils.getExtension(actualPath);

        var expectedPath = Paths.get("src/test/resources/expected.json").toString();
        String type1 = FilenameUtils.getExtension(expectedPath);

        Map<String, Object> actual = Parser.parsing(actualPath, type);

        Map<String, Object> expected = Parser.parsing(expectedPath, type1);

        assertEquals(expected, actual);
    }

    @Test
    public void testYamlParsing() throws Exception {
        var actualPath = Paths.get("src/test/resources/filepath1.yaml").toString();
        String type = FilenameUtils.getExtension(actualPath);

        var expectedPath = Paths.get("src/test/resources/expected.yaml").toString();
        String type1 = FilenameUtils.getExtension(expectedPath);

        Map<String, Object> actual = Parser.parsing(actualPath, type);

        Map<String, Object> expected = Parser.parsing(expectedPath, type1);

        assertEquals(expected, actual);
    }

    @Test
    public void testDefaultFormat() throws Exception {
        var filePath1 = Paths.get("src", "test", "resources", "filepath1.json").toString();
        var filePath2 = Paths.get("src", "test", "resources", "filepath2.json").toString();

        var stylishPath = Paths.get(pathToStylish).toAbsolutePath().normalize();
        String expected = Files.readString(stylishPath);

        App app = new App();
        app.setFilepath1(filePath1);
        app.setFilepath2(filePath2);
        app.call();

        String expectedNormalized = expected.replaceAll("\\s+", " ").trim();
        String actualNormalized = outputStream.toString().replaceAll("\\s+", " ").trim();

        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    public void testJsonToStylish() throws Exception {
        var filePath1 = Paths.get("src", "test", "resources", "filepath1.json").toString();
        var filePath2 = Paths.get("src", "test", "resources", "filepath2.json").toString();

        var stylishPath = Paths.get(pathToStylish).toAbsolutePath().normalize();
        String expected = Files.readString(stylishPath);

        App app = new App();
        app.setFormat("stylish");
        app.setFilepath1(filePath1);
        app.setFilepath2(filePath2);
        app.call();

        String expectedNormalized = expected.replaceAll("\\s+", " ").trim();
        String actualNormalized = outputStream.toString().replaceAll("\\s+", " ").trim();

        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    public void testYamlToStylish() throws Exception {
        var filePath1 = Paths.get("src", "test", "resources", "filepath1.yaml").toString();
        var filePath2 = Paths.get("src", "test", "resources", "filepath2.yaml").toString();

        var stylishPath = Paths.get(pathToStylish).toAbsolutePath().normalize();
        String expected = Files.readString(stylishPath);

        App app = new App();
        app.setFormat("stylish");
        app.setFilepath1(filePath1);
        app.setFilepath2(filePath2);
        app.call();

        String expectedNormalized = expected.replaceAll("\\s+", " ").trim();
        String actualNormalized = outputStream.toString().replaceAll("\\s+", " ").trim();

        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    public void tesJsonToPlain() throws Exception {
        var filePath1 = Paths.get("src", "test", "resources", "filepath1.json").toString();
        var filePath2 = Paths.get("src", "test", "resources", "filepath2.json").toString();

        var plainPath = Paths.get(pathToPlain).toAbsolutePath().normalize();
        String expected = Files.readString(plainPath);

        App app = new App();
        app.setFormat("plain");
        app.setFilepath1(filePath1);
        app.setFilepath2(filePath2);
        app.call();

        String expectedNormalized = expected.replaceAll("\\s+", " ").trim();
        String actualNormalized = outputStream.toString().replaceAll("\\s+", " ").trim();

        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    public void tesYamlToPlain() throws Exception {
        var filePath1 = Paths.get("src", "test", "resources", "filepath1.yaml").toString();
        var filePath2 = Paths.get("src", "test", "resources", "filepath2.yaml").toString();

        var plainPath = Paths.get(pathToPlain).toAbsolutePath().normalize();
        String expected = Files.readString(plainPath);

        App app = new App();
        app.setFormat("plain");
        app.setFilepath1(filePath1);
        app.setFilepath2(filePath2);
        app.call();

        String expectedNormalized = expected.replaceAll("\\s+", " ").trim();
        String actualNormalized = outputStream.toString().replaceAll("\\s+", " ").trim();

        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    public void testJsonToJson() throws Exception {
        var filePath1 = Paths.get("src", "test", "resources", "filepath1.json").toString();
        var filePath2 = Paths.get("src", "test", "resources", "filepath2.json").toString();

        var jsonPath = Paths.get(pathToJson).toAbsolutePath().normalize();
        String expected = Files.readString(jsonPath);

        App app = new App();
        app.setFormat("json");
        app.setFilepath1(filePath1);
        app.setFilepath2(filePath2);
        app.call();

        String expectedNormalized = normalizeJson(expected.replaceAll("\\s+", " ").trim());
        String actualNormalized = normalizeJson(outputStream.toString().replaceAll("\\s+", " ").trim());

        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    public void testYamlToJson() throws Exception {
        var filePath1 = Paths.get("src", "test", "resources", "filepath1.yaml").toString();
        var filePath2 = Paths.get("src", "test", "resources", "filepath2.yaml").toString();

        var jsonPath = Paths.get(pathToJson).toAbsolutePath().normalize();
        String expected = Files.readString(jsonPath);

        App app = new App();
        app.setFormat("json");
        app.setFilepath1(filePath1);
        app.setFilepath2(filePath2);
        app.call();

        String expectedNormalized = normalizeJson(expected.replaceAll("\\s+", " ").trim());
        String actualNormalized = normalizeJson(outputStream.toString().replaceAll("\\s+", " ").trim());

        assertEquals(expectedNormalized, actualNormalized);
    }

    public String normalizeJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() { });
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
    }
}
