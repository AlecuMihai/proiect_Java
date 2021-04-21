package com.foodDelivery;

import java.util.Arrays;
import java.util.Objects;

public class User implements IdentifiedObject{
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;

    User(String firstName, String lastName, String phoneNumber) {

        //* de adaugat protectie date, una alta
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.firstName + this.lastName + this.phoneNumber});
    }

    @Override
    public Object getID() {
        return this.hashCode();
    }
}


