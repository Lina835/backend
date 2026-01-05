package fr.java.dto;

import java.util.List;
import java.util.Map;

public class CreateOrderRequest {
    public String customerRef;
    public List<Item> items;

    public static class Item {
        public int dishId;
        public int quantity; // 1..9
        public Map<String, String> options;
    }
}
