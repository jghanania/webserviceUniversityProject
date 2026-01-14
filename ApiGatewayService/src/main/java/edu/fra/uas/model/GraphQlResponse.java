package edu.fra.uas.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.Map;

/**
 * Represents a generic response structure for GraphQL queries and mutations.
 * This class is used to dynamically map GraphQL response data into a Java
 * object.
 */
public class GraphQlResponse {
    private Map<String, Object> data;

    @JsonAnySetter
    public void setData(String key, Object value) {
        this.data = Map.of(key, value);
    }

    public Map<String, Object> getData() {
        return data;
    }
}
