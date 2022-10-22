package guru.springframework.services;

import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.domains.Difficulty;
import guru.springframework.domains.Recipe;
import guru.springframework.repositories.RecipeRepository;
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
    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        recipeService=new RecipeServiceImpl(recipeCommandToRecipe, recipeToRecipeCommand, recipeRepository);
    }

    @Test
    void getRecipiesTest() {
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
    @Test
    void getRecipiesCommanByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);
        RecipeCommand foundRecipeCommand=recipeService.findCommandById(1L);
        assertNotNull(foundRecipeCommand," Null Recipe returned");
        assertEquals(1L,foundRecipeCommand.getId());
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }
    @Test
    public void testDeleteById() throws Exception{
        //given
        Long id = 1L;
        //when
        recipeService.deleteById(1L);
        //then
        verify(recipeRepository,times(1)).deleteById(anyLong());
    }
}