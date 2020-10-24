package com.recipe.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author smanickavasagam
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeResponseDTO {
	private int id;
	private String name;
	private String time;
	private String method;
	private String notes;
	private boolean isKidsRecipe;
	private String flavour;
	private String type;


	/**
	 * @return the id
	 */
	@JsonProperty(value = "RecipeId")
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@JsonProperty(value = "RecipeName")
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the time
	 */
	@JsonProperty(value = "PreparationTime")
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the method
	 */
	@JsonProperty(value = "Directions")
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the notes
	 */
	@JsonProperty(value = "Notes")
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the isKidsRecipe
	 */
	@JsonProperty(value = "KidsRecipe")
	public boolean isKidsRecipe() {
		return isKidsRecipe;
	}

	/**
	 * @param isKidsRecipe the isKidsRecipe to set
	 */
	public void setKidsRecipe(boolean isKidsRecipe) {
		this.isKidsRecipe = isKidsRecipe;
	}

	/**
	 * @return the flavour
	 */
	@JsonProperty(value = "Flavour")
	public String getFlavour() {
		return flavour;
	}

	/**
	 * @param flavour the flavour to set
	 */
	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}

	/**
	 * @return the type
	 */
	@JsonProperty(value = "RecipeType")
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
