package foodDelivery;

import java.util.HashMap;

public class Order<T extends Item, U extends Local> implements Sayable{
    private final U local;
    private final Class<? extends Local> typeParameter;
    private final User user;
    private final DeliveryMan deliveryMan;
    private HashMap<T , Integer> shoppingList = new HashMap<>();
    private Double orderPrice = 0.0;

    public Order(U local, User user, DeliveryMan deliveryMan) {
        this.local = local;
        this.user = user;
        this.deliveryMan = deliveryMan;
        this.typeParameter = local.getClass();
    }

    public U getLocal() {
        return local;
    }

    public User getUser() {
        return user;
    }

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }
    public void addToShoppingList(T item, Integer noOfItems) {
        assert (noOfItems > 0);
        try {
            this.shoppingList.put(item, noOfItems);
            this.orderPrice += local.getItemPrice(item) * noOfItems;
            if(typeParameter == Restaurant.class) {
                Restaurant r = (Restaurant) getLocal();
                r.modifyPopularityScore((double)noOfItems);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Double getOrderPrice() {
        return this.orderPrice;
    }
    public HashMap<T ,Integer> getShoppingList() {
        return this.shoppingList;
    }
}
