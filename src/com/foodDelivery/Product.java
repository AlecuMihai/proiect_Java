package com.foodDelivery;

import java.util.Objects;

public class Product implements Cloneable{
    private final String name;
    private final NutritionFacts nutritionFacts;

    public static class Builder {

        private final String name;
        private NutritionFacts nutritionFacts = null;

        public Builder(String name) throws NullPointerException{
            this.name = Objects.requireNonNull(name, "name can not be null");
        }

        public Builder addNutritionFacts(NutritionFacts nutritionFacts) throws NullPointerException{
            this.nutritionFacts = Objects.requireNonNull(nutritionFacts, "nutritionFacts can not be null");
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    private Product(Builder builder) throws NullPointerException {
        this.name = builder.name;
        this.nutritionFacts = builder.nutritionFacts;
    }

    public String getName() {
        return this.name;
    }

    // * DE IMPLEMENTAT CLONE
    @Override
    public Product clone() {
        Product foo;
        try {
            foo = (Product) super.clone();
           // NutritionFacts same reference ??
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
        return foo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return name.equals(product.name) && nutritionFacts.equals(product.nutritionFacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nutritionFacts);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static void main(String[] args) {

    }
}
