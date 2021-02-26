package binary.test.task.service;

import binary.test.task.model.Recipe;
import binary.test.task.model.Version;
import java.util.List;

public interface RecipeService {
    Recipe add(Recipe recipe);

    Recipe update(Recipe recipe);

    Recipe fetchFromParent(Long parentRecipeId, Recipe recipe);

    List<Recipe> getAll();

    Version getVersion(Recipe recipe);
}
