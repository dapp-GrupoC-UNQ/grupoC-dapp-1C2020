package com.example.demo.model;

import com.example.demo.model.discounts.*;
import com.example.demo.model.exceptions.InsufficientMerchandiseStockException;
import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.model.merchandise.MerchandiseCategory;
import com.example.demo.serializers.StoreJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.demo.model.exceptions.NotFoundProductInStore;
import com.example.demo.model.exceptions.RepeatedMerchandiseInStore;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@JsonSerialize(using = StoreJsonSerializer.class)
public class Store {

    String storeName;
    String storeCategory; // CAMBIAR A LISTA DE ENUM
    String storeAddress;
    Integer deliveryDistanceInKm;
    LocalDateTime proximoTurnoDeLocal;
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
         proximoTurnoDeLocal = this.primerTurnoDeLocal();
    }

    private LocalDateTime primerTurnoDeLocal() {
        LocalDate now = LocalDate.now();
        List<RangoHorarioComercio> storeTime = this.storeTimeSchedule;
        List<LocalDate> diaDeApertura = storeTime.stream().map(time -> this.proximaFechaConDia(time.dia(), now)).collect(Collectors.toList());
        LocalDate diaMasCercanoANow = diaDeApertura.stream().min(Comparator.comparingLong(x -> ChronoUnit.DAYS.between(now, x))).get();
        return this.encontrarHorarioApertura(diaMasCercanoANow);
    }

    private LocalDateTime encontrarHorarioApertura(LocalDate day) {
        List<RangoHorarioComercio> storeTime = this.storeTimeSchedule;
        storeTime = storeTime.stream().filter(schedule -> schedule.diaDeAtencion.equals(day.getDayOfWeek())).collect(Collectors.toList());
        RangoHorarioComercio horario = storeTime.stream().min(Comparator.comparing(RangoHorarioComercio::horaDeApertura)).get();
        return LocalDateTime.of(day.getYear(),day.getMonth(), day.getDayOfMonth(), horario.horaDeApertura().getHour(), horario.horaDeApertura().getMinute());
    }

    private LocalDate proximaFechaConDia(DayOfWeek dayOfWeek, LocalDate today) {
        LocalDate now = today;
        return now.with(TemporalAdjusters.next(dayOfWeek));
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
        Merchandise merchandise = this.findMerchandise(name, brand);
        Discount discount = discountList.stream().filter(aDiscount -> aDiscount.canApplyDiscountFor(merchandise)).findFirst().orElse(new NoDiscount());
        return merchandise.price() - (discount.percentOfDiscount() * merchandise.price() / 100);
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

    public AdquiredProduct getProduct(String productName, String productBrand, Integer quantity) {
        Merchandise merchandise = this.findMerchandise(productName, productBrand);
        if (this.stockOf(productName, productBrand) < quantity){
            throw new InsufficientMerchandiseStockException();
        }
        this.decreaseStock(productName, productBrand, quantity);
        return new AdquiredProduct(merchandise.name(), merchandise.brand(), merchandise.price(), quantity);
    }


    public Merchandise getMerchandise(String productName, String productBrand) {
        return this.findMerchandise(productName, productBrand);
    }

    public void addMerchandiseDiscountFor(String productName, String productBrand, Integer percentOfDiscount, LocalDate endDate) {
        Merchandise merchandise = this.getMerchandise(productName, productBrand);
        this.discountList.add(new MerchandiseDiscount(merchandise, percentOfDiscount, LocalDate.now(), endDate));
    }

    public void addCategoryDiscount(MerchandiseCategory category, Integer percentOfDiscount, LocalDate endDate) {
        this.discountList.add(new CategoryDiscount(category, percentOfDiscount, LocalDate.now(), endDate));
    }

    public LocalDateTime nextTurn(LocalDateTime aDate) {
        if (aDate.toLocalDate().isBefore(proximoTurnoDeLocal.toLocalDate())) {
            LocalDateTime turnoADar = proximoTurnoDeLocal;
            this.updateNextTurn();
            return turnoADar;
        }
        else {
            LocalDateTime turnoADar = primerTurnoDeLocal();
            this.updateNextTurn();
            return turnoADar;
        }
    }

    private void updateNextTurn() {
        if(this.isOpenAt(proximoTurnoDeLocal.getDayOfWeek(), LocalTime.of(proximoTurnoDeLocal.getHour(), proximoTurnoDeLocal.getMinute()).plusMinutes(15))){
            this.proximoTurnoDeLocal = proximoTurnoDeLocal.plusMinutes(15);
        }else{
            this.proximoTurnoDeLocal = this.primerTurnoDeLocal();
        }
    }

}
