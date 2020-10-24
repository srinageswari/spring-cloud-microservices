package com.recipe.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.client.dto.RecipeResponseDTO;
import com.recipe.data.RecipeRepository;
import com.recipe.model.Recipe;
import com.recipe.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smanickavasagam
 *
 */
@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private RecipeRepository recipeData;

	protected final ParameterizedTypeReference<RecipeResponseDTO> restApiResponseParameterizedTypeRef = new ParameterizedTypeReference<RecipeResponseDTO>() {
	};

	@PostMapping(value = "/addNewRecipe")
	public ResponseEntity<RecipeResponseDTO> add(@RequestBody Recipe recipe) {
		boolean recipeCreated = false;
		if (recipe != null)
			recipeCreated = recipeService.add(recipe);
		if (recipeCreated) {
			RecipeResponseDTO recipeResponseDTO = getRecipeResponseDTO(recipe);
			return new ResponseEntity<RecipeResponseDTO>(recipeResponseDTO, HttpStatus.OK);
		} else
			return new ResponseEntity<RecipeResponseDTO>(HttpStatus.CONFLICT);
	}

	@GetMapping(value = "/getRecipeDetailsByName/{name}")
	public ResponseEntity<RecipeResponseDTO> getRecipeByName(@PathVariable String name) {
		Recipe recipe = recipeService.getDetailsByName(name);
		if (recipe == null) {
			return ResponseEntity.notFound().build();
		}
		RecipeResponseDTO recipeResponseDTO = getRecipeResponseDTO(recipe);
		return ResponseEntity.ok().body(recipeResponseDTO);
	}

	@PatchMapping(value = "/updateRecipeDetails/{name}")
	public ResponseEntity<RecipeResponseDTO> updateRecipeByName(@PathVariable String name,
			@RequestBody Map<Object, Object> fields) {
		Recipe recipe = recipeService.getDetailsByName(name);
		final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
		final Recipe recipe1 = mapper.convertValue(fields, Recipe.class);
		if (fields != null) {
			for (Entry<Object, Object> entry : fields.entrySet()) {
				// use reflection to get field k on manager and set it to value k
				Field field;
				try {
					Field privateStringField = Recipe.class.getDeclaredField(String.valueOf(entry.getKey()));
					privateStringField.setAccessible(true);
					field = ReflectionUtils.findField(Recipe.class, privateStringField.getName());
					field.setAccessible(true);
					ReflectionUtils.setField(field, recipe, privateStringField.get(recipe1));
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		recipeData.save(recipe);
		RecipeResponseDTO recipeResponseDTO = getRecipeResponseDTO(recipe);
		return new ResponseEntity<RecipeResponseDTO>(recipeResponseDTO, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteRecipeDetailsByName/{name}")
	public void deleteRecipeByName(@PathVariable String name) {
		recipeService.delete(name);
	}

	@GetMapping(value = "/viewRecipeDetails")
	public List<RecipeResponseDTO> viewAllRecipeDetails() {
		List<Recipe> allRecipes = recipeService.view();
		RecipeResponseDTO recipeResponseDTO = null;
		List<RecipeResponseDTO> recipeResponseDTOLst = new ArrayList<RecipeResponseDTO>();
		for (Recipe recipe : allRecipes) {
			recipeResponseDTO = getRecipeResponseDTO(recipe);
			recipeResponseDTOLst.add(recipeResponseDTO);
		}
		return recipeResponseDTOLst;
	}

	public RecipeResponseDTO getRecipeResponseDTO(Recipe recipe) {
		RecipeResponseDTO recipeResponseDTO = new RecipeResponseDTO();
		recipeResponseDTO.setId(recipe.getId());
		recipeResponseDTO.setName(recipe.getName());
		recipeResponseDTO.setNotes(recipe.getNotes());
		recipeResponseDTO.setTime(recipe.getTime());
		recipeResponseDTO.setMethod(recipe.getMethod());
		recipeResponseDTO.setType(recipe.getType());
		recipeResponseDTO.setFlavour(recipe.getFlavour());
		recipeResponseDTO.setKidsRecipe(recipe.isKidsRecipe());
		return recipeResponseDTO;
	}

}