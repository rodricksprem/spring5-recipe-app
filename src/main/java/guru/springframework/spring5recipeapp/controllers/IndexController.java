package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.domains.Category;
import guru.springframework.spring5recipeapp.domains.UnitOfMeasure;
import guru.springframework.spring5recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Slf4j
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping({"","/","index"})
    public String getIndex(Model model){
        log.debug("inside getIndex method...");

        model.addAttribute("recipes",recipeService.getRecipies());

        return "index";
    }
}
