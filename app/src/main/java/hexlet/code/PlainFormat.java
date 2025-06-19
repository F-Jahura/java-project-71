package hexlet.code;
/*
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
 */

public class PlainFormat {
    /*public static String format(TreeMap<String, String> commonData) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : commonData.entrySet()) {
            String propertyName = entry.getKey();
            Object difference = entry.getValue();

            String output;
            if (difference instanceof PropertyUpdate) {
                PropertyUpdate update = (PropertyUpdate) difference;
                output = String.format("Property '%s' was updated. From %s to %s",
                        propertyName, formatValue(update.getOldValue()), formatValue(update.getNewValue()));
            } else if (difference instanceof PropertyAddition) {
                PropertyAddition addition = (PropertyAddition) difference;
                output = String.format("Property '%s' was added with value: %s",
                        propertyName, formatValue(addition.getNewValue()));
            } else if (difference instanceof PropertyRemoval) {
                output = String.format("Property '%s' was removed", propertyName);
            } else {
                continue;
            }

            result.append(output).append("\n");
        }
        return result.toString().trim();
    }

    private static String formatValue(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return String.format("'%s'", value);
        }
        return String.valueOf(value);
    }*/
}
