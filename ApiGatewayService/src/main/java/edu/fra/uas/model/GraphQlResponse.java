package edu.fra.uas.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.Map;

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

