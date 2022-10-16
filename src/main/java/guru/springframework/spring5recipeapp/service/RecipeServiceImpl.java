package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.domains.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipies() {
        Set<Recipe> recipes= new HashSet<>();
        //The double colon (::) operator, also known as method reference operator in Java, is used to call a method by referring to it with the help of its class directly
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }
}
