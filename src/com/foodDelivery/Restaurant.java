package com.foodDelivery;

import java.util.*;

public class Restaurant extends LocalB implements Comparable<Restaurant>{

    @Override
    public int compareTo(Restaurant o) {
        return this.popularityScore.compareTo(o.popularityScore);
    }

    public enum Popularity {LOW, MEDIUM, HIGH}
    private Popularity popularity;
    private HashMap<Product, Double> menu;

    private Integer popularityScore;

    public static class Builder extends LocalB.Builder<Builder> {
        private final Popularity popularity = Popularity.MEDIUM;

        private final Integer popularityScore = 500;

        private HashMap<Product, Double> menu = new HashMap<>();

        public Builder(String name) {
            super(name);
        }
        public Builder setPopularity(Popularity val) {
            return self();
        }
        @Override
        protected Builder self() {
            return this;
        }

        public Builder addItemToMenu(Product product, Double price) throws NullPointerException, IllegalArgumentException{
            if(Objects.isNull(price))
                throw new NullPointerException("price can not be null");
            else if(price <= 0)
                throw new IllegalArgumentException("price needs the be a double value greater than zero");
            menu.put(Objects.requireNonNull(product, "product can not be null"), price);
            return this;
        }

        @Override
        public Restaurant build() {
            return new Restaurant(this);
        }
    }
    private Restaurant(Builder builder) {
        super(builder);
        this.popularity = builder.popularity;
        this.popularityScore = builder.popularityScore;
        this.menu = builder.menu;
    }

    public void addItemToMenu(Product product, Double price) throws NullPointerException, IllegalArgumentException{
        if(Objects.isNull(price))
            throw new NullPointerException("price can not be null");
        else if(price <= 0)
            throw new IllegalArgumentException("price needs to be a double value greater than zero");
        menu.put(Objects.requireNonNull(product, "product can not be null"), price);
    }

    // * ar fi mai inteligent un productID
    public Product getItemFromMenu(String productName) throws NullPointerException, ClassCastException{
        Product productToBeReturned = null;
        for(Product key : menu.keySet()) {
            if(key.getName().equals(productName))
                 productToBeReturned = (Product) key.clone();
        }
        return productToBeReturned;
    }

    public HashMap<Product, Double> getMenu() {
        return (HashMap<Product, Double>) this.menu.clone();
    }

    public Double getProductPrice(Product product) throws NullPointerException{
        return menu.get(Objects.requireNonNull(product));
    }

    @Override
    public Object getID() {
        return this.hashCode();
    }

    //* DE REFACUT toString, hashCode si equals
    @Override
    public String toString() {
        StringBuilder to_be_printed = new StringBuilder("Restaurant name: " + this.name + '\n');
        if(popularity != null)
            to_be_printed.append("Popularity: " + popularity.toString());
        return to_be_printed.toString();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {this.name + this.popularity + this.email});
    }

    @Override
    public boolean equals(Object o) {
        if(o == null)
            throw new NullPointerException("Object entered is null");
        else if(o.getClass() != this.getClass())
            throw new IllegalArgumentException("Object entered is not Market");
        else {
            Restaurant o_2 = (Restaurant) o;
            return o_2.name.equals(this.name) && Objects.equals(o_2.popularity, this.popularity);
        }
    }
}
