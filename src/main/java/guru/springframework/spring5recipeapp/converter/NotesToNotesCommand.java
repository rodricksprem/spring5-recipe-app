package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.NotesCommand;
import guru.springframework.spring5recipeapp.domains.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Override
    @Nullable
    @Synchronized // for thread safe
    public NotesCommand convert(Notes source) {
       if(source==null) {
           return null;
       }
       final  NotesCommand target = new NotesCommand();
       target.setId(source.getId());
       target.setRecipeNotes(source.getRecipeNotes());
       return target;
    }
}
