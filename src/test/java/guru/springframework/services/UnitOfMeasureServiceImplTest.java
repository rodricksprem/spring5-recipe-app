package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domains.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnitOfMeasureServiceImplTest {
    UnitOfMeasureService unitOfMeasureService;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    //init converters
    public UnitOfMeasureServiceImplTest() {
        this.unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
     }
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository,unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void listAllUoms() {
        Set<UnitOfMeasure> unitOfMeasureSet = new HashSet<>();
        //given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(1L);
        unitOfMeasureSet.add(unitOfMeasure );
        unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(2L);
        unitOfMeasureSet.add(unitOfMeasure);
        //when
        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasureSet);

        //then
        Set<UnitOfMeasureCommand> unitOfMeasureCommandsSet =unitOfMeasureService.listAllUoms();
        verify(unitOfMeasureRepository,times(1)).findAll();
        assertNotNull(unitOfMeasureCommandsSet);
        assertEquals(2, unitOfMeasureSet.size());
        assertEquals(2, unitOfMeasureCommandsSet.size());
    }
}