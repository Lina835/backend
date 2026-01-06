package fr.java.model;

import java.time.Instant;
import java.util.List;

public record Order(
        int id,
        String customerRef,
        List<OrderItem> items,
        double total,
        Instant createdAt
) {}
