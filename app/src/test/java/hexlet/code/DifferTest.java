package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
final class DifferTest {
    private final String pathToStylish = "src/test/resources/stylish";
    private final String pathToPlain = "src/test/resources/plain";
    private final String pathToJson = "src/test/resources/json";
    private final String pathToJsonFile1 = "src/test/resources/filepath1.json";
    private final String pathToJsonFile2 = "src/test/resources/filepath2.json";
    private final String pathToYamlFile1 = "src/test/resources/filepath1.yaml";
    private final String pathToYamlFile2 = "src/test/resources/filepath2.yaml";

    @Test
    void testDefaultFormatJson() throws Exception {
        var defaultPath = Paths.get(pathToStylish).toAbsolutePath().normalize();

        var expected = Files.readString(defaultPath);
        var expectedNormalized = expected.replaceAll("\\s+", " ").trim();

        var actual = Differ.generate(pathToJsonFile1, pathToJsonFile2);
        String actualNormalized = actual.replaceAll("\\s+", " ").trim();

        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    void testDefaultFormatYaml() throws Exception {
        var defaultPath = Paths.get(pathToStylish).toAbsolutePath().normalize();

        var expected = Files.readString(defaultPath);
        var expectedNormalized = expected.replaceAll("\\s+", " ").trim();

        var actual = Differ.generate(pathToYamlFile1, pathToYamlFile2);
        String actualNormalized = actual.replaceAll("\\s+", " ").trim();

        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    void testJsonToStylish() throws Exception {
        var stylishPath = Paths.get(pathToStylish).toAbsolutePath().normalize();

        var expected = Files.readString(stylishPath);
        var expectedNormalized = expected.replaceAll("\\s+", " ").trim();

        var actual = Differ.generate(pathToJsonFile1, pathToJsonFile2, "stylish");
        String actualNormalized = actual.replaceAll("\\s+", " ").trim();

        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    void testYamlToStylish() throws Exception {
        var stylishPath = Paths.get(pathToStylish).toAbsolutePath().normalize();

        var expected = Files.readString(stylishPath);
        var expectedNormalized = expected.replaceAll("\\s+", " ").trim();

        var actual = Differ.generate(pathToYamlFile1, pathToYamlFile2, "stylish");
        String actualNormalized = actual.replaceAll("\\s+", " ").trim();

        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    void tesJsonToPlain() throws Exception {
        var plainPath = Paths.get(pathToPlain).toAbsolutePath().normalize();

        var expected = Files.readString(plainPath);
        var expectedNormalized = expected.replaceAll("\\s+", " ").trim();

        var actual = Differ.generate(pathToJsonFile1, pathToJsonFile2, "plain");
        String actualNormalized = actual.replaceAll("\\s+", " ").trim();

        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    void tesYamlToPlain() throws Exception {
        var plainPath = Paths.get(pathToPlain).toAbsolutePath().normalize();

        var expected = Files.readString(plainPath);
        var expectedNormalized = expected.replaceAll("\\s+", " ").trim();

        var actual = Differ.generate(pathToYamlFile1, pathToYamlFile2, "plain");
        String actualNormalized = actual.replaceAll("\\s+", " ").trim();

        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    void testJsonToJson() throws Exception {
        var jsonPath = Paths.get(pathToJson).toAbsolutePath().normalize();

        var expected = Files.readString(jsonPath);
        var expectedNormalized = expected.replaceAll("\\s+", " ").trim();

        var actual = Differ.generate(pathToJsonFile1, pathToJsonFile2, "json");
        String actualNormalized = actual.replaceAll("\\s+", " ").trim();

        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    void testYamlToJson() throws Exception {
        var jsonPath = Paths.get(pathToJson).toAbsolutePath().normalize();

        var expected = Files.readString(jsonPath);
        var expectedNormalized = expected.replaceAll("\\s+", " ").trim();

        var actual = Differ.generate(pathToYamlFile1, pathToYamlFile2, "json");
        String actualNormalized = actual.replaceAll("\\s+", " ").trim();

        assertEquals(expectedNormalized, actualNormalized);
    }
}
