package burgers.data;

import burgers.domain.Burger;

public interface BurgerRepository {
    Burger save(Burger burger);
}
