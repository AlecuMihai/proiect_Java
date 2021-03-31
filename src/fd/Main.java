package fd;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Restaurant r1 = new Restaurant.Builder("Vivo").build();
        Restaurant r2 = new Restaurant.Builder("Excalibur").build();

        ArrayList<Restaurant> restaurantArrayList = new ArrayList<>(Arrays.asList(r1, r2));

        Product p1 = new Product.Builder("Hamburger").addNutritionFacts(
                new NutritionFacts.Builder(400.0, 1).fat(14.3).calories(20.0).build()).build();
        r1.addItemToMenu(p1, 20.0);
        Product p2 = new Product.Builder("Cheeseburger").build();
        r1.addItemToMenu(p2, 15.75);
        Product p3 = new Product.Builder("Caesar_Salad").build();
        r1.addItemToMenu(p3, 16.99);
        Product p4 = new Product.Builder("Red_Wine").build();
        r1.addItemToMenu(p4, 30.0);

        Market m1 = new Market.Builder("Kaufland", Market.Size.SUPER).build();
        Market m2 = new Market.Builder("Carrefour", Market.Size.MINI).build();

        ArrayList<Market> marketArrayList = new ArrayList<>(Arrays.asList(m1, m2));

        m1.addItemToStock(new Product.Builder("Apa_minerala").build(), 6.99);
        m1.addItemToStock(new Product.Builder("Apa_plata").build(), 6.99);
        m1.addItemToStock(new Product.Builder("Apa_rece_de_izvor").build(), 6.99);

        User u1 = new User("Mihai", "Alecu", "0734485917");
        DeliveryMan d1 = new DeliveryMan.Builder("Alexandru", "Minculescu", "07multide7").build();
        d1.setAvailability(true);

        // @--------------------------------------@

        AppService appService = new AppService();
        appService.listRestaurantMenu(r1);
        appService.listAllMarkets(marketArrayList);
        appService.listAllRestaurants(restaurantArrayList);
        appService.placeOrder(d1, u1, new ArrayList<Product>(Arrays.asList(p1, p2)), m1);

        System.out.println();
    }
}
