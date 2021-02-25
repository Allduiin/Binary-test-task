package binary.test.task.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity(name = "recipes")
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCreated;
    private String description;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Recipe> children;
    @OneToMany
    private List<UnVersionedRecipe> versions;

    @Entity(name = "versions")
    @Data
    private static class UnVersionedRecipe {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private LocalDateTime dateCreated;
        private String description;
    }

    public boolean createAndAddUnVersionedRecipe() {
        UnVersionedRecipe unVersionedRecipe = new UnVersionedRecipe();
        unVersionedRecipe.dateCreated = dateCreated;
        unVersionedRecipe.description = description;
        versions.add(unVersionedRecipe);
        return true;
    }
}
