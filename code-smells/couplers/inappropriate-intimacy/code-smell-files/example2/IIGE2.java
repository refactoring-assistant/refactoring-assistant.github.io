import java.util.ArrayList;
import java.util.List;

enum IngredientVariation {
  CREAM(0.10),
  SUGAR(0.05),
  ESPRESSO_SHOT(0.75),
  HOT_WATER(0.01),
  TAP_WATER(0.00),
  ICE(0.02),
  STEAMED_MILK(0.30),
  CHOCOLATE_SYRUP(0.50);

  private final double cost;

  IngredientVariation(double cost) {
    this.cost = cost;
  }

  public double getCost() {
    return cost;
  }
}

interface CoffeeVariation {
  void brew();
  void addCream(int extraPumps);
  void addSugar(int extraPumps);
  List<Ingredient> getIngredients();
  double getCost();
}

abstract class AbstractCoffeeVariation implements CoffeeVariation {
  protected List<Ingredient> ingredients = new ArrayList<>();

  @Override
  public void addCream(int extraPumps) {
    for (int i = 0; i <= extraPumps; i++) {
      ingredients.add(Ingredient.CREAM);
    }
  }

  @Override
  public void addSugar(int extraPumps) {
    for (int i = 0; i <= extraPumps; i++) {
      ingredients.add(Ingredient.SUGAR);
    }
  }

  @Override
  public double getCost() {
    return ingredients.stream()
      .mapToDouble(Ingredient::getCost)
      .sum();
  }

  @Override
  public List<Ingredient> getIngredients() {
    return ingredients;
  }
}

class EspressoVariation extends AbstractCoffeeVariation {

  public EspressoVariation() {
    this.ingredients = new ArrayList<>();
  }

  @Override
  public void brew() {
    System.out.println("Brewing espresso: grinding beans, extracting shot.");
    this.ingredients.add(Ingredient.ESPRESSO_SHOT);
  }
}

class AmericanoVariation extends EspressoVariation {
  protected boolean iced;

  public AmericanoVariation(boolean iced) {
    super();
    this.iced = false;
  }

  @Override
  public void brew() {
    super.brew();

    if (iced) {
      this.ingredients.add(Ingredient.TAP_WATER);
      this.ingredients.add(Ingredient.ICE);
    } else {
      this.ingredients.add(Ingredient.HOT_WATER);
    }
  }

  @Override
  public void addCream(int extraPumps) {
    super.addCream(0);

    for (int i=0; i<=extraPumps; i++) {
      this.ingredients.add(Ingredient.CREAM);
    }
  }

  @Override
  public void addSugar(int extraPumps) {
    super.addSugar(0);

    for (int i=0; i<=extraPumps; i++) {
      this.ingredients.add(Ingredient.SUGAR);
    }
  }
}

class LatteVariation extends AbstractCoffeeVariation {
  private boolean iced;

  public LatteVariation(Boolean iced) {
    this.iced = false;
    this.ingredients = new ArrayList<>();

    this.ingredients.add(Ingredient.CREAM);
    this.ingredients.add(Ingredient.CREAM);
    this.ingredients.add(Ingredient.SUGAR);
  }

  @Override
  public void brew() {
    System.out.println("Brewing Latte: pulling espresso and steamed milk.");
    this.ingredients.add(Ingredient.ESPRESSO_SHOT);
    this.ingredients.add(Ingredient.ESPRESSO_SHOT);
    this.ingredients.add(Ingredient.STEAMED_MILK);
  }
}

class CafeMochaVariation extends LatteVariation {

  public CafeMochaVariation(Boolean iced) {
    super(iced);

    this.ingredients.add(Ingredient.CHOCOLATE_SYRUP);
    this.ingredients.add(Ingredient.CHOCOLATE_SYRUP);
  }

  @Override
  public void brew() {
    super.brew();
  }

  @Override
  public void addCream(int extraPumps) {
    super.addCream(0);
  }

  @Override
  public void addSugar(int extraPumps) {
    super.addSugar(0);
  }

  private void addChocolate(int extraPumps) {
    for (int i = 0; i <= extraPumps; i++) {
      this.ingredients.add(Ingredient.CHOCOLATE_SYRUP);
    }
  }
}

class IIGE01 {
  public static void main(String[] args) {
    CoffeeVariation espresso = new EspressoVariation();
    espresso.brew();
    espresso.addCream(1);
    espresso.addSugar(2);
    printSummary("Espresso", espresso);

    CoffeeVariation americano = new AmericanoVariation(false);
    americano.brew();
    americano.addCream(0);
    americano.addSugar(1);
    printSummary("Americano (hot)", americano);

    CoffeeVariation icedAmericano = new AmericanoVariation(true);
    icedAmericano.brew();
    icedAmericano.addCream(1);
    icedAmericano.addSugar(0);
    printSummary("Americano (iced)", icedAmericano);

    CoffeeVariation latte = new LatteVariation(false);
    latte.brew();
    latte.addCream(2);
    latte.addSugar(0);
    printSummary("Latte (hot)", latte);

    CoffeeVariation icedLatte = new LatteVariation(true);
    icedLatte.brew();
    icedLatte.addCream(0);
    icedLatte.addSugar(1);
    printSummary("Latte (iced)", icedLatte);

    CoffeeVariation mocha = new CafeMochaVariation(false);
    mocha.brew();
    mocha.addCream(1);
    mocha.addSugar(1);
    printSummary("Cafe Mocha (hot)", mocha);

    CoffeeVariation icedMocha = new CafeMochaVariation(true);
    icedMocha.brew();
    icedMocha.addCream(0);
    icedMocha.addSugar(2);
    printSummary("Cafe Mocha (iced)", icedMocha);
  }

  private static void printSummary(String name, CoffeeVariation drink) {
    System.out.printf("--- %s ---\n", name);
    System.out.printf("Cost: $%.2f\n", drink.getCost());
    System.out.println("Ingredients: " + drink.getIngredients());
    System.out.println();
  }
}
