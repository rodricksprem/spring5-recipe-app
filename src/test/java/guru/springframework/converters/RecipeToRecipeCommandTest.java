package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domains.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {
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
    RecipeToRecipeCommand recipeToRecipeCommand;
    @BeforeEach
    void setUp() {
        recipeToRecipeCommand = new RecipeToRecipeCommand(new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand(),
                new CategoryToCategoryCommand());
    }
    @Test
    public void testNullObject() throws Exception {
        assertNull(recipeToRecipeCommand.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(recipeToRecipeCommand.convert(new Recipe()));
    }
    @Test
    void convert() {
    Recipe recipe = new Recipe();
    recipe.setId(RECIPE_ID);
    recipe.setCookTime(COOK_TIME);
    recipe.setDirections(DIRECTIONS);
    recipe.setDescription(DESCRIPTION);
    recipe.setDifficulty(DIFFICULTY);
    recipe.setPrepTime(PREP_TIME);
    recipe.setServings(SERVINGS);
    recipe.setSource(SOURCE);
    recipe.setUrl(URL);
    Category category = new Category();
    category.setId(CAT_ID_1);
    recipe.getCategories().add(category);
    category = new Category();
    category.setId(CAT_ID2);
    recipe.getCategories().add(category);
    Notes notes=new Notes();
    notes.setId(NOTES_ID);
    recipe.setNotes(notes);
    Ingredient ingredient = new Ingredient();
    ingredient.setId(INGRED_ID_1);
    recipe.getIngredients().add(ingredient);
    ingredient=new Ingredient();
    ingredient.setId(INGRED_ID_2);
    recipe.getIngredients().add(ingredient);
    RecipeCommand recipeCommand=recipeToRecipeCommand.convert(recipe);
    assertNotNull(recipeCommand);
    assertEquals(RECIPE_ID,recipeCommand.getId());
    assertEquals(COOK_TIME,recipeCommand.getCookTime());
    assertEquals(DESCRIPTION,recipeCommand.getDescription());
    assertEquals(DIRECTIONS,recipeCommand.getDirections());
    assertEquals(DIFFICULTY,recipeCommand.getDifficulty());
    assertEquals(PREP_TIME,recipeCommand.getPrepTime());
    assertEquals(SERVINGS,recipeCommand.getServings());
    assertEquals(SOURCE,recipeCommand.getSource());
    assertEquals(URL,recipeCommand.getUrl());
    assertEquals(2,recipeCommand.getIngredients().size());
    assertEquals(2,recipeCommand.getCategories().size());
    }
}