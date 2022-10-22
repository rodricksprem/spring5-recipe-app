package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domains.Notes;
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
