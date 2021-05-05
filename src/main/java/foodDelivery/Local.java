package foodDelivery;

import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

public abstract class Local implements Sayable{
    protected final String name;
    protected final String email;

    static final AtomicLong NEXT_ID = new AtomicLong(100);
    final long id = NEXT_ID.getAndIncrement();

    public long getId() {
        return id;
    }

    public Local(String name, String email) throws IllegalArgumentException{
        Pattern regex = Pattern.compile("[$&+,:;=?@#|/]");
        if(regex.matcher(name).find()) {
            LOGGER.error("ERROR while creating LOCAL entity");
            throw new IllegalArgumentException("attribute name can not contain special characters");
        }
        this.name = name;
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public abstract Double getItemPrice(Item item) throws Exception;
    public abstract Double getItemPrice(String string) throws Exception;
    public String getEmail() {
        return email;
    }
}
