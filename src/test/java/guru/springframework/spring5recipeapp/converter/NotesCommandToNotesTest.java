package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.NotesCommand;
import guru.springframework.spring5recipeapp.domains.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {
    NotesCommandToNotes notesCommandToNotes;
    final String RECIPE_NOTES = "Notes";
    final Long ID= 1L;

    @BeforeEach
    void setUp() {
        notesCommandToNotes = new NotesCommandToNotes();

    }
    @Test
    void testNull() {
        assertNull(notesCommandToNotes.convert(null));

    }
    @Test
    void testEmptyObj() {
        assertNotNull(notesCommandToNotes.convert(new NotesCommand()));

    }
    @Test
    void convert() {
        NotesCommand notesCommand  = new NotesCommand();
        notesCommand.setId(ID);
        notesCommand.setRecipeNotes(RECIPE_NOTES);
        Notes notes=notesCommandToNotes.convert(notesCommand);
        assertNotNull(notes);
        assertEquals(ID,notes.getId());
        assertEquals(RECIPE_NOTES,notes.getRecipeNotes());

    }
}