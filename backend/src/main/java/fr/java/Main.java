package fr.java;

import fr.java.api.dto.CreateOrderRequest;
import fr.java.api.dto.CreateOrderResponse;
import fr.java.data.MySqlStore;
import fr.java.model.Category;
import fr.java.model.Dish;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        MySqlStore store = new MySqlStore();

        Javalin app = Javalin.create(config -> {
            // CORS Javalin 6
            config.bundledPlugins.enableCors(cors -> cors.addRule(it -> it.anyHost()));
        });

        // check
        app.get("/", ctx -> ctx.result("API OK"));

        // GET /categories
        app.get("/categories", ctx -> {
            List<Category> cats = store.getCategories();

            // on renvoie un JSON simple {id,name}
            List<Object> out = new ArrayList<>();
            for (Category c : cats) {
                out.add(new SimpleCategory(c.id, c.name));
            }
            ctx.json(out);
        });

        // GET /categories/:id/dishes
        app.get("/categories/{id}/dishes", ctx -> {
            int categoryId = Integer.parseInt(ctx.pathParam("id"));
            List<Dish> dishes = store.getDishesByCategory(categoryId);
            ctx.json(dishes); // Dish Modelio est déjà OK 
        });

        // POST /orders
        app.post("/orders", ctx -> {
            CreateOrderRequest req = ctx.bodyAsClass(CreateOrderRequest.class);

            if (req.customerRef == null || req.customerRef.trim().isEmpty()) {
                ctx.status(400).result("customerRef required");
                return;
            }
            if (req.items == null || req.items.isEmpty()) {
                ctx.status(400).result("items required");
                return;
            }

            List<MySqlStore.OrderLine> lines = new ArrayList<>();

            for (CreateOrderRequest.Item it : req.items) {
                int dishId = it.dishId;
                int qty = Math.max(1, it.quantity);

                Dish dish = store.getDishById(dishId);
                if (dish == null) {
                    ctx.status(400).result("Unknown dishId: " + dishId);
                    return;
                }

                MySqlStore.OrderLine line = new MySqlStore.OrderLine();
                line.dishId = dishId;
                line.quantity = qty;
                line.unitPrice = dish.price;

                // options
                if (it.options != null) {
                    for (String s : it.options) {
                        if (s == null) continue;
                        String t = s.trim();
                        if (t.isEmpty()) continue;

                        String[] parts = t.split(":", 2);
                        if (parts.length == 2) {
                            line.options.add(new MySqlStore.Option(parts[0].trim(), parts[1].trim()));
                        } else {
                            // si pas de ":", on stocke en name=option value=""
                            line.options.add(new MySqlStore.Option(t, ""));
                        }
                    }
                }

                lines.add(line);
            }

            int orderId = store.createOrder(req.customerRef.trim(), lines);
            ctx.json(new CreateOrderResponse(orderId));
        });

        app.start(7000);
        System.out.println("Backend running on http://localhost:7000");
    }

    // petit DTO interne pour éviter d'envoyer Category.dish (relation Modelio)
    public static class SimpleCategory {
        public int id;
        public String name;
        public SimpleCategory(int id, String name) { this.id = id; this.name = name; }
    }
}
