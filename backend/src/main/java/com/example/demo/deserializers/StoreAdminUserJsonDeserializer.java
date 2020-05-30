package com.example.demo.deserializers;

import com.example.demo.model.StoreSchedule;
import com.example.demo.model.store.Store;
import com.example.demo.model.user.StoreAdminUser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class StoreAdminUserJsonDeserializer extends JsonDeserializer<StoreAdminUser> {
    @Override
    public StoreAdminUser deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonParser);

        String username = jsonNode.get("username").textValue();
        String password = jsonNode.get("password").textValue();
        Store store = objectMapper.treeToValue(jsonNode.get("store"), Store.class);

        return new StoreAdminUser(username, password, store);
    }
}
