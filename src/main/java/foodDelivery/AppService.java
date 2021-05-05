package foodDelivery;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import fileReader.ConstructorReflectionFromCSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppService implements Sayable{

    private static AppService INSTANCE;
    private AppService() {
        ConstructorReflectionFromCSVReader ctor =
                ConstructorReflectionFromCSVReader.getInstance();

        try {
            deliveryManList = (ArrayList<DeliveryMan>) ctor.readFromCSVFile(new File("src/main/resources/DMRU/devmen.csv").toPath(),
                    "foodDelivery.DeliveryMan", DeliveryMan.type());
        } catch (Exception e) {
            LOGGER.error("ERROR while creating DELIVERYMAN " + e.getMessage());
        }

        try {
            restaurantList = (ArrayList<Restaurant>) ctor.readFromCSVFile(new File("src/main/resources/DMRU/restaurants.csv").toPath(),
                    "foodDelivery.Restaurant", Restaurant.type());

            //load DISHES INTO RESTAURANTS;
        } catch (Exception e) {
            LOGGER.error("ERROR while creating RESTAURANT " + e.getMessage());
        }

        try {
            marketList = (ArrayList<Market>) ctor.readFromCSVFile(new File("src/main/resources/DMRU/markets.csv").toPath(),
                    "foodDelivery.Market", Market.type());
        } catch (Exception e) {
            LOGGER.error("ERROR while creating MARKET " + e.getMessage());
        }

        try {
            userList = (ArrayList<User>) ctor.readFromCSVFile(new File("src/main/resources/DMRU/users.csv").toPath(),
                    "foodDelivery.User", User.type());
        } catch (Exception e) {
            LOGGER.error("ERROR while creating MARKET " + e.getMessage());
        }

        // LOAD PRODUCTS/DISHES FROM SPECIFIC CSV FILES

        File directoryPath = new File("src/main/resources/itemsAndSubclasses");
        File[] files = directoryPath.listFiles();

        assert files != null;
        for(File file : files) {
            if(file.getName().contains("restaurant")) {
                System.out.println("Reading from: " + file.getName());
                Pattern pattern = Pattern.compile("\\d{3}");
                Matcher matcher = pattern.matcher(file.getName());

                if(matcher.find()) {
                    String restaurant_id = file.getName().substring(matcher.start(), matcher.end());
                    System.out.println("Loading into restaurant: " + restaurant_id + "...");

                    try(CSVReader csvReader = new CSVReaderBuilder(new FileReader(file)).build()) {

                        Restaurant restaurant = searchById(Long.parseLong(restaurant_id), this.restaurantList);
                        for(int i = 0; i < 30; ++i) {
                            System.out.print("|");
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println();

                        if(restaurant != null) {
                            csvReader.readAll().forEach(data -> {
                                try {
                                    Dish dish = new Dish(data[0], Double.parseDouble(data[1]));
                                    restaurant.addDishToMenu(dish, Double.parseDouble(data[2]));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        }

                    } catch (IOException | CsvException e) {
                        LOGGER.error("ERROR: " + e.getMessage());
                    }
                }
            } else if(file.getName().contains("market")) {
                System.out.println("Reading from file: " + file.getName());

                Pattern pattern = Pattern.compile("\\d{3}");
                Matcher matcher = pattern.matcher(file.getName());

                if(matcher.find()) {
                    String market_id = file.getName().substring(matcher.start(), matcher.end());

                    System.out.println("Loading into market: " + market_id + "...");

                    try(CSVReader csvReader = new CSVReaderBuilder(new FileReader(file)).build()) {

                        Market market = searchById(Long.parseLong(market_id), this.marketList);
                        for(int i = 0; i < 30; ++i) {
                            System.out.print("|");
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println();

                        if(market != null) {
                            csvReader.readAll().forEach(data -> {
                                try {
                                    Product product = new Product(data[0], data[1], data[2], data[3], data[4], data[5]);
                                    market.addProductToStock(product, Double.parseDouble(data[6]));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        }

                    } catch (IOException | CsvException e) {
                        LOGGER.error("ERROR: " + e.getMessage());
                    }
                }
            }
        }
    }

    // ==============
    // GET INSTANCE ||
    // ==============
    static {
        try {
            INSTANCE = new AppService();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("ERROR while getting APPSERVICE INSTANCE");
        }
    }

    public static AppService getInstance() {
        return INSTANCE;
    }

    private ArrayList<DeliveryMan> deliveryManList = null;
    private ArrayList<Restaurant> restaurantList = null;
    private ArrayList<Market> marketList = null;
    private ArrayList<User> userList = null;

    // ==================
    // P R I N T E R S ||
    // ==================
    public void listAllDeliveryMan() {
        this.deliveryManList.forEach(System.out::println);
    }
    public void listAllMarkets() {
        AtomicLong index = new AtomicLong(1);
        for(Market market : this.getMarketList()) {
            System.out.println(index.getAndIncrement() + ". " + market.getName());
        }
    }
    public void listAllRestaurants() {
        AtomicLong index = new AtomicLong(1);
        for(Restaurant restaurant : this.getRestaurantList()) {
            System.out.println(index.getAndIncrement() + ". " + restaurant.getName());
        }
    }
    public void listAllUsers() {
        this.userList.forEach(System.out::println);
    }


    // =============
    // G E T E R S ||
    //==============
    public List<DeliveryMan> getDeliveryManList() {
        return deliveryManList;
    }
    public List<Market> getMarketList() {
        return marketList;
    }
    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }
    public List<User> getUserList() {
        return userList;
    }

    <T extends Local> T searchById(Long localId, ArrayList<T> localsList) {
        for(T local : localsList) {
            if(local.getId() == localId)
                return local;
        }
        return null;
    }

    // ====================
    // U T I L I T I E S  ||
    // ====================
    public void placeOrder(){
        System.out.print("Select where to order from:\n[1.] Restaurant\n[2.] Market\nYour input: ");
        Integer choice = null;
        Scanner scanner = new Scanner(System.in);

        while(choice == null || (choice < 1 || choice > 2)) {
            choice = scanner.nextInt();
        }
        switch (choice) {
            case 1: {
                listAllRestaurants();

                HashMap<Dish, Integer> orderList = new HashMap<>();

                choice = null;
                System.out.print("List the dishes of: ");
                while(choice == null || (choice < 1 || choice > this.getRestaurantList().size())) {
                    choice = scanner.nextInt();
                }
                HashMap<Dish, Double> menu = this.getRestaurantList().get(choice - 1).getMenu();
                AtomicInteger index = new AtomicInteger(1);

                for(Map.Entry<Dish, Double> entry : menu.entrySet()) {
                    System.out.println(index.getAndIncrement() + ". "+ entry.getKey() + "\tPrice: " + entry.getValue());
                }

                Object[] menuArray = menu.keySet().toArray();

                Boolean orderDone = false;
                System.out.println("Enter \"0\" to confirm order.");
                while(!orderDone) {
                    int choiceIndex = -1;
                    System.out.print("Add to order: ");
                    choiceIndex = scanner.nextInt();

                    while(choiceIndex - 1 < -1 || choiceIndex > menu.size()) {
                        System.out.print("Invalid input! Please try again: ");
                        choiceIndex = scanner.nextInt();
                    }
                    if(choiceIndex == 0) {
                        orderDone = true;
                    } else {
                        if(orderList.containsKey((Dish)menuArray[choiceIndex - 1])) {
                            Integer noOfItems = orderList.get(((Dish)menuArray[choiceIndex - 1]));
                            orderList.put((Dish)menuArray[choiceIndex - 1], noOfItems + 1);
                        } else {
                            orderList.put((Dish)menuArray[choiceIndex - 1], 1);
                        }
                    }
                }
                orderList.forEach((key, value) -> System.out.println(key + " " + value));
                break;
            }
            case 2: {
                listAllMarkets();

                HashMap<Product, Integer> orderList = new HashMap<>();

                choice = null;
                System.out.print("List the produces of :");
                while(choice == null || (choice < 1 || choice > this.getRestaurantList().size())) {
                    choice = scanner.nextInt();
                }
                HashMap<Product, Double> stock = this.getMarketList().get(choice - 1).getProductList();
                AtomicInteger index = new AtomicInteger(1);

                for(Map.Entry<Product, Double> entry : stock.entrySet()) {
                    System.out.println(index.getAndIncrement() + ". Product: " + entry.getKey() + "\n\tPrice: " + entry.getValue());
                }

                Object[] stockArray = stock.keySet().toArray();

                Boolean orderDone = false;
                System.out.println("Enter \"0\" to confirm order.");
                while(!orderDone) {
                    int choiceIndex = -1;
                    System.out.print("Add to order: ");
                    choiceIndex = scanner.nextInt();

                    while(choiceIndex - 1 < -1 || choiceIndex > stock.size()) {
                        System.out.print("Invalid input! Please try again: ");
                        choiceIndex = scanner.nextInt();
                    }
                    if(choiceIndex == 0) {
                        orderDone = true;
                    } else {
                        if(orderList.containsKey((Product)stockArray[choiceIndex - 1])) {
                            Integer noOfItems = orderList.get(((Product)stockArray[choiceIndex - 1]));
                            orderList.put((Product)stockArray[choiceIndex - 1], noOfItems + 1);
                        } else {
                            orderList.put((Product) stockArray[choiceIndex - 1], 1);
                        }
                    }
                }
                orderList.forEach((key, value) -> System.out.println(key + " " + value));
                break;
            }
            default:
                break;
        }
        scanner.close();
    }
}
