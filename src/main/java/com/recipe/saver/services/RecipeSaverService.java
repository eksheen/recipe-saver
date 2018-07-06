package com.recipe.saver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.recipe.saver.dao.RecipeSaverDao;
import com.recipe.saver.model.Recipe;

@Component
public class RecipeSaverService {
	@Autowired
	public RecipeSaverDao recipeSaverDao;
	
	public boolean postRecipe(Recipe recipe) {
		return recipeSaverDao.postRecipe(recipe);
	}
	
	public List<Recipe> getAllRecipes() {
		return recipeSaverDao.getAllRecipes();
	}
	
	public List<Recipe> getRecipesByName(String name) {
		return recipeSaverDao.getRecipesByName(name);
	}
	
	public boolean deleteRecipe(String name) {
		return recipeSaverDao.deleteRecipe(name);
	}
	
}
