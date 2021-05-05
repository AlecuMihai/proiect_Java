package foodDelivery;

import java.util.regex.Pattern;

public class DeliveryMan implements Sayable{
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;

    private Boolean isAvailable = false;

    public enum Vehicle {CAR, BIKE, MOTORBIKE, SCOOTER}
    private Vehicle vehicle;

    public DeliveryMan(String firstName, String lastName, String phoneNumber, String vehicle) throws IllegalArgumentException{
        Pattern regex = Pattern.compile("[$&+,:;=?@#|/]");
        if(regex.matcher(firstName).find() || regex.matcher(lastName).find()) {
            LOGGER.error("ERROR while creating deliveryman entity");
            throw new IllegalArgumentException("Attributes can not contain special characters");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.vehicle = Vehicle.valueOf(vehicle);
        LOGGER.info("User created : " + this);
    }

    public void setAvailability(Boolean val){
        this.isAvailable = val;
    }
    public Boolean getAvailability() {
        return this.isAvailable;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public String getName() {
        return this.firstName + " " + this.lastName;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public String toString() {
        return "DeliveryMan{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isAvailable=" + isAvailable +
                ", vehicle=" + vehicle +
                '}';
    }
    public static Class<?>[] type() {
        return new Class<?>[] {String.class, String.class, String.class, String.class};
    }
}
