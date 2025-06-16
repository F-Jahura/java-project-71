package hexlet.code;

import org.apache.commons.io.FilenameUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    public App() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        //System.out.printf("Hello World!\n");

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

        new CommandLine(new App()).parseArgs(args);


    }

    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: stylish]")
    String format;

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    File filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    File filepath2;

    @Override
    public Integer call() throws Exception {
        String type = FilenameUtils.getExtension(filepath1.toString() + filepath2.toString());

        Map<String, Object> data1 = Parser.parsing(filepath1, type);
        Map<String, Object> data2 = Parser.parsing(filepath2, type);


        TreeMap<String, String> commonData = new TreeMap<>();

        for (Map.Entry<String, Object> entry : data1.entrySet()) {
            if (data2.containsKey(entry.getKey())) {
                if (!entry.getValue().equals(data2.get(entry.getKey()))) {
                    commonData.put(entry.getKey(), String.format("- %s: %s\n+ %s: %s",
                            entry.getKey(), entry.getValue(), entry.getKey(), data2.get(entry.getKey())));
                } else {
                    commonData.put(entry.getKey(), String.format("  %s: %s", entry.getKey(), entry.getValue()));
                }
            } else {
                commonData.put(entry.getKey(), String.format("- %s: %s", entry.getKey(), entry.getValue()));
            }
        }

        for (Map.Entry<String, Object> entry : data2.entrySet()) {
            if (!data1.containsKey(entry.getKey())) {
                commonData.put(entry.getKey(), String.format("+ %s: %s", entry.getKey(), entry.getValue()));
            }
        }

        System.out.println("{");
        for (String value : commonData.values()) {
            System.out.println(value);
        }
        System.out.println("}");

        return 0;
    }
}
