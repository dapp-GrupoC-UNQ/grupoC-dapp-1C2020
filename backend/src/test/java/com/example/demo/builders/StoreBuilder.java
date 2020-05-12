package com.example.demo.builders;

import com.example.demo.model.Store;
import com.example.demo.model.StoreSchedule;
import com.example.demo.model.merchandise.MerchandiseCategory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StoreBuilder {
    public static StoreBuilder aStore() {
        return new StoreBuilder();
    }

    public static List<Store> storeList() {
        Store store = aStore().build();
        Store anotherStore = aStore().withName("Coto").withCategory("Almacen").build();
        return Arrays.asList(store, anotherStore);
    }
    private String storeName = "Jumbo";
    private String storeCategory = "Supermercado";
    private String storeAddress = "Calchaqui 123";
    private Integer maxShippingDistanceInKm = 3;
    private List<String> availablePaymentMethods = Arrays.asList("Efectivo");
    private List<StoreSchedule> storeSchedule = Arrays.asList(new StoreSchedule(DayOfWeek.FRIDAY, LocalTime.of(9,0), LocalTime.of(15, 0)));
    private LocalDate openingDate = LocalDate.now();

    public static List<Store> storeWithACategoryList(String category) {
        return storeList().stream().filter(store -> store.storeCategory().equals(category)).collect(Collectors.toList());
    }

    public Store build() {
        return new Store(storeName, storeCategory, storeAddress,
                maxShippingDistanceInKm, availablePaymentMethods, storeSchedule, openingDate);
    }

    public StoreBuilder withName(String aName) {
        storeName = aName;
        return this;
    }

    public StoreBuilder withAddress(String anAddress) {
        storeAddress = anAddress;
        return this;
    }

    public StoreBuilder withShippingDistance(Integer kms) {
        maxShippingDistanceInKm = kms;
        return this;
    }

    public StoreBuilder withCategory(String aCategory) {
        storeCategory = aCategory;
        return this;
    }

    public StoreBuilder withPaymentMethods(List<String> paymentMethods){
        availablePaymentMethods = paymentMethods;
        return this;
    }

    public StoreBuilder withSchedule(List<StoreSchedule> storeSchedule) {
        this.storeSchedule = storeSchedule;
        return this;
    }

    public static Store withMerchandise(String productName, String productBrand, Double price, Integer stock, MerchandiseCategory aCategory) {
        Store store = aStore().build();
        store.addMerchandise(productName, productBrand, price, stock, aCategory);
        return store;
    }

    public StoreBuilder withOpeningDate(LocalDate anOpeningDate) {
        openingDate = anOpeningDate;
        return this;
    }
}
