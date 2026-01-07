package fr.java.model;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("50b226eb-abb4-4a9c-b206-c29a0092000f")
public class Dish {
    @objid ("79910d9d-a6b8-47ed-9dea-34ac41b08d91")
    public int id;

    @objid ("4dc773eb-7d1b-4239-9772-dcf3e2e591f4")
    public int categoryId;

    @objid ("11032e0d-ffa8-4c55-835a-fe3c2186f0ed")
    public String name;

    @objid ("55d0dc34-54ef-4ee0-ba0b-53253f8bafd8")
    public String description;

    @objid ("35864227-2ff2-49fb-9501-ea2f638cdc55")
    public double price;

    @objid ("71f72a29-502e-47d3-9838-4fad9ae4c1e2")
    public boolean available;

    @objid ("2c47091f-719d-4920-918c-41b6c0639b01")
    public String icon;

}
