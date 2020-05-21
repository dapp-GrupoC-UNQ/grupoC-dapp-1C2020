package com.example.demo.model;

import com.example.demo.model.store.Store;
import com.example.demo.model.ticket.Ticket;
import com.example.demo.sendMail.MailSender;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Purchase {

    private Store purchaseStore;
    private User purchaseUser;
    private List<AcquiredProduct> productList = new ArrayList<>();

    public Purchase(Store store, User name){
        purchaseStore = store;
        purchaseUser = name;
    }

    public Store store() { return this.purchaseStore; }

    public User user() { return this.purchaseUser;  }

    public Integer productsQuantity() { return this.productList.stream().mapToInt(AcquiredProduct::quantity).sum();  }

    public List<AcquiredProduct> getListOfAdquiredProducts() {
        return this.productList;
    }

    public void addProduct(String productName, String productBrand, Integer quantity) {
        this.productList.add(this.store().getProduct(productName, productBrand, quantity));
    }

    public Boolean breaksMoneyThreshold() {
        return this.user().moneyThreshold().breaksTheLimitWith(this);
    }

    public void finishPurchase(String paymentMethod, MailSender mailSender) {
        StorePickUp date = new StorePickUp(this.store().nextTurn(LocalDateTime.now()));
        purchaseUser.addTicketOfPurchase(new Ticket(this, paymentMethod, date));
        //mailSender.sendMail(purchaseStore.mail(), purchaseUser.username(), "Confirmacion de compra", "Su compra fue confirmada, su turno de retiro es " + date.pickUpDate());
    }

    public void finishPurchaseWithHomeDelivery(String paymentMethod, String deliveryAddress, MailSender mailSender) {
        HomeDelivery delivery = new HomeDelivery(deliveryAddress, this.store().homeDeliveryTime());
        purchaseUser.addTicketOfPurchase(new Ticket(this, paymentMethod, delivery));
        //mailSender.sendMail(store().mail(), user().username(), "Confirmacion de compra", "Su compra se completó con éxito, su pedido llegara el " + delivery.pickUpDate());
    }
}