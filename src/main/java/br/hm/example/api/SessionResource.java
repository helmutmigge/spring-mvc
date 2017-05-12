package br.hm.example.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Created by helmut.guimaraes on 11/05/2017.
 */
public class SessionResource {
    private Map<String, Object> attributes;
    private String id;

    public SessionResource(){}

    public SessionResource(String id, Map<String, Object> attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public String getId() {
        return id;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return this.getId();
        }
    }
}
