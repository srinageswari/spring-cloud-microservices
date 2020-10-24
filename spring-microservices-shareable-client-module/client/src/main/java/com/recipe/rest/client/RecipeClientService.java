package com.recipe.rest.client;

import org.springframework.http.ResponseEntity;

import com.recipe.client.dto.RecipeResponseDTO;


/**
 * @author smanickavasagam
 *
 */
public interface RecipeClientService {
	   public ResponseEntity<RecipeResponseDTO> getRecipies(String input);
}
