package burgers.data;

import burgers.domain.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findById(String id);
    Ingredient save(Ingredient ingredient);
}
