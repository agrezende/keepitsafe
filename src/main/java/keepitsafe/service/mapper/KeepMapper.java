package keepitsafe.service.mapper;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import keepitsafe.model.Keep;
import keepitsafe.model.Secret;

/**
 * Utility to convert keeps and jsons
 */
public class KeepMapper {
    private final static ObjectMapper mapper = new ObjectMapper();
    
    /**
     * Build a JSON with the following structure
     * 
     * {
     *     "id": ...,
     *     "name": ...,
     *     "description": ...
     * }
     * 
     */
    public static ObjectNode toJson(Keep keep) {
        ObjectNode keepJ = mapper.createObjectNode();
        keepJ.put("id", keep.getId());
        keepJ.put("name", keep.getName());
        keepJ.put("description", keep.getDescription());
        return keepJ;
    }
    
    /**
     * Build a JSON Array with the structure of {@link KeepMapper#toJson(Keep)}
     */
    public static ArrayNode toJson(Collection<Keep> keeps) {
        ArrayNode keepsJ = mapper.createArrayNode();
        keeps.forEach(keep -> keepsJ.add(toJson(keep)));
        return keepsJ;
    }

    /**
     * Build a JSON Array with the structure of {@link KeepMapper#toJson(Keep)}
     * concatenated with an array of {@link SecretMapper#toJson(Collection)}
     */
    public static ObjectNode toJsonWithSecrets(Keep keep) {
        Collection<Secret> secrets = keep.getSecrets();
        ObjectNode keepJ = toJson(keep);
        ArrayNode secretsJ = SecretMapper.toJson(secrets);
        keepJ.withArray("secrets").addAll(secretsJ);
        return keepJ;
    }
}
