package com.example.demo.serializers;

import com.example.demo.model.Discount;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class DiscountJsonSerializer extends JsonSerializer<Discount> {


    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(Discount discount, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeStringField("productName", discount.productName());
        jgen.writeStringField("productBrand", discount.productBrand());
        jgen.writeNumberField("productPrice", discount.price());
        jgen.writeNumberField("percentOfDiscount", discount.percentOfDiscount());
        jgen.writeEndObject();
    }
}
