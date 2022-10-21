package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.domains.Recipe;
import guru.springframework.spring5recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @RequestMapping("/recipe/show/{id}")
    public String showRecipeID(@PathVariable String id, Model model){
        Recipe recipe = recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe",recipe);
        return "recipe/show";

    }
}
