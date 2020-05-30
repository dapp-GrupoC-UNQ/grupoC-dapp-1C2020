package com.example.demo.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.example.demo.model.ClientUser;

import java.io.IOException;

public class UserJsonSerializer extends JsonSerializer<ClientUser> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(
            ClientUser clientUser, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeStringField("username", clientUser.username());
        jgen.writeStringField("password", clientUser.password());
        jgen.writeEndObject();
    }
}
