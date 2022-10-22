package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domains.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {
    CategoryCommandToCategory categoryCommandToCategory;
    final String DESCRIPTION="description";
    final Long ID= 1L;

    @BeforeEach
    void setUp() {
        categoryCommandToCategory = new CategoryCommandToCategory();

    }
    @Test
    void testNull() {
        assertNull(categoryCommandToCategory.convert(null));

    }
    @Test
    void testEmptyObj() {
        assertNotNull(categoryCommandToCategory.convert(new CategoryCommand()));

    }
    @Test
    void convert() {
        CategoryCommand categoryCommand  = new CategoryCommand();
        categoryCommand.setId(ID);
        categoryCommand.setDescription(DESCRIPTION);
        Category category=categoryCommandToCategory.convert(categoryCommand);
        assertNotNull(category);
        assertEquals(ID,category.getId());
        assertEquals(DESCRIPTION,category.getDescription());

    }
}