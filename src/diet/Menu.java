package diet;

import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a complete menu.
 * It consist of packaged products and servings of recipes.
 *
 */
public class Menu implements NutritionalElement {

	private String name;
	private Food food;
	private Map<String, Double> portion = new TreeMap<>();
	
	private double calories=0, proteins=0, carbs=0, fat=0;

	
	/**
	 * Menu constructor.
	 * The reference {@code food} of type {@link Food} must be used to
	 * retrieve the information about recipes and products 
	 * 
	 * @param nome unique name of the menu
	 * @param food object containing the information about products and recipes
	 */
	public Menu(String name, Food food){
		this.name = name;
		this.food = food;
	}
	
	/**
	 * Adds a given serving size of a recipe.
	 * The recipe is a name of a recipe defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 */
	public void addRecipe(String recipe, double quantity) {
		portion.put(name, quantity);
		//that part could be done faster like in addProduct()
		calories += food.recipes.get(recipe).getCalories()*quantity/100;
		proteins += food.recipes.get(recipe).getProteins()*quantity/100;
		carbs += food.recipes.get(recipe).getCarbs()*quantity/100;
		fat += food.recipes.get(recipe).getFat()*quantity/100;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param product the name of the product to be used as ingredient
	 */
	public void addProduct(String product) {
		portion.put(name, -1.0);	//just a convention for not default size on products
		NutritionalElement nutritionalElement = food.getProduct(product);
		calories += nutritionalElement.getCalories();
		proteins += nutritionalElement.getProteins();
		carbs += nutritionalElement.getCarbs();
		fat += nutritionalElement.getFat();
	}

	public String getName() {
		return name;
	}

	public double getCalories() {
		return calories;
	}

	public double getProteins() {
		return proteins;
	}

	public double getCarbs() {
		return carbs;
	}

	public double getFat() {
		return fat;
	}

	public boolean per100g() {
		// nutritional values are provided for the whole menu.
		// caso i valori restituiti si riferiscono al totale del menu e non ai 100 grammi.
		return false;
	}
}
