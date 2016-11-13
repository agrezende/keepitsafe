package keepitsafe.service.mapper;

import java.util.Collection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import keepitsafe.model.Keep;
import keepitsafe.model.Secret;

/**
 * Utility to convert secrets and jsons
 */
public class SecretMapper {
    private final static ObjectMapper mapper = new ObjectMapper();

    /**
     * Build a JSON with the following structure
     * 
     * {
     *     "id": ...,
     *     "name": ...,
     *     "description": ...,
     *     "login": ...
     * }
     * 
     */
    public static ObjectNode toJson(Secret secret) {
        ObjectNode secretJ = mapper.createObjectNode();
        secretJ.put("id", secret.getId());
        secretJ.put("name", secret.getName());
        secretJ.put("description", secret.getDescription());
        secretJ.put("login", secret.getLogin());
        return secretJ;
    }
    
    /**
     * Build a JSON Array with the structure of {@link KeepMapper#toJson(Keep)}
     */
    public static ArrayNode toJson(Collection<Secret> secrets) {
        ArrayNode secretsJ = mapper.createArrayNode();
        secrets.forEach(secret -> secretsJ.add(toJson(secret)));
        return secretsJ;
    }

}
