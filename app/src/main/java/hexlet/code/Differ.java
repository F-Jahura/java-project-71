package hexlet.code;

import hexlet.code.formatters.FormatStyle;
import hexlet.code.formatters.JsonStylish;
import hexlet.code.formatters.PlainSylish;
import hexlet.code.formatters.Stylish;
import org.apache.commons.io.FilenameUtils;

import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        String type = FilenameUtils.getExtension(filePath1 + filePath2);

        Map<String, Object> data1 = Parser.parsing(filePath1, type);
        Map<String, Object> data2 = Parser.parsing(filePath2, type);

        FormatStyle formatStyle;
        if ("plain".equalsIgnoreCase(formatName)) {
            formatStyle = new PlainSylish();
        } else if ("stylish".equalsIgnoreCase(formatName)) {
            formatStyle = new Stylish();
        } else if ("json".equalsIgnoreCase(formatName)) {
            formatStyle = new JsonStylish();
        } else {
            throw new UnsupportedOperationException("Unsupported format: " + formatName);
        }

        return formatStyle.format(data1, data2);
    }

    public static String generate(String filePath1, String  filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
