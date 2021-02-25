package binary.test.task.service;

import binary.test.task.model.Recipe;
import java.time.LocalDateTime;
import java.util.List;

public interface RecipeService {
    Recipe add(Recipe recipe);

    Recipe update(Recipe recipe, Long recipeId);

    Recipe fetchFromParent(Long parentRecipeId, Recipe recipe);

    List<Recipe> getAll();
}
