package fr.java.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//begin of modifiable zone(Javadoc).......C/133b2790-46ea-4cb2-8d64-0e7172ab8fa5

//end of modifiable zone(Javadoc).........E/133b2790-46ea-4cb2-8d64-0e7172ab8fa5
public class Order {
//begin of modifiable zone(Javadoc).......C/016809b3-a757-4549-b479-5b53d3f18239

//end of modifiable zone(Javadoc).........E/016809b3-a757-4549-b479-5b53d3f18239
    public int id;

//begin of modifiable zone(Javadoc).......C/a0297eb1-082a-4f56-9d3c-3aa91014fbb4

//end of modifiable zone(Javadoc).........E/a0297eb1-082a-4f56-9d3c-3aa91014fbb4
    public String customerRef;

//begin of modifiable zone(Javadoc).......C/5a7a970b-ad67-4d3b-b31f-9e13827a7bda

//end of modifiable zone(Javadoc).........E/5a7a970b-ad67-4d3b-b31f-9e13827a7bda
    public double total;

//begin of modifiable zone(Javadoc).......C/def761a4-6cfc-47d7-b610-5ae21a4f7002

//end of modifiable zone(Javadoc).........E/def761a4-6cfc-47d7-b610-5ae21a4f7002
    public Date createdAt;

//begin of modifiable zone(Javadoc).......C/82682191-b12c-43df-85c4-fb393f693ef9

//end of modifiable zone(Javadoc).........E/82682191-b12c-43df-85c4-fb393f693ef9
    public List<OrderItem> orderItem = new ArrayList<OrderItem> ();

}
