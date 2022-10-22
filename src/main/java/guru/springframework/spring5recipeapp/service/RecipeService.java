package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.domains.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {

  Set<Recipe> getRecipies();
  Recipe findById(Long id);
  RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

  RecipeCommand findCommandById(Long valueOf);
}
