package fr.java.model;

import java.util.Map;

public record OrderItem(
        int dishId,
        int quantity,
        double unitPrice,
        Map<String, String> options
) {}
