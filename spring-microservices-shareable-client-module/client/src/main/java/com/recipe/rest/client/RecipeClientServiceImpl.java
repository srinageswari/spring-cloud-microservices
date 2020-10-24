package com.recipe.rest.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.recipe.client.dto.RecipeResponseDTO;

/**
 * @author smanickavasagam
 *
 */
@Component
public class RecipeClientServiceImpl implements RecipeClientService {

	private RestTemplate restTemplate = new RestTemplate();
	protected final ParameterizedTypeReference<RecipeResponseDTO> restApiResponseParameterizedTypeRef = new ParameterizedTypeReference<RecipeResponseDTO>() {
	};

	@Value("${application.rest.protocol:http}")
	protected String APPLICATION_REST_PROTOCOL;

	private String SERVICE_NAME = "/api/recipe";

	protected String getBaseURL() {
		return APPLICATION_REST_PROTOCOL + "://" + "localhost" + ":" + "9002" + SERVICE_NAME;
	}

	@Override
	public ResponseEntity<RecipeResponseDTO> getRecipies(String name) {
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(getBaseURL() + "/getRecipeDetailsByName/{name}");
		ResponseEntity<RecipeResponseDTO> recipeResponse = restTemplate.exchange(builder.build(false).toUriString(),
				HttpMethod.GET, null, restApiResponseParameterizedTypeRef, name);
		return recipeResponse;
	}

}
