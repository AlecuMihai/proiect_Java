package com.foodDelivery;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Market extends LocalB {
    public enum Size {MINI, SUPER}
    private final Size size;
    private HashMap<Product, Double> stock;

    public static class Builder extends LocalB.Builder<Builder> {

        private final Size size;
        private HashMap<Product, Double> stock = new HashMap<>();
        public Builder(String name, Size size) {
            super(name);
            this.size = Objects.requireNonNull(size);
        }

        public Builder addProduceToStock(Product product, Double price) {
            this.stock.put(Objects.requireNonNull(product, "product can not be null"), price);
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }
        @Override
        public Market build() {
            return new Market(this);
        }
    }

    private Market(Builder builder) throws ClassCastException{
        super(builder);
        this.size = builder.size;

        // * TO-DO SUPPRESS WARNING
        this.stock = (HashMap<Product, Double>) builder.stock.clone();
    }
    @Override
    public Object getID() {
        return this.hashCode();
    }
    @Override
    public String toString() {
        return "Market name: " + this.name +
                "Size: " + this.size;
    }

    // * TO-DO - REWRITE HASHCODE AND EQUALS

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {this.name, this.size});
    }

    @Override
    public boolean equals(Object copy) {
        if(copy == null)
            throw new NullPointerException("Object entered is null");
        else if(copy.getClass() != this.getClass())
            throw new IllegalArgumentException("Object entered is not Market");
        else {
            Market copy_2 = (Market)copy;
            return copy_2.name.equals(this.name) && Objects.equals(copy_2.size, this.size);
        }
    }

    public void addItemToStock(Product product, Double price) throws NullPointerException{
        if(Objects.isNull(price))
            throw new NullPointerException("price can not be null");
        else if(price <= 0)
            throw new IllegalArgumentException("price needs to be a double greater than zero");
        stock.put(Objects.requireNonNull(product, "product can not be null"), price);
    }

    public Product getItemFromStock(String productName) {
        Product productToBeReturned = null;
        for(Product key : stock.keySet()) {
            if(key.getName().equals(productName))
                productToBeReturned = (Product) key.clone();
        }
        return productToBeReturned;
    }

    // public void removeItemFromStock() {}

    public HashMap<Product, Double> getProductsList() {
         HashMap<Product, Double> o = (HashMap<Product, Double>) this.stock.clone();
         return o.getClass() == stock.getClass() ? o : null;
    }

    public Double getProducePrice(Product product) throws NullPointerException{
        return stock.get(Objects.requireNonNull(product));
    }

    public static void main(String[] args) {

    }
}
