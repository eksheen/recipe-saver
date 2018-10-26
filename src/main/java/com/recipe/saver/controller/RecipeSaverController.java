package com.recipe.saver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.saver.model.Ingredient;
import com.recipe.saver.model.Recipe;
import com.recipe.saver.services.RecipeSaverService;

@RestController
@RequestMapping("/api/v0/recipe-saver")
public class RecipeSaverController {
	
	@Autowired
	RecipeSaverService recipeSaverService;
	
	@RequestMapping(value = "/get-all-recipes", method = RequestMethod.GET , produces = "application/json")
    public List<Recipe> getAllRecipes() {
		List<Recipe> allRecipes = recipeSaverService.getAllRecipes();  
        return allRecipes;
    }
	
	@RequestMapping(value = "/get-recipes-by-name/{name}", method = RequestMethod.GET , produces = "application/json")
    public List<Recipe> getRecipesByName(@PathVariable String name) {
		List<Recipe> recipesByName = recipeSaverService.getRecipesByName(name);  
        return recipesByName;
    }
	
	
	@RequestMapping(value = "/post-recipe", method = RequestMethod.POST , produces = "application/json")
    public void postRecipe() {
		Recipe pizza = getPizza();
		recipeSaverService.postRecipe(pizza);
		
    }
	
	@RequestMapping(value = "/delete-recipe/{name}", method = RequestMethod.DELETE , produces = "application/json")
    public void deleteRecipe(@PathVariable String name) {
		recipeSaverService.deleteRecipe(name);
    }
	
	public Recipe getPizza() {
		Ingredient dough = new Ingredient();
		Ingredient tomatoSauce = new Ingredient();
		Ingredient cheese = new Ingredient();
		Ingredient pepperoni = new Ingredient();
		Recipe pizzaRecipe = new Recipe();
		List<Ingredient> pizzaIngredients = new ArrayList<Ingredient>();
		List<String> pizzaRecipieSteps = new ArrayList<String>();
		
		dough.setName("pizza dough");
		dough.setMeasurement(1.00);
		dough.setUnit("pound");
		dough.setDescription("");
		
		tomatoSauce.setName("tomato sauce");
		tomatoSauce.setMeasurement(16.00);
		tomatoSauce.setUnit("fl oz");
		tomatoSauce.setDescription("");
		
		cheese.setName("shredded mozzarella cheese");
		cheese.setMeasurement(2.00);
		cheese.setUnit("cup");
		cheese.setDescription("");
		
		pepperoni.setName("pepperoni");
		pepperoni.setMeasurement(20.00);
		pepperoni.setUnit("slices");
		pepperoni.setDescription("");
		
		pizzaIngredients.add(dough);
		pizzaIngredients.add(tomatoSauce);
		pizzaIngredients.add(cheese);
		pizzaIngredients.add(pepperoni);
		
		pizzaRecipe.setDescription("A delicious pepperoni pizza");
		pizzaRecipe.setName("Pepperoni Pizza");
		pizzaRecipe.setIngredients(pizzaIngredients);
		
		String step1 = "roll dough out into a flat circle";
		String step2 = "gently smooth tomato sauce out over the dough";
		String step3 = "sprinkle shredded mozzarella over the top of the pizza";
		String step4 = "place all 20 pepperoni's on the pizza";	
		pizzaRecipieSteps.add(step1);
		pizzaRecipieSteps.add(step2);
		pizzaRecipieSteps.add(step3);
		pizzaRecipieSteps.add(step4);
		
		return pizzaRecipe;
	}
    
}
