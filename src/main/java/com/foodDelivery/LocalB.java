package com.foodDelivery;

import java.util.regex.Pattern;

public abstract class LocalB implements IdentifiedObject{
    final String name;
    final String email;

    abstract static class Builder<T extends Builder<T>> {
        private final String name;
        private String email = "";
        Builder (String name) {
            Pattern regex = Pattern.compile("[$&+,:;=?@#|/]");
            if(regex.matcher(name).find())
                throw new IllegalArgumentException("attribute name can not contain special characters");
            this.name = name;
        }
        public T email(String email) {
            this.email = email;
            return self();
        }
        protected abstract T self();
        abstract LocalB build();
    }
    LocalB(Builder<?> builder) {
        this.name = builder.name;
        this.email = builder.email;
    }

    @Override
    public abstract String toString();
    @Override
    public abstract int hashCode();
    @Override
    public abstract boolean equals(Object o);
}