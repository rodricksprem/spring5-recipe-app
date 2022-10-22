package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domains.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {
    NotesToNotesCommand notesToNotesCommand;
    final String RECIPE_NOTES = "Notes";
    final Long ID= 1L;

    @BeforeEach
    void setUp() {
        notesToNotesCommand = new NotesToNotesCommand();

    }
    @Test
    void testNull() {
        assertNull(notesToNotesCommand.convert(null));

    }
    @Test
    void testEmptyObj() {
        assertNotNull(notesToNotesCommand.convert(new Notes()));

    }
    @Test
    void convert() {
        Notes notes  = new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(RECIPE_NOTES);
        NotesCommand notesCommand=notesToNotesCommand.convert(notes);
        assertNotNull(notesCommand);
        assertEquals(ID,notesCommand.getId());
        assertEquals(RECIPE_NOTES,notesCommand.getRecipeNotes());

    }
}