package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args); // NOSONAR
        System.exit(exitCode); // NOSONAR
    }

    @Option(names = { "-f", "--format" }, paramLabel = "format",
            description = "Output format (default: ${DEFAULT-VALUE})", defaultValue = "stylish")
    private String format;

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;


    @Override
    @SuppressWarnings("squid:S1199")
    public Integer call() throws Exception {
        String effectiveFormat = format == null || format.isEmpty() ? "stylish" : format.trim();

        var diff = Differ.generate(filepath1, filepath2, effectiveFormat);

        System.out.println(diff);

        return 0;
    }
}
