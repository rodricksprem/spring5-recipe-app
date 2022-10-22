package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import  static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


class IngredientControllerTest {
    IngredientController ingredientController;
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;
    MockMvc mockMvc;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredientController = new IngredientController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    void testListIngredients() throws  Exception{
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        when(recipeService.findCommandById(1L)).thenReturn(recipeCommand);
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));
    }
}