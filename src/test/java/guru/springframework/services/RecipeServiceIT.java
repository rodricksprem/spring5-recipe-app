package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domains.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeServiceIT {
    public final String NEW_DESCRIPTON= "new description";
    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeService recipeService;

    @BeforeEach
    public void setup() {

    }

    @Test
    @Transactional
    public void testSaveOfDescription() throws Exception{
        Set<Recipe> recipes= new HashSet<>();
        recipeRepository.findAll().forEach(recipes::add);
        Recipe recipe = recipes.iterator().next();
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);
        recipeCommand.setDescription(NEW_DESCRIPTON);
        RecipeCommand savedRecipeCommand =recipeService.saveRecipeCommand(recipeCommand);
        assertEquals(NEW_DESCRIPTON,savedRecipeCommand.getDescription());
        assertEquals(recipe.getId(),savedRecipeCommand.getId());
        assertEquals(recipe.getCategories().size(),savedRecipeCommand.getCategories().size());
        assertEquals(recipe.getIngredients().size(),savedRecipeCommand.getIngredients().size());



    }


}
