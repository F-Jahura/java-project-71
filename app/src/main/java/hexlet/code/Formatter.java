package hexlet.code;

import hexlet.code.formatters.FormatStyle;
import hexlet.code.formatters.PlainSylish;
import hexlet.code.formatters.Stylish;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.Map;

public class Formatter {
    public static String generate(File filePath1, File filePath2, String formatName) throws Exception {
        String type = FilenameUtils.getExtension(filePath1.toString() + filePath2.toString());

        Map<String, Object> data1 = Parser.parsing(filePath1, type);
        Map<String, Object> data2 = Parser.parsing(filePath2, type);

        FormatStyle formatStyle;
        if ("plain".equalsIgnoreCase(formatName)) {
            formatStyle = new PlainSylish();
        } else if ("stylish".equalsIgnoreCase(formatName)) {
            formatStyle = new Stylish();
        } else {
            throw new UnsupportedOperationException("Unsupported format: " + formatName);
        }

        return formatStyle.format(data1, data2);
    }
}
