package hexlet.code;

//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
//import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.Type;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parsing(File content, String type) throws Exception {
        ObjectMapper objectMapper = switch (type) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new UnsupportedOperationException("Unsupported input format: " + type);
        };
        return objectMapper.readValue(content, new TypeReference<Map<String, Object>>() {
        });
    }


    /*public static Map<String, Object> asYaml(File jsonString) throws JsonProcessingException, IOException {
        // parse JSON
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeTree = objectMapper.readTree(jsonString);
        // save it as YAML
        YAMLMapper yamlMapper = new YAMLMapper();
        String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);

        return objectMapper.readValue(jsonAsYaml, new TypeReference<Map<String, Object>>() {
        });
    }*/
}
