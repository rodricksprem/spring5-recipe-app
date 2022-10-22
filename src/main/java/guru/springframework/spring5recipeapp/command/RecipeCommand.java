package guru.springframework.spring5recipeapp.command;

import guru.springframework.spring5recipeapp.domains.Category;
import guru.springframework.spring5recipeapp.domains.Difficulty;
import guru.springframework.spring5recipeapp.domains.Ingredient;
import guru.springframework.spring5recipeapp.domains.Notes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Byte[] image;
    private NotesCommand notes;
    private Difficulty difficulty;

    private Set<IngredientCommand> ingredients=new HashSet<>();

    private Set<CategoryCommand> categories=new HashSet<>();

}
