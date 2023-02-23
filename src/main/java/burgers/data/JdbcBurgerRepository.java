package burgers.data;

import burgers.domain.Burger;
import burgers.domain.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Arrays;

@Repository
public class JdbcBurgerRepository implements BurgerRepository {

    private JdbcTemplate jdbc;

    public JdbcBurgerRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Burger save(Burger burger) {
        long burgerId = saveBurgerInfo(burger);
        burger.setId(burgerId);
        for (Ingredient ingredient : burger.getIngredients()) {
            saveIngredient(ingredient, burgerId);
        }
        return burger;
    }

    private void saveIngredient(Ingredient ingredient, long burgerId) {
        jdbc.update(
        "insert into BURGER_INGREDIENTS (burger, ingredient) " +
            "values (?, ?)",
            burgerId, ingredient.getId()
        );

    }

    private long saveBurgerInfo(Burger burger) {
        burger.setCreatedAt(LocalDateTime.now());
        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
            "insert into BURGER(name, createdAt) values (?, ?)",
            Types.VARCHAR, Types.TIMESTAMP
        ).newPreparedStatementCreator(
            Arrays.asList(
                burger.getName(),
                burger.getCreatedAt()
            )
        );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
