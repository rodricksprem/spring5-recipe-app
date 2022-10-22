package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.CategoryCommand;
import guru.springframework.spring5recipeapp.command.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domains.Category;
import guru.springframework.spring5recipeapp.domains.UnitOfMeasure;
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