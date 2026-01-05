package fr.java;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        var app = Javalin.create(config -> {
            config.http.defaultContentType = "application/json";
        });

        app.get("/", ctx -> ctx.result("Backend OK ✅"));

        app.start(7000);

        System.out.println("Server started on http://localhost:7000");
    }
}
