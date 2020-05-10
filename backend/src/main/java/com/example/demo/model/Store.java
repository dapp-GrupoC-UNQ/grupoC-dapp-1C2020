package com.example.demo.model;

import com.example.demo.model.discounts.Discount;
import com.example.demo.model.discounts.MerchandiseDiscount;
import com.example.demo.model.excepciones.InsufficientMerchandiseStockException;
import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.model.merchandise.MerchandiseCategory;
import com.example.demo.serializers.StoreJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.demo.model.excepciones.NotFoundProductInStore;
import com.example.demo.model.excepciones.RepeatedMerchandiseInStore;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonSerialize(using = StoreJsonSerializer.class)
public class Store {

    String storeName;
    String storeCategory; // CAMBIAR A LISTA DE ENUM
    String storeAddress;
    Integer deliveryDistanceInKm;
    List<String> availablePaymentMethods;
    List<RangoHorarioComercio> storeTimeSchedule;
    List<Discount> discountList = new ArrayList<>();
    List<Merchandise> merchandiseList = new ArrayList<>();

    public Store(String name, String category, String address, Integer distanceInKm, List<String> paymentMethods, List<RangoHorarioComercio> timeSchedule) {
         storeName = name;
         storeCategory = category;
         storeAddress = address;
         deliveryDistanceInKm = distanceInKm;
         availablePaymentMethods =  paymentMethods;
         storeTimeSchedule = timeSchedule;
    }

    public String name() {
        return this.storeName;
    }

    public String address() {
        return this.storeAddress;
    }

    public Integer deliveryDistanceInKm() {
        return this.deliveryDistanceInKm;
    }

    public String storeCategory() {
        return this.storeCategory;
    }

    public List<String> availablePaymentMethods() { return this.availablePaymentMethods; }

    public Integer amountOfAvailablePaymentMethods() { return availablePaymentMethods.size();   }

    public Boolean isOpenAt(DayOfWeek dia, LocalTime hora) {
        return   storeTimeSchedule.stream().anyMatch(horario -> horario.estaDisponibleEnHorario(dia, hora));
    }

    public Boolean sellsProduct(String nombreProducto, String marcaProducto) {
      return merchandiseList.stream().anyMatch(mercaderia -> this.equalMerchandise(mercaderia, nombreProducto, marcaProducto));
    }

    private Boolean equalMerchandise(Merchandise mercaderia, String unNombreProducto, String unaMarcaProducto) {
        return mercaderia.name().equals(unNombreProducto) && mercaderia.brand().equals(unaMarcaProducto);
    }

    private Merchandise findMerchandise(String nombreProducto, String marcaProducto) {
        return merchandiseList.stream()
                .filter(mercaderia -> this.equalMerchandise(mercaderia, nombreProducto, marcaProducto))
                .findFirst()
                .orElseThrow(NotFoundProductInStore::new);
    }

    public Boolean canPayWith(String medioDePago) {
        return availablePaymentMethods.contains(medioDePago);
    }

    public Boolean hasMerchandises() {
        return !merchandiseList.isEmpty();
    }

    public void addMerchandise(String name, String brand, Double price, Integer stock, MerchandiseCategory aCategory) {
        if(this.sellsProduct(name, brand)) { throw new RepeatedMerchandiseInStore();}
        merchandiseList.add(new Merchandise(name, brand, price, stock, aCategory));
    }

    public Boolean sellsMerchandise(String name, String brand) {
        return merchandiseList.stream().anyMatch(merchandise -> this.equalMerchandise(merchandise, name, brand));
    }

    public Integer stockOf(String name, String brand) {
        return this.findMerchandise(name, brand).stock();
    }

    public Double priceOf(String name, String brand) {
        return this.findMerchandise(name, brand).price();
    }

    public void updatePriceFor(String name, String brand, Double newPrice) {
        this.findMerchandise(name, brand).updatePrice(newPrice);
    }

    public void addStock(String name, String brand, Integer newStock) {
        this.findMerchandise(name, brand).addStock(newStock);
    }

    public void decreaseStock(String name, String brand, Integer stockToDecrese) {
        this.findMerchandise(name, brand).decreaseStock(stockToDecrese);
    }

    public List<Merchandise> listOfAvailableMerchandise() {
        return this.merchandiseList.stream().filter(merchandise -> merchandise.stock() > 0).collect(Collectors.toList());
    }

    public void addDiscount(MerchandiseDiscount merchandiseDiscount) {
        this.discountList.add(merchandiseDiscount);
    }

    public Boolean hasADiscount(MerchandiseDiscount merchandiseDiscount) {
        return this.discountList.contains(merchandiseDiscount);
    }

   /* public List<MerchandiseDiscount> listOfAvailableDiscount() {
        return this.discountList;
    }
*/
    public AdquiredProduct getProduct(String productName, String productBrand, Integer quantity) {
        Merchandise merchandise = this.findMerchandise(productName, productBrand);
        if (this.stockOf(productName, productBrand) < quantity){
            throw new InsufficientMerchandiseStockException();
        }
        this.decreaseStock(productName, productBrand, quantity);
        return new AdquiredProduct(merchandise.name(), merchandise.brand(), merchandise.price(), quantity);
    }

    public void applyDiscountOn(Merchandise product, Discount discount) {
        product.setADiscount(discount);
    }

    public Merchandise getMerchandise(String productName, String productBrand) {
        return this.findMerchandise(productName, productBrand);
    }

 /*   public void addDiscountFor(String productName, String brand, Integer percentageToDiscount, LocalDate startDate, LocalDate endDate) {
        this.findMerchandise(productName, brand).setADiscount(new MerchandiseDiscount(percentageToDiscount, startDate, endDate));
    }*/
}
