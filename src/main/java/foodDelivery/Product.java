package foodDelivery;

import java.util.Objects;

public class Product extends Item implements Sayable{
    private final NutritionFacts nutritionFacts;

    public Product(String name, NutritionFacts nutritionFacts) throws NullPointerException{
        super(name);
        Objects.requireNonNull(nutritionFacts);
        this.nutritionFacts = nutritionFacts;
    }
    public Product(String name, String fat, String cholesterol, String sodium, String carbohydrate, String protein)
            throws NullPointerException{
        super(name);
        this.nutritionFacts = new NutritionFacts(Double.parseDouble(fat),
                Double.parseDouble(cholesterol),
                Double.parseDouble(sodium),
                Double.parseDouble(carbohydrate),
                Double.parseDouble(protein));
    }
    public NutritionFacts getNutritionFacts() {
        return this.nutritionFacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return nutritionFacts.equals(product.nutritionFacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nutritionFacts);
    }

    public static Class<?>[] type() {
        return new Class<?>[] {String.class, String.class, String.class, String.class, String.class, String.class};
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
