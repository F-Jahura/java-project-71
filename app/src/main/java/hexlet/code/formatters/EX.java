package hexlet.code.formatters;

public class EX {
    /*public class Formatter {
        public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
            // Чтение файлов
            String type = FilenameUtils.getExtension(filePath1 + filePath2);

            // Парсинг данных
            Map<String, Object> data1 = Parser.parsing(filePath1, type);
            Map<String, Object> data2 = Parser.parsing(filePath2, type);

            // Построение разницы
            TreeMap<String, Map<String, Object>> diff = Differ.getDiff(data1, data2);

            // Форматирование разницы
            FormatStyle formatStyle = selectFormatStyle(formatName);
            return formatStyle.format(diff);
        }

        public static String generate(String filePath1, String filePath2) throws Exception {
            return generate(filePath1, filePath2, "stylish");
        }

        private static FormatStyle selectFormatStyle(String formatName) {
            switch (formatName.toLowerCase()) {
                case "plain":
                    return new Plain();
                case "stylish":
                    return new Stylish();
                case "json":
                    return new Json();
                default:
                    throw new UnsupportedOperationException("Unsupported format: " + formatName);
            }
        }
    }

    // Обновленные интерфейс и классы
    public interface FormatStyle {
        String format(TreeMap<String, Map<String, Object>> diff) throws Exception;
    }

    public final class Plain implements FormatStyle {
        private static String formatValue(Object value) {
            if (value instanceof Map || value instanceof List) {
                return "[complex value]";
            }
            if (value instanceof String) {
                return String.format("'%s'", value);
            }
            return String.valueOf(value);
        }

        @Override
        public String format(TreeMap<String, Map<String, Object>> diff) {
            StringBuilder builder = new StringBuilder();

            diff.forEach((key, details) -> {
                String status = (String) details.get("status");
                switch (status) {
                    case "removed" -> builder.append(String.format("Property '%s' was removed\n", key));
                    case "added" -> builder.append(String.format("Property '%s' was added with value: %s\n", key,
                            formatValue(details.get("value"))));
                    case "updated" -> builder.append(String.format("Property '%s' was updated. From %s to %s\n",
                            key, formatValue(details.get("oldValue")), formatValue(details.get("newValue"))));
                    default -> {}
                }
            });

            return builder.toString().trim();
        }
    }*/
}
