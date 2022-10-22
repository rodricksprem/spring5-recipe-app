package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.CategoryCommand;
import guru.springframework.spring5recipeapp.domains.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category,CategoryCommand> {

    @Override
    @Nullable
    @Synchronized // for thread safe
    public CategoryCommand convert(Category source) {
       if(source==null) {
           return null;
       }
       final  CategoryCommand target = new CategoryCommand();
       target.setId(source.getId());
       target.setDescription(source.getDescription());
       return target;
    }
}
