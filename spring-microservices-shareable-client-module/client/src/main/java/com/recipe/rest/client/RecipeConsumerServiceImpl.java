package com.recipe.rest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.recipe.client.dto.RecipeResponseDTO;

/**
 * @author smanickavasagam
 *
 */
@Component
public class RecipeConsumerServiceImpl {

	@Autowired
	RecipeClientService recipeClientService;

	public ResponseEntity<RecipeResponseDTO> getRecipies(String input) {
		ResponseEntity<RecipeResponseDTO> recipeResponse = recipeClientService.getRecipies(input);
		return recipeResponse;
	}
}
