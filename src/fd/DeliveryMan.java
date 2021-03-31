package fd;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

public class DeliveryMan implements IdentifiedObject{

    static final AtomicLong NEXT_ID = new AtomicLong(100);
    final long id = NEXT_ID.getAndIncrement();

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
            Pattern regex = Pattern.compile("[$&+,:;=?@#|]");
            if(regex.matcher(lastName).find() || regex.matcher(lastName).find() || regex.matcher(phoneNumber).find())
                throw new IllegalArgumentException("No special characters");
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

    public Boolean getAvailability() {
        return this.isAvailable;
    }
    @Override
    public Object getID() {
        return id;
    }
}
