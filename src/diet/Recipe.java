package diet;

import java.util.*;


/**
 * Represent a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
	
	private String name;
	private Food food;
	private Map<String, Double> ingredients = new TreeMap<>();
	private double calories=0, proteins=0, carbs=0, fat=0;
    private double grams=0;
	
	/**
	 * Recipe constructor.
	 * The reference {@code food} of type {@link Food} must be used to
	 * retrieve the information about ingredients. 
	 * 
	 * @param nome unique name of the recipe
	 * @param food object containing the information about ingredients
	 */
	public Recipe(String name, Food food){
		this.name=name;
		this.food=food;
		food.recipes.put(name, this);//si aggiunge alle ricette possibili di food
	}
	
	/**
	 * Adds a given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material defined with the {@code food}
	 * argument of the constructor.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 */
	public void addIngredient(String material, double quantity) {
		NutritionalElement nutritionalElement = food.getRawMaterial(material);
		ingredients.put(nutritionalElement.getName(), quantity); 
		grams += quantity;
		
		calories += nutritionalElement.getCalories()*quantity/100;
		proteins += nutritionalElement.getProteins()*quantity/100;
		carbs += nutritionalElement.getCarbs()*quantity/100;
		fat += nutritionalElement.getFat()*quantity/100;
	}

	public String getName() {
		return name;
	}

	public double getCalories() {
		return calories*100/grams;
	}

	public double getProteins() {
		return proteins*100/grams;
	}

	public double getCarbs() {
		return carbs*100/grams;
	}

	public double getFat() {
		return fat*100/grams;
	}

  public boolean per100g() {
	  // a recipe expressed nutritional values per 100g
//	  While the sum of the amounts of ingredients (in grams) of a recipe is not necessarily equal to 100g, 
//	  the nutritional values of the recipe must refer to an hypothetical portion of 100 grams. 
	  
    return true;
  }

}
