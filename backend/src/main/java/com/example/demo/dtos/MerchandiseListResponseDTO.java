package com.example.demo.dtos;

import com.example.demo.model.merchandise.Merchandise;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MerchandiseListResponseDTO {

    @JsonProperty
    private Long storeId;
    @JsonProperty
    private List<Merchandise> merchandises;

    public MerchandiseListResponseDTO(@JsonProperty("merchandises")List<Merchandise> merchandises, @JsonProperty("storeId") Long storeId) {
        this.merchandises = merchandises;
        this.storeId = storeId;
    }

    public MerchandiseListResponseDTO(){};

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<Merchandise> getMerchandises() {
        return merchandises;
    }

    public void setMerchandises(List<Merchandise> merchandises) {
        this.merchandises = merchandises;
    }
}
