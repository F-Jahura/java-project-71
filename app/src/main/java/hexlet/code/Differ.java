package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.FormatStyle;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.TreeMap;

public final class Differ {
    private Differ() {
    }
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        String type1 = FilenameUtils.getExtension(filePath1);
        String type2 = FilenameUtils.getExtension(filePath2);

        String content1 = new String(Files.readAllBytes(new File(filePath1).toPath()));
        String content2 = new String(Files.readAllBytes(new File(filePath2).toPath()));

        Map<String, Object> data1 = Parser.parsing(content1, type1);
        Map<String, Object> data2 = Parser.parsing(content2, type2);

        TreeMap<String, Map<String, Object>> dif = FindDiffer.getDiff(data1, data2);

        FormatStyle formatStyle = Formatter.selectFormatStyle(formatName);

        return formatStyle.format(dif);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}

