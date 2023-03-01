package burgers.controller;

import burgers.data.BurgerRepository;
import burgers.data.IngredientRepository;
import burgers.data.UserRepository;
import burgers.domain.Burger;
import burgers.domain.Ingredient;
import burgers.domain.Ingredient.Type;
import burgers.domain.Order;
import burgers.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignBurgerController {

    private final IngredientRepository ingredientRepo;

    private final BurgerRepository burgerRepo;

    private final UserRepository userRepo;

    @Autowired
    public DesignBurgerController(IngredientRepository ingredientRepo, BurgerRepository burgerRepo, UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.burgerRepo = burgerRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        for (Type type : Type.values()) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);

        return "design";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "burger")
    public Burger burger() {
        return new Burger();
    }

    @PostMapping
    public String processDesign(@Valid Burger design, Errors errors,
                                @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        Burger saved = burgerRepo.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }
}
