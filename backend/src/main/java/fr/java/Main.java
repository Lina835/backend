package fr.java;

import fr.java.repo.MySqlStore;
import io.javalin.Javalin;

public class Main {

    public static void main(String[] args) {

        MySqlStore store = new MySqlStore();

        var app = Javalin.create(config ->
                config.http.defaultContentType = "application/json"
        );

        app.get("/", ctx -> ctx.result("Backend OK ✅"));

        app.get("/api/categories", ctx ->
                ctx.json(store.getCategories())
        );

        app.get("/api/dishes", ctx -> {
            String q = ctx.queryParam("categoryId");
            Integer categoryId = (q == null || q.isBlank())
                    ? null
                    : Integer.parseInt(q);

            ctx.json(store.getDishes(categoryId));
        });

        app.start(7000);
        System.out.println("Server started on http://localhost:7000");
    }
}
