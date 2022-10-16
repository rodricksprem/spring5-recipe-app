package guru.springframework.spring5recipeapp.repositories;

import guru.springframework.spring5recipeapp.domains.Category;
import guru.springframework.spring5recipeapp.domains.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
}
