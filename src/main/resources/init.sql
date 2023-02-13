delete from BURGER_ORDER_BURGERS;
delete from BURGER_INGREDIENTS;
delete from BURGER;
delete from BURGER_ORDER;
delete from INGREDIENT;

INSERT INTO INGREDIENT(id, name, type) values ("PLBN" ,"Plain Bun", WRAP);
INSERT INTO INGREDIENT(id, name, type) values ("BABN", "Bagel Bun", WRAP);
INSERT INTO INGREDIENT(id, name, type) values ("GRBF", "Ground Beef", PROTEIN);
INSERT INTO INGREDIENT(id, name, type) values ("BACO", "Bacon", PROTEIN);
INSERT INTO INGREDIENT(id, name, type) values ("TMTO", "Tomato", VEGGIES);
INSERT INTO INGREDIENT(id, name, type) values ("LETC", "Lettuce", VEGGIES);
INSERT INTO INGREDIENT(id, name, type) values ("CHED", "Cheddar", CHEESE);
INSERT INTO INGREDIENT(id, name, type) values ("MOZZ", "Mozzarella", CHEESE);
INSERT INTO INGREDIENT(id, name, type) values ("SLSA", "Salsa", SAUCE);
INSERT INTO INGREDIENT(id, name, type) values ("SRCR", "Sour Cream", SAUCE);