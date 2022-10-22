package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domains.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final  NotesCommandToNotes notesCommandToNotes;
    private final  CategoryCommandToCategory categoryCommandToCategory;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientCommandToIngredient, NotesCommandToNotes notesCommandToNotes, CategoryCommandToCategory categoryCommandToCategory) {
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.notesCommandToNotes = notesCommandToNotes;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }

    @Synchronized
    @Override
    @Nullable
    public Recipe convert(RecipeCommand source) {
        if(source==null){
            return null;

        }
        final Recipe target = new Recipe();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setDifficulty(source.getDifficulty());
        target.setDirections(source.getDirections());
        target.setServings(source.getServings());
        target.setPrepTime(source.getPrepTime());
        target.setCookTime(source.getCookTime());
        target.setUrl(source.getUrl());
        target.setSource(source.getSource());
        target.setNotes(notesCommandToNotes.convert(source.getNotes()));
        if(source.getCategories()!=null && source.getCategories().size()>0){
            source.getCategories().forEach((CategoryCommand categoryCommand) -> target.getCategories().add(categoryCommandToCategory.convert(categoryCommand)));
        }
        if(source.getIngredients()!=null && source.getIngredients().size()>0){
            source.getIngredients().forEach((IngredientCommand ingredientCommand) -> target.getIngredients().add(ingredientCommandToIngredient.convert(ingredientCommand)) );
        }
        return  target;
    }
}
