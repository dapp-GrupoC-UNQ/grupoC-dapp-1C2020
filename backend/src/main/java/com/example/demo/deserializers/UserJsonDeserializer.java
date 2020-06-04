package com.example.demo.deserializers;

import com.example.demo.model.user.ClientUser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UserJsonDeserializer extends JsonDeserializer<ClientUser> {
    @Override
    public ClientUser deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonParser);

        String username = jsonNode.get("username").textValue();
        String password = jsonNode.get("password").textValue();

        return new ClientUser(username, password);
    }
}
