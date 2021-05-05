package foodDelivery;

import java.util.Objects;

public class NutritionFacts implements Cloneable {
    private final Double Fat;
    private final Double Cholesterol;
    private final Double Sodium;
    private final Double Carbohydrate;
    private final Double Protein;

    public NutritionFacts(Double fat, Double cholesterol, Double sodium, Double carbohydrate, Double protein) {
        Fat = fat;
        Cholesterol = cholesterol;
        Sodium = sodium;
        Carbohydrate = carbohydrate;
        Protein = protein;
    }
    public NutritionFacts(String[] values) {
        Fat = Double.parseDouble(values[0]);
        Cholesterol = Double.parseDouble(values[1]);
        Sodium = Double.parseDouble(values[2]);
        Carbohydrate = Double.parseDouble(values[3]);
        Protein = Double.parseDouble(values[4]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NutritionFacts)) return false;
        NutritionFacts that = (NutritionFacts) o;
        return Fat.equals(that.Fat) &&
                Cholesterol.equals(that.Cholesterol) &&
                Sodium.equals(that.Sodium) &&
                Carbohydrate.equals(that.Carbohydrate) &&
                Protein.equals(that.Protein);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Fat, Cholesterol, Sodium, Carbohydrate, Protein);
    }

    @Override
    public String toString() {
        return "NutritionFacts{" +
                "Fat=" + Fat +
                ", Cholesterol=" + Cholesterol +
                ", Sodium=" + Sodium +
                ", Carbohydrate=" + Carbohydrate +
                ", Protein=" + Protein +
                '}';
    }
}