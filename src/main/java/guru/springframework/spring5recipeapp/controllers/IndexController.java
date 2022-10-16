package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.domains.Category;
import guru.springframework.spring5recipeapp.domains.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private final CategoryRepository categoryRepository;
    private  final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @RequestMapping({"","/","index"})
    public String getIndex(){
        Optional<Category> categoryFound=categoryRepository.findByDescription("American1");

        Optional<UnitOfMeasure> unitOfMeasureFound=unitOfMeasureRepository.findByDescription("Teaspoon");
        if(categoryFound.isPresent()) {
            System.out.println(" Category Id is " + categoryFound.get().getId());
        }
        if(unitOfMeasureFound.isPresent()) {
            System.out.println(" Unit of Measure Id is " + unitOfMeasureFound.get().getId());
        }
        return "index";
    }
}
