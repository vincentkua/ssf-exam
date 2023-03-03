package nus.iss.ssfbatch2exam.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Stock {

    @NotNull(message = "Item Field cannot be empty")
    private String itemname;

    @Min(value = 1,message = "You must add at least 1 item" )
    @NotNull(message = "You must add at least 1 item")
    private Integer quantity;

    public Stock() {
    }

    public Stock(String itemname, Integer quantity) {
        this.itemname = itemname;
        this.quantity = quantity;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Stock [itemname=" + itemname + ", quantity=" + quantity + "]";
    }

    

    

    
    
}
