package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domains.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {
    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
    final String DESCRIPTION="description";
    final Long ID= 1L;

    @BeforeEach
    void setUp() {
        unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();

    }
    @Test
    void testNull() {
        assertNull(unitOfMeasureCommandToUnitOfMeasure.convert(null));

    }
    @Test
    void testEmptyObj() {
        assertNotNull(unitOfMeasureCommandToUnitOfMeasure.convert(new UnitOfMeasureCommand()));

    }
    @Test
    void convert() {
        UnitOfMeasureCommand unitOfMeasureCommand  = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID);
        unitOfMeasureCommand.setDescription(DESCRIPTION);
        UnitOfMeasure unitOfMeasure=unitOfMeasureCommandToUnitOfMeasure.convert(unitOfMeasureCommand);
        assertNotNull(unitOfMeasure);
        assertEquals(ID,unitOfMeasure.getId());
        assertEquals(DESCRIPTION,unitOfMeasure.getDescription());

    }
}