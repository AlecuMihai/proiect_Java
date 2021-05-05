package foodDelivery;

import java.util.HashMap;
import java.util.Objects;

public class Market extends Local implements Sayable{

    public enum Size {MINI, SUPER}
    private final Size size;
    private HashMap<Product, Double> stock = new HashMap<>();

    public Market(String name, String email, String size) throws IllegalArgumentException {
        super(name, email);
        this.size = Size.valueOf(size);
    }
    public void addProductToStock(Product product, Double productPrice){
        try{
            Objects.requireNonNull(product);
            Objects.requireNonNull(productPrice);
            if(productPrice <= 0)
                throw new IllegalArgumentException("price cannot be less than zero");
            stock.putIfAbsent(product, productPrice);
        } catch (NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
    public void addProductToStock(HashMap<Product, Double> hashMap) throws NullPointerException{
        Objects.requireNonNull(hashMap);
        hashMap.forEach((key, value) -> stock.put(key, value));
    }
    public Product getProductFromStock(String productName) {
        for(Product key : stock.keySet()) {
            if(key.getName().equals(productName))
                return key;
        }
        return null;
    }
    public void removeProductFromStock(Product product) {
        stock.remove(product);
    }
    public HashMap<Product, Double> getProductList() {
        return this.stock;
    }
    public Size getSize() {
        return size;
    }
    public static Class<?>[] type() {
        return new Class<?>[] {String.class, String.class, String.class};
    }

    @Override
    public String toString() {
        return "Market@" + id + "{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", size=" + size + "}";
    }
    @Override
    public Double getItemPrice(Item item) {
        return stock.get((Product)item);
    }

    @Override
    public Double getItemPrice(String string) throws Exception {
        for(Product key : stock.keySet()) {
            if(key.getName().equals(string)) {
                return stock.get(key);
            }
        }
        throw new Exception("Item not found");
    }
}
