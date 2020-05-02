package com.example.demo.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import model.User;

import java.io.IOException;

public class UserJsonSerializer extends JsonSerializer<User> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(
            User user, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeStringField("username", user.username());
        jgen.writeStringField("password", user.password());
        jgen.writeEndObject();
    }
}
