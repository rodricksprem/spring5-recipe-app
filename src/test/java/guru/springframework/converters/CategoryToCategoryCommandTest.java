package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domains.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {
    CategoryToCategoryCommand categoryToCategoryCommand;
    final String DESCRIPTION="description";
    final Long ID= 1L;

    @BeforeEach
    void setUp() {
        categoryToCategoryCommand = new CategoryToCategoryCommand();

    }
    @Test
    void testNull() {
        assertNull(categoryToCategoryCommand.convert(null));

    }
    @Test
    void testEmptyObj() {
        assertNotNull(categoryToCategoryCommand.convert(new Category()));

    }
    @Test
    void convert() {
        Category category  = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);
        CategoryCommand categoryCommand=categoryToCategoryCommand.convert(category);
        assertNotNull(categoryCommand);
        assertEquals(ID,categoryCommand.getId());
        assertEquals(DESCRIPTION,categoryCommand.getDescription());

    }
}