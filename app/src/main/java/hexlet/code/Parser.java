package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parsing(String filepath, String type) throws Exception {
        ObjectMapper objectMapper = switch (type) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new UnsupportedOperationException("Unsupported input format: " + type);
        };

        String content = new String(Files.readAllBytes(new File(filepath).toPath()));
        return objectMapper.readValue(content, new TypeReference<Map<String, Object>>() {
        });
    }
}
