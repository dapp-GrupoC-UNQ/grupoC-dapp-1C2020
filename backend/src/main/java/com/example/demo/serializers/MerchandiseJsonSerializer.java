package com.example.demo.serializers;

import com.example.demo.model.Comercio;
import com.example.demo.model.merchandise.Merchandise;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class MerchandiseJsonSerializer extends JsonSerializer<Merchandise> {

    @Override
    public void serialize(Merchandise merchandise, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", merchandise.name());
        jsonGenerator.writeStringField("brand", merchandise.brand());
        jsonGenerator.writeNumberField("price", merchandise.price());
        jsonGenerator.writeNumberField("stock", merchandise.stock());
        jsonGenerator.writeObjectField("discountToApply", merchandise.discountToApply());
        jsonGenerator.writeEndObject();
    }
}
