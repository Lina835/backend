package fr.java.api.dto;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderRequest {
    public String customerRef;
    public List<Item> items = new ArrayList<>();

    public static class Item {
        public int dishId;
        public int quantity;
        public List<String> options = new ArrayList<>(); // ex: "spice:Moyen", "accompagn:Riz"
    }
}
