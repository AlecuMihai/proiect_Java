import foodDelivery.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Test {
    private final Logger LOGGER = LogManager.getLogger(Test.class);

    @org.junit.Test
    public void test() {
        AppService appService = AppService.getInstance();
        appService.listAllDeliveryMan();
        DeliveryMan d1 = appService.getDeliveryManList().get(0);
        System.out.println(d1.getPhoneNumber());
    }

    @org.junit.Test
    public void test1() {
        AppService appService = AppService.getInstance();
        appService.placeOrder();
    }

    @org.junit.Test
    public void test2() {
        AppService appService = AppService.getInstance();
        //pass
    }

    @org.junit.Test
    public void test3() {
        User u1 = new User("asd", "Asd", "asd");
        Restaurant r1 = new Restaurant("asd", "asd");
        Dish d1 = new Dish("aa", 45.45);
        r1.addDishToMenu(d1, 45.5);
        try {
            System.out.println(r1.getItemPrice(d1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Order<Dish, Restaurant> o1 = new Order<>(r1, new User("asda", "asda", "asd"),
                new DeliveryMan("asd", "asd", "asd", "CAR"));

        o1.addToShoppingList(d1, 4);
        System.out.println(o1.getShoppingList());
    }

    public static void main(String[] args) {
        AppService appService = AppService.getInstance();
        appService.placeOrder();
    }
}
