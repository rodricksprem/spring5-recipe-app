package guru.springframework.converters;

import guru.springframework.domains.UnitOfMeasure;
import guru.springframework.commands.UnitOfMeasureCommand;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter< UnitOfMeasureCommand, UnitOfMeasure> {

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
