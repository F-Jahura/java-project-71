package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
//import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*public class AppTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        // Перехват стандартного вывода
        System.setOut(new PrintStream(outputStream));
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
    }

    @Test
    public void testJsonComparing() throws Exception {
        var path1 = getFixturePath("file1.json");
        var path2 = getFixturePath("file2.json");

        var expected = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}\n";

        App app = new App();
        app.filepath1 = new File(path1.toString());
        app.filepath2 = new File(path2.toString());
        app.call();

        System.out.println(app.call());

        //assertEquals(expected, outputStream.toString());
    }
}*/

public class AppTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testDiff() throws Exception {
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
}
