package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
//import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AppTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
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
}
