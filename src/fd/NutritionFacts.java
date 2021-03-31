package fd;

public class NutritionFacts implements Cloneable{
    private final Double servingSize;
    private final Integer servings;
    private final Double calories;
    private final Double fat;
    private final Double sodium;
    private final Double carbohydrate;

    public static class Builder {
        // Required parameters
        private final Double servingSize;
        private final int servings;

        // Optional parameters - initialized to default values
        private Double calories = (double) 0;
        private Double fat = (double) 0;
        private Double sodium = (double) 0;
        private Double carbohydrate = (double) 0;

        public Builder(Double servingSize, Integer servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(Double val)
        { calories = val; return this; }

        public Builder fat(Double val)
        { fat = val; return this; }

        public Builder sodium(Double val)
        { sodium = val; return this; }

        public Builder carbohydrate(Double val)
        { carbohydrate = val; return this; }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }
    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    // * If you've read the item about cloning in my book, especially if you read between the lines,
    // you will know that I think clone is deeply broken.
    // [...] It's a shame that Cloneable is broken, but it happens.**

    // so should I?
    @Override
    public NutritionFacts clone() {
        NutritionFacts foo;
        try {
            foo = (NutritionFacts) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
        return foo;
    }

    //* DE REFACUT HASHCODE SI EQUALS

    public static void main(String[] args) {
        NutritionFacts n1 = new NutritionFacts.Builder(10.0, 20).build();
        NutritionFacts n2 = n1.clone();
        System.out.println();
    }
}
