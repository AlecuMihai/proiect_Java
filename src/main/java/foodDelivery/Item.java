package foodDelivery;

import java.util.regex.Pattern;

public abstract class Item implements Sayable{
    private final String name;
    protected Item(String name) {
        Pattern regex = Pattern.compile("[$&+,:;=?@#|/]");
        if(regex.matcher(name).find()) {
            LOGGER.error("ERROR while creating ITEM entity");
            throw new IllegalArgumentException("argument can not contain special characters");
        }
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
