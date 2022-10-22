package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.command.CategoryCommand;
import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.command.NotesCommand;
import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.converter.CategoryCommandToCategory;
import guru.springframework.spring5recipeapp.converter.IngredientCommandToIngredient;
import guru.springframework.spring5recipeapp.converter.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.domains.Difficulty;
import guru.springframework.spring5recipeapp.domains.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {
    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;
    RecipeServiceImpl recipeService;
    @Mock
     RecipeRepository recipeRepository;
    private RecipeCommandToRecipe recipeCommandToRecipe;
    private CategoryCommandToCategory categoryCommandToCategory;
    private IngredientCommandToIngredient ingredientCommandToIngredient;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        recipeService=new RecipeServiceImpl(recipeCommandToRecipe, categoryCommandToCategory, ingredientCommandToIngredient, recipeRepository);
    }

    @Test
    void getRecipies() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipes);
        assertEquals(1,recipeService.getRecipies().size());
        verify(recipeRepository,times(1)).findAll();
        verify(recipeRepository,never()).findById(anyLong());
     }
    @Test
    void getRecipieByIDTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        Optional<Recipe> optionalRecipe=recipeRepository.findById(1L);
        assertNotNull(optionalRecipe.get()," Null Recipe returned");
        assertEquals(1L,optionalRecipe.get().getId());
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }

}