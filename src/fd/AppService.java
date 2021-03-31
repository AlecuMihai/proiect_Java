package fd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class AppService {
    public static final AppService INSTANCE = new AppService();
    public void listAllRestaurants(ArrayList<Restaurant> restaurantsArrayList) throws NullPointerException{
        Collections.sort(restaurantsArrayList);
        for (Restaurant restaurant : restaurantsArrayList) {
            System.out.println(restaurant);
        }
    }
    public void listRestaurantMenu(Restaurant restaurant) throws NullPointerException{
        for (Product product : Objects.requireNonNull(restaurant).getMenu().keySet()) {
            System.out.println(product.toString() + " " + restaurant.getMenu().get(product));
        }
    }
    public void listAllMarkets(ArrayList<Market> marketArrayList) throws NullPointerException{
        for(Market market : Objects.requireNonNull(marketArrayList)) {
            System.out.println(market);
        }
    }
    public void placeOrder(DeliveryMan deliveryMan, User user, ArrayList<Product> arrayList, LocalB shop) throws NullPointerException{
        if(deliveryMan.getAvailability()) {
            Order order = new Order.Builder(Objects.requireNonNull(shop),
                                            Objects.requireNonNull(user),
                                            Objects.requireNonNull(deliveryMan)).build();
        }
        else {
            System.out.println("DeliveryMan is not available");
        }
    }
}
