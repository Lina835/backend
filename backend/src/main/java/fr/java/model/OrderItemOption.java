package fr.java.model;

public class OrderItemOption {
    public int id;
    public int orderItemId;

    public String name;
    public String value;

    public OrderItemOption() {}

    public OrderItemOption(int id, int orderItemId, String name, String value) {
        this.id = id;
        this.orderItemId = orderItemId;
        this.name = name;
        this.value = value;
    }
}
