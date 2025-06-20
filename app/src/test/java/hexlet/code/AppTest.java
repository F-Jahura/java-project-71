package hexlet.code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;

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

    @Test
    public void testJson() throws Exception {
        var filePath1 = Paths.get("src", "test", "resources", "file1.json");
        var filePath2 = Paths.get("src", "test", "resources", "file2.json");

        App app = new App();
        app.filepath1 = new File(filePath1.toString());
        app.filepath2 = new File(filePath2.toString());
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
        var filePath1 = Paths.get("src", "test", "resources", "file1.yaml");
        var filePath2 = Paths.get("src", "test", "resources", "file2.yaml");

        App app = new App();
        app.filepath1 = new File(filePath1.toString());
        app.filepath2 = new File(filePath2.toString());
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
        var filePath1 = Paths.get("src", "test", "resources", "filepath1.json");
        var filePath2 = Paths.get("src", "test", "resources", "filepath2.json");

        App app = new App();
        app.filepath1 = new File(filePath1.toString());
        app.filepath2 = new File(filePath2.toString());
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
        var filePath1 = Paths.get("src", "test", "resources", "filepath1.yaml");
        var filePath2 = Paths.get("src", "test", "resources", "filepath2.yaml");

        App app = new App();
        app.filepath1 = new File(filePath1.toString());
        app.filepath2 = new File(filePath2.toString());
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
}
