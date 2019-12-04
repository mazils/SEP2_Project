package client.model.spareParts;

import java.io.Serializable;

public class SparePart implements ISparePart, Serializable {
    private String name;
    private int quantity;
    //low STock Reminder;
    //amount received
    //amount needed


    public SparePart(String name) {
      this.name= name;
      this.quantity= 0;
    }

    public SparePart(String name, int quantity){
        this.name=name;
        this.quantity=quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }





}
