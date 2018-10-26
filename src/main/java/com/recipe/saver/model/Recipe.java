package com.recipe.saver.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Recipe {
	
  @Id
  @GeneratedValue
	public String recipeID;
	
	public String name;
	
	public String description;
	
	public List<Ingredient> ingredients;
	
	public List<String> Steps;

	public String getRecipeID() {
		return recipeID;
	}

	public void setRecipeID(String recipeID) {
		this.recipeID = recipeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getSteps() {
		return Steps;
	}

	public void setSteps(List<String> steps) {
		Steps = steps;
	}
}
