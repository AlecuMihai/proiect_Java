package foodDelivery;

import java.util.List;

public class Dish extends Item implements Sayable{
    private final Double quantity;
    private List<String> mainIngredients = null;

    public Dish(String name, Double quantity){
        super(name);
        this.quantity = quantity;
    }
    public void addMainIngredients(List<String> list) {
        mainIngredients = list;
    }
    public Double getQuantity() {
        return this.quantity;
    }
    public List<String> getMainIngredients() {
        return mainIngredients;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Dish : " + getName() + " " + quantity + "g\n");
        if(mainIngredients != null) {
            stringBuilder.append("Main ingredients : ");
            mainIngredients.forEach(element -> stringBuilder.append(element).append(" "));
        }
         return stringBuilder.toString();
    }

    public static Class<?>[] type() {
        return new Class<?>[] {String.class, Double.class};
    }
}
