package fr.java.repo;

import fr.java.model.*;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryStore {
    private final List<Category> categories = new ArrayList<>();
    private final List<Dish> dishes = new ArrayList<>();
    private final Map<Integer, Order> orders = new HashMap<>();
    private final AtomicInteger orderSeq = new AtomicInteger(1);

    public InMemoryStore() { seed(); }

    private void seed() {
        categories.addAll(List.of(
                new Category(1, "Entrees"),
                new Category(2, "Plats"),
                new Category(3, "Boissons_Desserts")
        ));

        dishes.addAll(List.of(
                new Dish(1, 1, "Gyozas", "Raviolis japonais", 5.50, true, "🥟"),
                new Dish(2, 2, "Ramen", "Soupe japonaise", 12.90, true, "🍜"),
                new Dish(3, 2, "Pad Thai", "Nouilles thai", 11.50, true, "🍜"),
                new Dish(4, 3, "Bubble Tea", "The perle", 6.00, true, "🧋"),
                new Dish(5, 3, "Mochi", "Dessert japonais", 4.20, false, "🍡") // indispo
        ));
    }

    public List<Category> getCategories() { return Collections.unmodifiableList(categories); }

    public List<Dish> getDishes(Integer categoryId) {
        if (categoryId == null) return Collections.unmodifiableList(dishes);
        List<Dish> out = new ArrayList<>();
        for (Dish d : dishes) if (d.categoryId() == categoryId) out.add(d);
        return out;
    }

    public Dish getDishById(int dishId) {
        for (Dish d : dishes) if (d.id() == dishId) return d;
        return null;
    }

    public Order createOrder(String customerRef, List<OrderItem> items) {
        int id = orderSeq.getAndIncrement();
        double total = items.stream().mapToDouble(i -> i.unitPrice() * i.quantity()).sum();
        Order order = new Order(id, customerRef, items, total, Instant.now());
        orders.put(id, order);
        return order;
    }

    public Order getOrder(int id) { return orders.get(id); }
}

