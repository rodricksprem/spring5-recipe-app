package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domains.Ingredient;

public interface IngredientService {
    IngredientCommand findRecipeIdAndIngredientId(Long recipeId,Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    void deleteById(Long recipeId,Long ingredientId);
}
