package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.CategoryCommand;
import guru.springframework.spring5recipeapp.command.NotesCommand;
import guru.springframework.spring5recipeapp.domains.Category;
import guru.springframework.spring5recipeapp.domains.Notes;
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