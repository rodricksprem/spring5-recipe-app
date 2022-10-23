package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domains.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public IngredientToIngredientCommand( UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;

    }

    @Override
    @Synchronized
    @Nullable
    public IngredientCommand convert(Ingredient source) {
        if(source==null){
            return null;

        }
        final IngredientCommand target = new IngredientCommand();
        target.setId(source.getId());
        if(source.getRecipe()!=null&& source.getRecipe().getId()!=null) {
            target.setRecipeId(source.getRecipe().getId());
        }
        target.setDescription(source.getDescription());
        target.setAmount(source.getAmount());

        target.setUnitOfMeasure(unitOfMeasureToUnitOfMeasureCommand.convert(source.getUnitOfMeasure()));
        return  target;
    }
}
