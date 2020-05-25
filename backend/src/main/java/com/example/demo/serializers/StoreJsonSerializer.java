package com.example.demo.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.example.demo.model.store.Store;

import java.io.IOException;

public class StoreJsonSerializer extends JsonSerializer<Store> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(
            Store store, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeStringField("storeName", store.name());
        jgen.writeStringField("storeAddress", store.address());
        jgen.writeNumberField("deliveryDistanceInKm", store.deliveryDistanceInKm());
        serializeStoreCategories(jgen, store);
        serializeStorePaymentMethods(jgen, store);
        jgen.writeEndObject();
    }

    private void serializeStoreCategories(JsonGenerator jgen, Store store) throws IOException {
        jgen.writeFieldName("storeCategories");
        jgen.writeStartArray();
        store.storeCategories().stream().forEach(category -> {
            try {
                jgen.writeString(category.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jgen.writeEndArray();
    }

    private void serializeStorePaymentMethods(JsonGenerator jgen, Store store) throws IOException {
        jgen.writeFieldName("storePaymentMethods");
        jgen.writeStartArray();
        store.availablePaymentMethods().stream().forEach(paymentMethod -> {
            try {
                jgen.writeString(paymentMethod);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jgen.writeEndArray();
    }
}
