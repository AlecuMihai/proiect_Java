package fd;

import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

public abstract class LocalB implements IdentifiedObject{
    final String name;
    final String email;

    static final AtomicLong NEXT_ID = new AtomicLong(0);
    final long id = NEXT_ID.getAndIncrement();

    abstract static class Builder<T extends Builder<T>> {
        // atribute obligatorii
        private final String name;
        // atribute optionale
        private String email = "";
        Builder (String name) {
            Pattern regex = Pattern.compile("[$&+,:;=?@#|]");
            if(regex.matcher(name).find())
                throw new IllegalArgumentException("No special characters");
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