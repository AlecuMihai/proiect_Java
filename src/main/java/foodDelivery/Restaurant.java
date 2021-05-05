package foodDelivery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Objects;

public class Restaurant extends Local implements Sayable{
    private Double popularityScore = 500.0;
    private HashMap<Dish, Double> menu = new HashMap<>();

    public Restaurant(String name, String email) throws IllegalArgumentException {
        super(name, email);
    }

    public void addDishToMenu(Dish dish, Double dishPrice) {
        try {
            Objects.requireNonNull(dish);
            menu.put(dish, dishPrice);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    public void removeDishFromStock(Dish dish) {
        menu.remove(dish);
    }
    public Dish getDishFromMenu(String dishName) {
        for(Dish key : menu.keySet()) {
            if(key.getName().equals(dishName))
                return key;
        }
        return null;
    }
    public HashMap<Dish, Double> getMenu() {
        return this.menu;
    }

    public static Class<?>[] type() {
        return new Class<?>[]{String.class, String.class};
    }

    public void modifyPopularityScore(Double value) {
        popularityScore += value;
    }

    @Override
    public Double getItemPrice(Item item) throws Exception{
        if(this.menu.get((Dish)item) == null) {
            throw new Exception("Item not found");
        }
        else {
            return this.menu.get((Dish)item);
        }
    }

    @Override
    public Double getItemPrice(String string) throws Exception {
        for(Dish key : menu.keySet()) {
            if(key.getName().equals(string)) {
                return menu.get(key);
            }
        }
        throw new Exception("Item not found");
    }

    @Override
    public String toString() {
        return "Restaurant@" + id + "{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", popularityScore=" + popularityScore + "}";
    }
}
