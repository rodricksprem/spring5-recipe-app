package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.converter.CategoryCommandToCategory;
import guru.springframework.spring5recipeapp.converter.IngredientCommandToIngredient;
import guru.springframework.spring5recipeapp.converter.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.domains.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService{
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private  final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeCommandToRecipe recipeCommandToRecipe, CategoryCommandToCategory categoryCommandToCategory, IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository) {
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipies() {
        Set<Recipe> recipes= new HashSet<>();
        log.debug("Inside getRecipies....");
        //The double colon (::) operator, also known as method reference operator in Java, is used to call a method by referring to it with the help of its class directly
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(recipeOptional.isPresent()){
           return recipeOptional.get();
        }else{
            throw new RuntimeException("Id Not Found");
        }
    }

    @Override
    @Transactional
    public Recipe saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe savedRecipe=null;
        Recipe detachedRecipe=recipeCommandToRecipe.convert(recipeCommand);
        savedRecipe=recipeRepository.save(detachedRecipe);
        log.debug(" Saved Recipe Id: "+savedRecipe.getId());
        return savedRecipe;
    }

}
