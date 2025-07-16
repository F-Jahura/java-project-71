package hexlet.code;

import hexlet.code.formatters.FormatStyle;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {
    public static FormatStyle selectFormatStyle(String formatName) {
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
