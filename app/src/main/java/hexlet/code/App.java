package hexlet.code;

import org.apache.commons.io.FilenameUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    public static void main(String[] args) throws Exception {
        //System.out.printf("Hello World!\n");
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Option(names = { "-f", "--format" }, paramLabel = "format",
            description = "Output format (default: ${DEFAULT-VALUE})", defaultValue = "stylish")
    String format;

    /*@Option(names = { "-f", "--format" }, paramLabel = "format",
            description = "Output format (default: ${DEFAULT-VALUE})", defaultValue = "plain")
    String formatPlain;*/

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    File filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    File filepath2;

    @Override
    public Integer call() throws Exception {
        String effectiveFormat = format == null || format.isEmpty() ? "stylish" : format.trim();

        Formatter formatter;
        if ("stylish".equalsIgnoreCase(effectiveFormat)) {
            formatter = new Stylish();
        } else {
            throw new UnsupportedOperationException("Unsupported format: " + effectiveFormat);
        }

        String type = FilenameUtils.getExtension(filepath1.toString() + filepath2.toString());

        Map<String, Object> data1 = Parser.parsing(filepath1, type);
        Map<String, Object> data2 = Parser.parsing(filepath2, type);

        System.out.println(formatter.format(Differences.differ(data1, data2)));

        //PlainFormat.format(Differences.differ(data1, data2));
        return 0;
    }
}
