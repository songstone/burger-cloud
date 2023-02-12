package burgers.controller;

import burgers.domain.Burger;
import burgers.domain.Ingredient;
import burgers.domain.Ingredient.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static burgers.domain.Ingredient.Type.*;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignBurgerController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("PLBN" ,"Plain Bun", WRAP),
            new Ingredient("BABN", "Bagel Bun", WRAP),
            new Ingredient("GRBF", "Ground Beef", PROTEIN),
            new Ingredient("BACO", "Bacon", PROTEIN),
            new Ingredient("TMTO", "Tomato", VEGGIES),
            new Ingredient("LETC", "Lettuce", VEGGIES),
            new Ingredient("CHED", "Cheddar", CHEESE),
            new Ingredient("MOZZ", "Mozzarella", CHEESE),
            new Ingredient("SLSA", "Salsa", SAUCE),
            new Ingredient("SRCR", "Sour Cream", SAUCE)
        );

        for (Type type : Type.values()) {
            model.addAttribute(type.toString().toLowerCase(),
                filterByType(ingredients, type));
        }

        model.addAttribute("burger", new Burger());

        return "design";
    }
    private List<Ingredient> filterByType(
        List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(@Valid Burger design, Errors errors) {

        if (errors.hasErrors()) {
            return "design";
        }
        // TODO 내역 저장 로직 구현
        log.info("Processing design: " + design);

        return "redirect:/orders/current";
    }
}
