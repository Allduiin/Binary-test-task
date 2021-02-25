package binary.test.task.repository;

import binary.test.task.model.ChildRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<ChildRecipe, Long> {
}
