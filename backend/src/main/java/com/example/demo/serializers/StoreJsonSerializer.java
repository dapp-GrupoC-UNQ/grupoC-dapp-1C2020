package com.example.demo.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import model.Comercio;

import java.io.IOException;

public class StoreJsonSerializer extends JsonSerializer<Comercio> {

    @Override
    public void serialize(
            Comercio comercio, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeStringField("nombreComercio", comercio.nombre());
        jgen.writeStringField("domicilio", comercio.domicilio());
        jgen.writeEndObject();
    }
}
