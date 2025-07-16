package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.FormatStyle;
import org.apache.commons.io.FilenameUtils;

import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        String type = FilenameUtils.getExtension(filePath1 + filePath2);

        Map<String, Object> data1 = Parser.parsing(filePath1, type);
        Map<String, Object> data2 = Parser.parsing(filePath2, type);

        TreeMap<String, Map<String, Object>> dif = FindDiffer.getDiff(data1, data2);

        FormatStyle formatStyle = Formatter.selectFormatStyle(formatName);

        return formatStyle.format(dif);
    }

    public static String generate(String filePath1, String  filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
