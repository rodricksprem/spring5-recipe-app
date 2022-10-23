package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domains.Ingredient;
import guru.springframework.domains.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {
    IngredientService ingredientService;
    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository, unitOfMeasureRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);
    }

    @Test
    void testFindRecipeIdAndIngredientIdHappyPath() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        //then

        IngredientCommand ingredientCommand = ingredientService.findRecipeIdAndIngredientId(1L,3L);
        assertNotNull(ingredientCommand);
        assertEquals(1L,ingredientCommand.getRecipeId());
        assertEquals(3L,ingredientCommand.getId());
        verify(recipeRepository,times(1)).findById(anyLong());

    }
    @Test
    void testSaveIngredientIdHappyPath()  throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(3L);
        ingredientCommand.setRecipeId(1L);
        Optional<Recipe> recipeOptional = Optional.of(new Recipe());
        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(1L);
        Ingredient newIngredient = new Ingredient();
        newIngredient.setId(3L);
        savedRecipe.addIngredient(newIngredient);

        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);
        //then

        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        assertNotNull(savedIngredientCommand);
        assertEquals(1L,savedIngredientCommand.getRecipeId());
        assertEquals(3L,savedIngredientCommand.getId());
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).save(any());

    }
    @Test
    void testDeleteIdHappyPath()  throws Exception {
        //given
       Recipe savedRecipe = new Recipe();
        savedRecipe.setId(1L);
        Ingredient newIngredient = new Ingredient();
        newIngredient.setId(3L);
        newIngredient.setRecipe(savedRecipe);
        savedRecipe.addIngredient(newIngredient);
        Optional<Recipe> recipeOptional = Optional.of(savedRecipe);
        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        when(recipeRepository.save(any())).thenReturn(savedRecipe);
        //then
        ingredientService.deleteById(Long.valueOf(1L),Long.valueOf(3L));

        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).save(any());

    }

}