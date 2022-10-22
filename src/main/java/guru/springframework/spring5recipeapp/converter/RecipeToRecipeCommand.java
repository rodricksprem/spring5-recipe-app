package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.CategoryCommand;
import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.domains.Category;
import guru.springframework.spring5recipeapp.domains.Ingredient;
import guru.springframework.spring5recipeapp.domains.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe,RecipeCommand> {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final  NotesToNotesCommand notesToNotesCommand;
    private final  CategoryToCategoryCommand categoryToCategoryCommand;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientToIngredientCommand, NotesToNotesCommand notesToNotesCommand, CategoryToCategoryCommand categoryToCategoryCommand) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.notesToNotesCommand = notesToNotesCommand;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }


    @Override
    @Synchronized
    @Nullable
    public RecipeCommand convert(Recipe source) {
        if(source==null){
            return null;

        }
        final RecipeCommand target = new RecipeCommand();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setDifficulty(source.getDifficulty());
        target.setDirections(source.getDirections());
        target.setServings(source.getServings());
        target.setPrepTime(source.getPrepTime());
        target.setCookTime(source.getCookTime());
        target.setImage(source.getImage());
        target.setUrl(source.getUrl());
        target.setSource(source.getSource());
        target.setNotes(notesToNotesCommand.convert(source.getNotes()));
        if(source.getCategories()!=null && source.getCategories().size()>0){
            source.getCategories().forEach((Category category) -> target.getCategories().add(categoryToCategoryCommand.convert(category)));
        }
        if(source.getIngredients()!=null && source.getCategories().size()>0){
            source.getIngredients().forEach((Ingredient ingredient) -> target.getIngredients().add(ingredientToIngredientCommand.convert(ingredient)) );
        }
        return  target;
    }
}
