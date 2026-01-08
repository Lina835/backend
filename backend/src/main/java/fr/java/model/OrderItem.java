package fr.java.model;
import java.util.ArrayList;
import java.util.List;

//begin of modifiable zone(Javadoc).......C/b0885918-c2ac-49e9-b39e-dfd214991327

//end of modifiable zone(Javadoc).........E/b0885918-c2ac-49e9-b39e-dfd214991327
public class OrderItem {
//begin of modifiable zone(Javadoc).......C/7f875914-afd0-4def-919d-62c42d19768f

//end of modifiable zone(Javadoc).........E/7f875914-afd0-4def-919d-62c42d19768f
    public int id;

//begin of modifiable zone(Javadoc).......C/5a528595-0bf2-4402-b0ad-38c77fcd889e

//end of modifiable zone(Javadoc).........E/5a528595-0bf2-4402-b0ad-38c77fcd889e
    public int quantity;

//begin of modifiable zone(Javadoc).......C/53071607-a80a-4934-a76d-09932c8eca4e

//end of modifiable zone(Javadoc).........E/53071607-a80a-4934-a76d-09932c8eca4e
    public double unitPrice;

//begin of modifiable zone(Javadoc).......C/7f9bd4ca-9275-4a54-8cd9-5e7244f24687

//end of modifiable zone(Javadoc).........E/7f9bd4ca-9275-4a54-8cd9-5e7244f24687
    public List<Dish> dish = new ArrayList<Dish> ();

//begin of modifiable zone(Javadoc).......C/c9f44bd2-5d1c-43af-a3f6-b1cfb7b0e532

//end of modifiable zone(Javadoc).........E/c9f44bd2-5d1c-43af-a3f6-b1cfb7b0e532
    public List<OrderItemOption> orderItemOption = new ArrayList<OrderItemOption> ();

}
