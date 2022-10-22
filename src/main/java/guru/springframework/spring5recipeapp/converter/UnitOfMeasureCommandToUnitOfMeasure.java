package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domains.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter< UnitOfMeasureCommand,UnitOfMeasure> {

    @Override
    @Nullable
    @Synchronized // for thread safe
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
       if(source==null) {
           return null;
       }
       final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
       unitOfMeasure.setId(source.getId());
       unitOfMeasure.setDescription(source.getDescription());
       return unitOfMeasure;
    }
}
