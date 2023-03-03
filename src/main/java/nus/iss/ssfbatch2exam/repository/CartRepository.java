package nus.iss.ssfbatch2exam.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import nus.iss.ssfbatch2exam.models.Stock;

@Repository
public class CartRepository {

    // private List<Stock> cart;
    private Map<String, Stock> cartmap;

    public CartRepository() {
        if (cartmap == null) {
            cartmap = new HashMap<String, Stock>();
        }
        // Stock stock = new Stock("apple", 7);
        // cartmap.put(stock.getItemname(), stock);
    }

    public Map<String, Stock> findAll() {
        return cartmap;
    }

    public void addstock(Stock stock) {

        if (cartmap.containsKey(stock.getItemname())) {
            Integer quantitytoadd = stock.getQuantity();
            Integer currentqualtity = cartmap.get(stock.getItemname()).getQuantity();
            Integer newquantity = currentqualtity + quantitytoadd;
            cartmap.put(stock.getItemname(), new Stock(stock.getItemname(), newquantity));

        } else {
            cartmap.put(stock.getItemname(), stock);
        }

    }

    public Integer checkcartsize(){
        return cartmap.size();  
    }

    public void clearstock(){
        cartmap.clear();
    }

}
