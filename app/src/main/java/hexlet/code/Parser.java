package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public final class Parser {
    private Parser() {
    }

    /*public static Map<String, Object> parsing(String content, String type) throws ParserException {
        ObjectMapper objectMapper;
        try {
            switch (type) {
                case "json":
                    objectMapper = new ObjectMapper();
                    break;
                case "yaml":
                case "yml":
                    objectMapper = new ObjectMapper(new YAMLFactory());
                    break;
                default:
                    throw new ParserException("Unsupported input format: " + type);
            }

            return objectMapper.readValue(content, new TypeReference<Map<String, Object>>() { });
        } catch (IOException e) {
            throw new ParserException("Error parsing content", e);
        }
    }*/
    public static Map<String, Object> parsing(String content, String type) throws Exception {
        ObjectMapper objectMapper = switch (type) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new UnsupportedOperationException("Unsupported input format: " + type);
        };
        return objectMapper.readValue(content, new TypeReference<Map<String, Object>>() {
        });
    }
}
