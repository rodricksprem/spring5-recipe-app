package guru.springframework.spring5recipeapp.repositories;

import guru.springframework.spring5recipeapp.domains.Category;
import guru.springframework.spring5recipeapp.domains.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
