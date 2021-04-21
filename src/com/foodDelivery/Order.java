package com.foodDelivery;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Order implements IdentifiedObject{
    private final LocalB shop;
    private final User user;
    private final DeliveryMan deliveryMan;
    private HashMap<Product, Integer> shoppingList = new HashMap<>();
    private Double price;

    public static class Builder {

        private final LocalB shop;
        private final User user;
        private final DeliveryMan deliveryMan;

        Builder(LocalB shop, User user, DeliveryMan deliveryMan) throws NullPointerException{
            this.shop = Objects.requireNonNull(shop, "shop must not be null");
            this.user = Objects.requireNonNull(user, "user must not be null");
            this.deliveryMan = Objects.requireNonNull(deliveryMan, "deliveryMan must not be null");
        }

        public Order build() {
            return new Order(this);
        }
    }

    // * TO-DO REWRITE toString, hashCode and equals
    public String toString() {
        return "Order{" +
                "shop=" + shop +
                ", user=" + user +
                ", deliveryMan=" + deliveryMan +
                ", shoppingList=" + shoppingList +
                ", id=" + this.getID() +
                '}';
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {this.shop, this.user, this.deliveryMan, this.shoppingList});
    }

    @Override
    public Object getID() {
        return this.hashCode();
    }

    private Order(Builder builder) throws NullPointerException{
        this.shop = builder.shop;
        this.user = builder.user;
        this.deliveryMan = builder.deliveryMan;
        this.price = 0.0;
    }

    public void addToShoppingList(Product product, int noOfProducts, Double price) throws NullPointerException{
        this.shoppingList.put(Objects.requireNonNull(product, "product can not be null"), (noOfProducts <= 0) ? 1 : noOfProducts);
        this.price = this.price + Objects.requireNonNull(price) * noOfProducts;
    }

    public Double getOrderPrice() {
        return this.price;
    }
}
