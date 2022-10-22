package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domains.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient> {
    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
    }

    @Override
    @Synchronized
    @Nullable
    public Ingredient convert(IngredientCommand source) {
        if(source==null){
            return null;

        }
        final Ingredient target = new Ingredient();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setAmount(source.getAmount());
        target.setUnitOfMeasure(unitOfMeasureCommandToUnitOfMeasure.convert(source.getUnitOfMeasure()));
        return  target;
    }
}
