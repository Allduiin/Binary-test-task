package binary.test.task.service.impl;

import binary.test.task.model.Recipe;
import binary.test.task.model.Version;
import binary.test.task.repository.RecipeRepository;
import binary.test.task.repository.VersionRepository;
import binary.test.task.service.RecipeService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final VersionRepository versionRepository;

    @Override
    public Recipe add(Recipe recipe) {
        recipe.setDateCreated(LocalDateTime.now());
        recipe.setChildren(new ArrayList<>());
        recipe.setVersions(new ArrayList<>());
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe update(Recipe recipe) {
        Recipe recipeFromDb = recipeRepository.getOne(recipe.getId());
        Version version = getVersion(recipeFromDb);
        versionRepository.save(version);
        recipe.getVersions().add(version);
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe fetchFromParent(Long parentRecipeId, Recipe recipe) {
        recipe = add(recipe);
        Recipe parentRecipe = recipeRepository.getOne(parentRecipeId);
        parentRecipe.getChildren().add(recipe);
        recipeRepository.save(parentRecipe);
        return recipe;
    }

    @Override
    public List<Recipe> getAll() {
        return recipeRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public Version getVersion(Recipe recipe) {
        Version version = new Version();
        version.setName(recipe.getName());
        version.setDateCreated(recipe.getDateCreated());
        version.setDescription(recipe.getDescription());
        return version;
    }
}
