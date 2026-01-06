package fr.java;

import fr.java.dto.CreateOrderRequest;
import fr.java.dto.CreateOrderResponse;
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

        app.post("/api/orders", ctx -> {
    // 1. On reçoit le JSON du Frontend et on le transforme en objet Java
    CreateOrderRequest req = ctx.bodyAsClass(CreateOrderRequest.class);
    
    // 2. On demande au Store d'enregistrer ça en base de données SQL
    // On va créer cette méthode juste après
    int newId = store.createOrder(req);
    
    // 3. On prépare la réponse
    CreateOrderResponse res = new CreateOrderResponse();
    res.id = newId;
    
    // 4. On renvoie la réponse avec le code 201 (Created)
    ctx.status(201).json(res);
});

        app.start(7000);
        System.out.println("Server started on http://localhost:7000");
    }
}
