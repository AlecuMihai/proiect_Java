package fd;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class User implements IdentifiedObject{
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;

    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private final long id = NEXT_ID.getAndIncrement();

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
        return Arrays.hashCode(new Object[]{this.firstName + this.lastName});
    }

    @Override
    public Object getID() {
        return this.hashCode() + this.id;
    }
}


