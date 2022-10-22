package guru.springframework.converters;

import guru.springframework.domains.Category;
import guru.springframework.commands.CategoryCommand;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Override
    @Nullable
    @Synchronized // for thread safe
    public Category convert(CategoryCommand source) {
       if(source==null) {
           return null;
       }
       final  Category target = new Category();
       target.setId(source.getId());
       target.setDescription(source.getDescription());
       return target;
    }
}
