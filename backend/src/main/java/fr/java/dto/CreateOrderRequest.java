package fr.java.dto;

import java.util.List;

public class CreateOrderRequest {
    public String customerRef;
    public List<Item> items;

    public static class Item {
    public int dishId;
    public int quantity;
    // Remplace Map<String, String> par Object ou List
    // Pour ne plus avoir d'erreur, utilise List si ton frontend envoie []
    public List<Object> options; 
}
}
