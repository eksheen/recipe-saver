package com.recipe.saver.model;

public class Ingredient {
	public String name;
	
	public Double measurement;
	
	public String unit;
	
	public String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Double measurement) {
		this.measurement = measurement;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
