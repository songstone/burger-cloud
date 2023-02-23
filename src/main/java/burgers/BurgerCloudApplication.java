package burgers;

import burgers.data.IngredientRepository;
import burgers.domain.Ingredient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static burgers.domain.Ingredient.*;

@SpringBootApplication
public class BurgerCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurgerCloudApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo) {
        return args -> {
             repo.save(new Ingredient("PLBN" ,"Plain Bun",    Type.WRAP));
             repo.save(new Ingredient("BABN", "Bagel Bun",    Type.WRAP));
             repo.save(new Ingredient("GRBF", "Ground Beef",  Type.PROTEIN));
             repo.save(new Ingredient("BACO", "Bacon",        Type.PROTEIN));
             repo.save(new Ingredient("TMTO", "Tomato",       Type.VEGGIES));
             repo.save(new Ingredient("LETC", "Lettuce",      Type.VEGGIES));
             repo.save(new Ingredient("CHED", "Cheddar",      Type.CHEESE));
             repo.save(new Ingredient("MOZZ", "Mozzarella",   Type.CHEESE));
             repo.save(new Ingredient("SLSA", "Salsa",        Type.SAUCE));
             repo.save(new Ingredient("SRCR", "Sour Cream",   Type.SAUCE));
        };
    }
}
