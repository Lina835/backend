package fr.java.model;

public record Dish(
        int id,
        int categoryId,
        String name,
        String description,
        double price,
        boolean available,
        String icon
) {}
