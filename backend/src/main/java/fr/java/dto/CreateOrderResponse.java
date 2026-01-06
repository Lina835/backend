package fr.java.dto;

public class CreateOrderResponse {
    
    public int id; // C'est l'ID de la table 'orders' (ex: 1234)

    // Constructeur vide (Indispensable pour que Javalin/Jackson puisse créer l'objet)
    public CreateOrderResponse() {
    }

    // Constructeur pratique pour ton Main
    public CreateOrderResponse(int id) {
        this.id = id;
    }
}