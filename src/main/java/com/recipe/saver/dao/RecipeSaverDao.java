package com.recipe.saver.dao;

import static org.assertj.core.api.Assertions.in;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.recipe.saver.model.Ingredient;
import com.recipe.saver.model.Recipe;

@Component
public class RecipeSaverDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final String INSERT_RECIPE = "INSERT INTO recipes VALUES (:RecipeID, :Name, :Description);";

//	private static final String INSERT_RECIPE = "INESRT INTO recipes VALUES (0, pizza, yum)";
	
	private static final String INSERT_INGREDIENT = "INSERT INTO ingredients (RecipeID, Name, Description, Measurement, Unit) VALUES (:recipeID, :name,:description,:measurement,:unit)";
	
	private static final String GET_ALL_RECIPES = "SELECT * recipes";
	
	public boolean postRecipe(Recipe recipe) {
		boolean success = false;		
		Map<String, Object> recipeParamMap = new HashMap<String, Object>();
		recipeParamMap.put("RecipeID", recipe.getRecipeID());
		recipeParamMap.put("Name", recipe.getName());
		recipeParamMap.put("Description",recipe.getDescription());
		
		System.out.println(recipe.getRecipeID());
		System.out.println(recipe.getName());
		System.out.println(recipe.getDescription());
		
		System.out.println(recipeParamMap.toString());
		
		namedParameterJdbcTemplate.update(INSERT_RECIPE, recipeParamMap);
			
		Map<String, Object> ingredientParamMap = new HashMap<String, Object>();
		for(Ingredient ingredient : recipe.getIngredients()) {
			System.out.println(ingredient.getRecipeID());
			System.out.println(ingredient.getName());
			System.out.println(ingredient.getDescription());
			System.out.println(ingredient.getMeasurement());
			System.out.println(ingredient.getUnit());
			
			ingredientParamMap.put("recipeID", recipe.getRecipeID());
			ingredientParamMap.put("name", ingredient.getName());
			ingredientParamMap.put("description", ingredient.getDescription());
			ingredientParamMap.put("measurement", ingredient.getMeasurement());
			ingredientParamMap.put("unit", ingredient.getUnit());
			
			System.out.println(ingredientParamMap.toString());
			
			namedParameterJdbcTemplate.update(INSERT_INGREDIENT, ingredientParamMap);
			
			ingredientParamMap.clear();
		}	
		success = true;
		return success;
	}
    
    
	
	
}
