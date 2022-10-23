package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domains.Ingredient;
import guru.springframework.domains.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService{
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if(!recipeOptional.isPresent()){
            log.error("RecipeId "+recipeId+" is not present");
            return  null;
        }
        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> optionalIngredientCommand = recipe.getIngredients().stream().filter(ingredienttemp->
                ingredienttemp.getId().longValue()==ingredientId.longValue()).findFirst().map(ingredientToIngredientCommand::convert);
        if(!optionalIngredientCommand.isPresent()){
            log.error("IngredientId  "+ingredientId+" is not present");
            return  null;
        }
        return optionalIngredientCommand.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
       Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());
       if(ingredientCommand!=null && !recipeOptional.isPresent()){
           log.error("recipt id "+ingredientCommand.getRecipeId()+" is not present");
           return  new IngredientCommand();
       }
       Set<Long> existingIngredientList  = new HashSet<>();
       Recipe recipe = recipeOptional.get();
       Optional<Ingredient> ingredientOption = recipe.getIngredients().stream().filter(ingredientTemp -> ingredientTemp.getId()==ingredientCommand.getId() ).findFirst();
       if(ingredientOption.isPresent()){
            Ingredient ingredientFound = ingredientOption.get();
           ingredientFound.setDescription(ingredientCommand.getDescription());
           ingredientFound.setAmount(ingredientCommand.getAmount());
           ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                   .findById(ingredientCommand.getUnitOfMeasure().getId())
                   .orElseThrow(()->new RuntimeException("UnitOfMeasure not found")));

       }else {// if we are adding first ingredient to recipe
           Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
           // used for verification
           recipe.getIngredients().forEach(existingIngredient-> existingIngredientList.add(existingIngredient.getId()));
           recipe.addIngredient(ingredient);
           ingredient.setRecipe(recipe);

       }
        Recipe savedRecipe = recipeRepository.save(recipe);
        Optional<Ingredient> savedIngredientOption = savedRecipe.getIngredients().stream()
                    .filter(ingredientTemp -> ingredientTemp.getId()==ingredientCommand.getId() ).findFirst();
        if(savedIngredientOption.isPresent()){
            return ingredientToIngredientCommand.convert(savedIngredientOption.get());
        }

        // if saved as new Ingredient , then it wont matched with existing ids
        Optional<Ingredient> savedNewIngredientOption = savedRecipe.getIngredients().stream()
                .filter(ingredientTemp -> !(existingIngredientList.contains(ingredientTemp.getId())) ).findFirst();
        if(savedNewIngredientOption.isPresent()){
            return ingredientToIngredientCommand.convert(savedNewIngredientOption.get());
        }
        return new IngredientCommand();
    }

    @Override
    public void deleteById(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if(recipeOptional.isPresent()){
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOption = recipe.getIngredients().stream().filter(ingredientTemp -> ingredientTemp.getId()==ingredientId ).findFirst();
            if(ingredientOption.isPresent()){
                Ingredient ingredientToBeRemoved=ingredientOption.get();
                ingredientToBeRemoved.setRecipe(null);
                recipe.getIngredients().remove(ingredientToBeRemoved);
                recipeRepository.save(recipe);
            }else{
                log.error("ingredient id "+ingredientId+" is not present");
            }

        }else{
            log.error("recipt id "+recipeId+" is not present");


        }

    }


}
