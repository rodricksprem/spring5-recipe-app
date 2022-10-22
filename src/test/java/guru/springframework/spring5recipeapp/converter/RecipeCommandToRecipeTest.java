package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.CategoryCommand;
import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.command.NotesCommand;
import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.domains.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {
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
    RecipeCommandToRecipe recipeCommandToRecipe;
    @BeforeEach
    void setUp() {
        recipeCommandToRecipe = new RecipeCommandToRecipe(new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes(),
                new CategoryCommandToCategory());
    }
    @Test
    public void testNullObject() throws Exception {
        assertNull(recipeCommandToRecipe.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
    assertNotNull(recipeCommandToRecipe.convert(new RecipeCommand()));
    }
    @Test
    void convert() {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
    CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CAT_ID_1);
        recipeCommand.getCategories().add(categoryCommand);
        categoryCommand = new CategoryCommand();
        categoryCommand.setId(CAT_ID2);
        recipeCommand.getCategories().add(categoryCommand);
    NotesCommand notesCommand=new NotesCommand();
        notesCommand.setId(NOTES_ID);
    recipeCommand.setNotes(notesCommand);
    IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setId(INGRED_ID_1);
        recipeCommand.getIngredients().add(ingredientCommand);
        ingredientCommand=new IngredientCommand();
        ingredientCommand.setId(INGRED_ID_2);
        recipeCommand.getIngredients().add(ingredientCommand);
    Recipe recipe=recipeCommandToRecipe.convert(recipeCommand);
    assertNotNull(recipe);
    assertEquals(RECIPE_ID,recipe.getId());
    assertEquals(COOK_TIME,recipe.getCookTime());
    assertEquals(DESCRIPTION,recipe.getDescription());
    assertEquals(DIRECTIONS,recipe.getDirections());
    assertEquals(DIFFICULTY,recipe.getDifficulty());
    assertEquals(PREP_TIME,recipe.getPrepTime());
    assertEquals(SERVINGS,recipe.getServings());
    assertEquals(SOURCE,recipe.getSource());
    assertEquals(URL,recipe.getUrl());
    assertEquals(2,recipe.getIngredients().size());
    assertEquals(2,recipe.getCategories().size());
    }
}