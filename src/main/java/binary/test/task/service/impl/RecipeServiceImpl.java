package binary.test.task.service.impl;

import binary.test.task.model.Recipe;
import binary.test.task.repository.RecipeRepository;
import binary.test.task.service.RecipeService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Override
    public Recipe add(Recipe recipe) {
        recipe.setDateCreated(LocalDateTime.now());
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe update(Recipe recipe, Long recipeId) {
        Recipe recipeFromDb = recipeRepository.getOne(recipeId);
        recipeFromDb.createAndAddUnVersionedRecipe();
        recipeFromDb.setDescription(recipe.getDescription());
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe fetchFromParent(Long parentRecipeId, Recipe recipe) {
        Recipe parentRecipe = recipeRepository.getOne(parentRecipeId);
        parentRecipe.getChildren().add(recipe);
        recipeRepository.save(parentRecipe);
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> getAll() {
        return recipeRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
}
