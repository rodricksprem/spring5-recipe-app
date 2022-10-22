package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domains.Recipe;

import java.util.Set;

public interface RecipeService {

  Set<Recipe> getRecipies();
  Recipe findById(Long id);
  RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

  RecipeCommand findCommandById(Long valueOf);

  void deleteById(Long id);
}
