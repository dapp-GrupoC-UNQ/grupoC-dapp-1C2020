package com.example.demo.model.purchasePriceCalculator;

import com.example.demo.model.AcquiredProduct;
import com.example.demo.model.Purchase;
import com.example.demo.model.purchase.MultiPurchase;
import com.example.demo.model.store.Store;
import com.example.demo.model.merchandise.MerchandiseCategory;

import java.util.List;
import java.util.stream.Collectors;

public class PurchasePriceCalculator {

    public Double calculatePriceFor(Purchase purchase) {
        return purchase.getListOfAdquiredProducts().stream().mapToDouble(AcquiredProduct::totalPrice).sum();
    }

    public Double calculatePriceForCategory(Purchase purchase, MerchandiseCategory category) {
        Store purchaseStore = purchase.store();
        List<AcquiredProduct> purchaseProductsWithCategory = purchase.getListOfAdquiredProducts().stream().filter(acquiredProduct -> purchaseStore.isProductFromCategory(acquiredProduct, category)).collect(Collectors.toList());
        return purchaseProductsWithCategory.stream().mapToDouble(AcquiredProduct::totalPrice).sum();
    }

    public Double calculatePriceForMultiPurchase(MultiPurchase multiPurchase) {
        return multiPurchase.getPurchases().stream().mapToDouble(this::calculatePriceFor).sum();
    }

    public Double calculatePriceForCategoryOfMultiPurchase(MultiPurchase multiPurchase, MerchandiseCategory category) {
        return multiPurchase.getPurchases().stream().mapToDouble(purchase -> calculatePriceForCategory(purchase, category)).sum();
    }
}
