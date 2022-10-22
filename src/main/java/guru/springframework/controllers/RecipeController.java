package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domains.Recipe;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){
        Recipe recipe = recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe",recipe);
        return "recipe/show";

    }

    @RequestMapping("/recipe/new")
    public String newRecipe( Model model){
       model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeform";

    }

    @PostMapping("recipe")

    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:"+savedRecipeCommand.getId()+"/show";

    }


    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));

        model.addAttribute("recipe",recipeCommand);
        return "recipe/recipeform";

    }
    @RequestMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id, Model model){
        recipeService.deleteById(Long.valueOf(id));
        log.debug(" Recipe id "+id +"got deleted");


        return "redirect:/";

    }
}
