package hexlet.code;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

public class App {

    public static void main(String[] args) {
        System.out.printf("Hello World!\n");

        int exitCode = new CommandLine(new CheckSum()).execute(args);
        System.exit(exitCode);
    }
}

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
class CheckSum implements Runnable {
    @Override
    public void run() {

    }
}