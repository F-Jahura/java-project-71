package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import hexlet.code.exception.ParserException;

import java.io.IOException;
import java.util.Map;

public final class Parser {
    private Parser() {
    }

    @SuppressWarnings("squid:S125")
    public static Map<String, Object> parsing(String content, String type) throws ParserException {
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
    }
}
