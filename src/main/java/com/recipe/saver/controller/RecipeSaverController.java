package com.recipe.saver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.saver.model.Ingredient;
import com.recipe.saver.model.Recipe;

@RestController
@RequestMapping("/api/v0/recipe-saver")
public class RecipeSaverController {
	
	@RequestMapping(value = "/getRecipe", method = RequestMethod.GET , produces = "application/json")
    public Recipe getRecipe() {
		Recipe pizza = getPizza();
        return pizza;
    }
	
	public Recipe getPizza() {
		Ingredient dough = new Ingredient();
		Ingredient tomatoSauce = new Ingredient();
		Ingredient cheese = new Ingredient();
		Ingredient pepperoni = new Ingredient();
		Recipe pizzaRecipe = new Recipe();
		List<Ingredient> pizzaIngredients = new ArrayList<Ingredient>();
		
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
		
		pepperoni.setName("pizza dough");
		pepperoni.setMeasurement(1.00);
		pepperoni.setUnit("pound");
		pepperoni.setDescription("");
		
		pizzaIngredients.add(dough);
		pizzaIngredients.add(tomatoSauce);
		pizzaIngredients.add(cheese);
		pizzaIngredients.add(pepperoni);
		
		pizzaRecipe.setDescription("A delicious pepperoni pizza");
		pizzaRecipe.setName("Pepperoni Pizza");
		pizzaRecipe.setIngredients(pizzaIngredients);
		
		return pizzaRecipe;
	}
    
}
