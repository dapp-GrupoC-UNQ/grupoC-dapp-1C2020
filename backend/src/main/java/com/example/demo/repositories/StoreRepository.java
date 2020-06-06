package com.example.demo.repositories;

import com.example.demo.model.*;
import com.example.demo.model.discounts.Discount;
import com.example.demo.model.discounts.MerchandiseDiscount;
import com.example.demo.model.discounts.NoDiscount;
import com.example.demo.model.exceptions.NotFoundStoreException;
import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.model.merchandise.MerchandiseCategory;
import com.example.demo.model.store.Store;
import com.example.demo.model.store.StoreCategory;
import com.example.demo.model.validator.EntityValidator;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository

public class StoreRepository implements IStoreRepository {

    private List<Store> stores = new ArrayList<>();
    private EntityValidator entityVAlidator = new EntityValidator();

    @Override
    public List<Store> getStores() {
        Discount noDiscount = new NoDiscount();
        List<DayOfWeek> openingDays = Arrays.asList(DayOfWeek.FRIDAY);
        List<StoreCategory> categories = Arrays.asList(StoreCategory.GROCERY);
        List<StoreCategory> categories2 = Arrays.asList(StoreCategory.CLEANING_SUPPLIES);
        StoreSchedule rangoHorario = new StoreSchedule(openingDays, LocalTime.of(9, 0), LocalTime.of(15, 0));
        Store store1 = new Store("Lo de tito", categories2, "Alsina 123", 4, Arrays.asList("Efectivo"), rangoHorario, LocalDate.now());
        Store store2 = new Store("Coto", categories, "Alsina 123", 4, Arrays.asList("Efectivo"), rangoHorario, LocalDate.now());
        Store store3 = new Store("Jumbo", categories, "Alsina 123", 4, Arrays.asList("Efectivo"), rangoHorario, LocalDate.now());
        store1.addMerchandise("Fideos", "Marolio", 24.3, 45, MerchandiseCategory.GROCERY, "data:image/jpeg;base64,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");
        store2.addMerchandise("Nesquick", "Nestle", 30.3, 24, MerchandiseCategory.GROCERY, "https://static.openfoodfacts.org/images/products/303/371/006/5967/front_fr.266.full.jpg");
        store2.addMerchandise("Sobre Jugo Naranja", "Tang", 10.3, 24, MerchandiseCategory.GROCERY, "https://2.bp.blogspot.com/_cSh0lw6GMB0/Sa6OslszGCI/AAAAAAAAAO4/WImrA9wgpmw/s280/tang-tapa.JPG");
        store2.addMerchandise("Mayonesa", "Hellmans", 20.3, 24, MerchandiseCategory.DAIRY_PRODUCTS, "https://supermercado.carrefour.com.ar/media/catalog/product/cache/1/image/1000x/040ec09b1e35df139433887a97daa66f/7/7/7794000960077_02_7.jpg");
        store2.addMerchandise("Carne picada x 1kg", "Coto", 100.0, 24, MerchandiseCategory.DAIRY_PRODUCTS, "https://lh3.googleusercontent.com/proxy/mx5HrLBEMcZnqsW1HpjW0Oc-hR5MqdrmiM5-rUxOHNKUTE4K7jVTJDZJXwV9P0jilljE8rtBf3eHYUl3uiWp_yPnK4oC64Iam8keSIUhdVH7_iol1KaK1YQ");

        stores.add(store1);
        stores.add(store2);
        stores.add(store3);
        return this.stores;
    }

    @Override
    public List<Store> getStoresWithACategory(StoreCategory category) {
        return this.getStores().stream().filter(store -> store.hasACategory(category)).collect(Collectors.toList());
    }

    @Override
    public List<Merchandise> getProductsFrom(Store store) {
        return store.listOfAvailableMerchandise();
    }

    @Override
    public Store getStore(String storeName) {
        return this.getStores().stream()
                .filter(comercio -> comercio.name().equals(storeName))
                .findFirst()
                .orElseThrow(NotFoundStoreException::new);
    }

    @Override
    public List<Merchandise> getDiscountFromAllStores() {
        Merchandise merchandise = new Merchandise("Nesquick", "Nestle", 30.3, 24, MerchandiseCategory.GROCERY, "https://mercanet.com.ar/server/Portal_0019782/img/products/fideo-matarazzo-mono-500-grs_5371692_xxl.jpg");
        Discount discount = new MerchandiseDiscount(merchandise, 20, LocalDate.of(2020, 5, 5), LocalDate.of(2020, 5, 10));
        return Arrays.asList(merchandise);
        //TODO: VER QUE EL DESCUENTO SE AGREGA DESDE LA TIENDA.
    }

    @Override
    public Store addStore(Store store) {
        validateStore(store);
        this.stores.add(store);
        return store;
    }

    private void validateStore(Store aStore) {
        entityVAlidator.validateStore(aStore);
    }
}
