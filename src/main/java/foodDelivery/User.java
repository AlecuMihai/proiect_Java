package foodDelivery;

import java.util.Objects;
import java.util.regex.Pattern;

public class User implements Sayable{
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;

    public User(String firstName, String lastName, String phoneNumber) throws IllegalArgumentException{
        Pattern regex = Pattern.compile("[$&+,:;=?@#|/]");
        if(regex.matcher(firstName).find() |
                regex.matcher(lastName).find() |
                regex.matcher(phoneNumber).find()) {
            LOGGER.error("ERROR while creating USER entity");
            throw new IllegalArgumentException("attribute name can not contain special characters");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        LOGGER.info("USER " + this + " created");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return firstName.equals(user.firstName) && lastName.equals(user.lastName) && phoneNumber.equals(user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
    public static Class<?>[] type() {
        return new Class<?>[] {String.class, String.class, String.class};
    }
}
