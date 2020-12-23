package com.example.productinventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InventoryController {

    List<Inventory> productInventoryList = new ArrayList<Inventory>();

    @GetMapping("/inventory/{productId}")
    public Inventory getInventoryDetails(@PathVariable Long productId){

        return getProductInventory(productId);
    }

    private Inventory getProductInventory(Long productId) {
        populateProductInventory();
        for(Inventory pi : productInventoryList){
            if (pi.getProductId().equals(productId)){
                return pi;
            }
        }

        return null;
    }

    private void populateProductInventory() {
        productInventoryList.add(new Inventory(3001L,1001L,true));
        productInventoryList.add(new Inventory(3002L,1002L,false));
        productInventoryList.add(new Inventory(3003L,1003L,true));

    }
}
