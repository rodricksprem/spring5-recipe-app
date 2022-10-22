package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.command.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domains.Ingredient;
import guru.springframework.spring5recipeapp.domains.UnitOfMeasure;
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
        target.setDescription(source.getDescription());
        target.setAmount(source.getAmount());

        target.setUnitOfMeasure(unitOfMeasureToUnitOfMeasureCommand.convert(source.getUnitOfMeasure()));
        return  target;
    }
}
