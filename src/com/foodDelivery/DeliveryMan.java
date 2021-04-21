package com.foodDelivery;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

public class DeliveryMan implements IdentifiedObject{

    private final String firstName;
    private final String lastName;
    private final String phoneNumber;

    private Boolean isAvailable;

    public enum Vehicle {CAR, BIKE, MOTORBIKE, SCOOTER}
    private final Vehicle vehicle;

    public static class Builder {
        private final String firsName;
        private final String lastName;
        private final String phoneNumber;

        private Vehicle vehicle;

        public Builder(String firstName, String lastName, String phoneNumber) {
            Pattern regex = Pattern.compile("[$&+,:;=?@#|/]");
            if(regex.matcher(lastName).find() || regex.matcher(lastName).find() || regex.matcher(phoneNumber).find())
                throw new IllegalArgumentException("Attributes can not contain special characters");
            this.firsName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
        }
        public Builder addVehicle(Vehicle vehicle) throws NullPointerException{
            vehicle = Objects.requireNonNull(vehicle, "vehicle can not be null");
            return this;
        }
        public DeliveryMan build() {
            return new DeliveryMan(this);
        }
    }
    private DeliveryMan(Builder builder) {
        this.firstName = builder.firsName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.vehicle = builder.vehicle;

        this.isAvailable = true;
    }

    public void setAvailability(Boolean val) throws NullPointerException{
        this.isAvailable = Objects.requireNonNull(val);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryMan)) return false;
        DeliveryMan that = (DeliveryMan) o;
        return firstName.equals(that.firstName) && lastName.equals(that.lastName) && phoneNumber.equals(that.phoneNumber) && isAvailable.equals(that.isAvailable) && vehicle == that.vehicle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber, isAvailable, vehicle);
    }

    public Boolean getAvailability() {
        return this.isAvailable;
    }

    @Override
    public Object getID() {
        return this.hashCode();
    }
}
