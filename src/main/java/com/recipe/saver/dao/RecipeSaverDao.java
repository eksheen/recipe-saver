package com.recipe.saver.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.recipe.saver.model.Ingredient;
import com.recipe.saver.model.Recipe;

@Component
public class RecipeSaverDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final String INSERT_RECIPE = "INSERT INTO recipes VALUES (:RecipeID, :Name, :Description);";
	
	private static final String INSERT_INGREDIENT = "INSERT INTO ingredients (RecipeID, Name, Description, Measurement, Unit) VALUES (:recipeID, :name,:description,:measurement,:unit)";
	
	private static final String GET_ALL_RECIPES = "SELECT * FROM recipes";
	
	private static final String GET_RECIPE_BY_NAME = "SELECT * FROM recipes where name = :name";
	
	private static final String DELETE_RECIPE_BY_NAME = "DELETE FROM recipes where name = :name";
	
	private static final String GET_INGREDIENTS_BY_RECIPE = "SELECT * FROM ingredients WHERE recipeID = :recipeID ";
	
	
	
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
	
	public List<Recipe> getAllRecipes() {
		List<Recipe> allRecipes = new ArrayList<Recipe>();
		allRecipes = namedParameterJdbcTemplate.query(GET_ALL_RECIPES, new RecipeRowMapper());
		populateIngredients(allRecipes);
		return allRecipes;
	}
	
	public List<Recipe> getRecipesByName(String name) {
		List<Recipe> recipesByName = new ArrayList<Recipe>();
		Map<String, Object> recipeParamMap = new HashMap<String, Object>();
		recipeParamMap.put("name", name);
		recipesByName = namedParameterJdbcTemplate.query(GET_RECIPE_BY_NAME, recipeParamMap, new RecipeRowMapper());
		populateIngredients(recipesByName);
		return recipesByName;
	}
	
	public boolean deleteRecipe(String name) {
		Map<String, Object> recipeParamMap = new HashMap<String, Object>();
		recipeParamMap.put("name", name);
		int rowsAffected = namedParameterJdbcTemplate.update(DELETE_RECIPE_BY_NAME, recipeParamMap);
		if(rowsAffected > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Helper Method
	 * @author eksheen
	 *
	 */
	public List<Recipe> populateIngredients(List<Recipe> recipes) {
		for(Recipe recipe : recipes) {
			List<Ingredient> ingredients = new ArrayList<Ingredient>();
			Map<String, Object> ingredientParamMap = new HashMap<String, Object>();	
			ingredients.clear();
			ingredientParamMap.put("recipeID", recipe.getRecipeID());
			ingredients = namedParameterJdbcTemplate.query(GET_INGREDIENTS_BY_RECIPE, ingredientParamMap, new IngredientRowMapper());
			ingredientParamMap.clear();
			recipe.setIngredients(ingredients);
		}
		return recipes;
	}
    
	private class RecipeRowMapper implements RowMapper<Recipe> {
		/**
		 * Row Mapper for Shipper object for linking shipper numbers
		 *
		 * @return Shipper object
		 */
		@Override
		public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
			Recipe recipe = new Recipe();
			recipe.setRecipeID(rs.getInt("RecipeID"));
			recipe.setName(rs.getString("Name"));
			recipe.setDescription(rs.getString("Description"));

			return recipe;
		}
	}
	
	private class IngredientRowMapper implements RowMapper<Ingredient> {
		/**
		 * Row Mapper for Shipper object for linking shipper numbers
		 *
		 * @return Shipper object
		 */
		@Override
		public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
			Ingredient ingredient = new Ingredient();
			ingredient.setRecipeID(rs.getInt("RecipeID"));
			ingredient.setName(rs.getString("Name"));
			ingredient.setDescription(rs.getString("Description"));
			ingredient.setMeasurement(rs.getDouble("Measurement"));
			ingredient.setUnit(rs.getString("Unit"));
			return ingredient;
		}
	}
	
	
}
