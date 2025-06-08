package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.Map;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    public static void main(String[] args) throws Exception{
        System.out.printf("Hello World!\n");

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
        ObjectMapper jasonMapper = new ObjectMapper();
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

        Map<String, Object> data1 = jasonMapper.readValue(filepath1, Map.class);
        Map<String, Object> data2 = jasonMapper.readValue(filepath2, Map.class);

        // Преобразование в YAML
        String yaml1 = yamlMapper.writeValueAsString(data1);
        String yaml2 = yamlMapper.writeValueAsString(data2);

        System.out.println(yaml1);
        System.out.println(yaml2);

        return 0;
    }
}

