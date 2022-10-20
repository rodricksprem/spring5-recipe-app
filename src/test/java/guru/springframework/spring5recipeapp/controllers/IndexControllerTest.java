package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.domains.Recipe;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerAdapter;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    IndexController indexController;
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    void getIndex() {
        Set<Recipe> recipes = new HashSet<>();

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);
        recipe.setId(2L);
        recipe = new Recipe();
        recipes.add(recipe);
        when(recipeService.getRecipies()).thenReturn(recipes);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        String viewName=indexController.getIndex(model);
        assertEquals("index",viewName);
        verify(recipeService,times(1)).getRecipies();
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Set<Recipe> returnedSet= argumentCaptor.getValue();
        assertEquals(2,returnedSet.size());
    }
}