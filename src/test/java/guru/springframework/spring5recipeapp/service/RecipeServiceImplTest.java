package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.domains.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;
    @Mock
     RecipeRepository recipeRepository;
@BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipies() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipes);
        assertEquals(1,recipeService.getRecipies().size());
        verify(recipeRepository,times(1)).findAll();
        verify(recipeRepository,never()).findById(anyLong());
     }
    @Test
    void getRecipieByIDTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        Optional<Recipe> optionalRecipe=recipeRepository.findById(1L);
        assertNotNull(optionalRecipe.get()," Null Recipe returned");
        assertEquals(1,recipeService.getRecipies().size());
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }
}