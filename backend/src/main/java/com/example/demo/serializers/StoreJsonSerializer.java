package com.example.demo.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import model.Comercio;

import java.io.IOException;

public class StoreJsonSerializer extends JsonSerializer<Comercio> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(
            Comercio comercio, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeStringField("storeName", comercio.nombre());
        jgen.writeStringField("storeAdress", comercio.domicilio());
        jgen.writeFieldName("storePaymentMethods");
        jgen.writeStartArray();
        comercio.mediosDePagoComercio().stream().forEach(paymentMethod -> {
            try {
                jgen.writeString(paymentMethod);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jgen.writeEndArray();
        jgen.writeEndObject();
    }
}
