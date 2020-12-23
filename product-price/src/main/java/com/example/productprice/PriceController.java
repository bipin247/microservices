package com.example.productprice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PriceController {

    private List<Price> priceList = new ArrayList<Price>();

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/price/{productId}")
    public Price getPriceDetails(@PathVariable Long productId){

        Price price = getProductPrice(productId);
        //get exchange rate from exchange-service
        Integer excRate = restTemplate.getForObject("http://localhost:8004/currexg/from/USD/to/GBP", Integer.class );

        price = new Price(price.getPriceId(),price.getProductId(),price.getOriginalPrice(),Math.multiplyExact(price.getDiscountPrice(),excRate));

        return price;
    }

    private Price getProductPrice(Long productId) {
        populateProductPrice();
        for(Price pi : priceList){
            if (pi.getProductId().equals(productId)){
                return pi;
            }
        }

        return null;
    }

    private void populateProductPrice() {
        priceList.add(new Price(2001L, 1001L,1999,999));
        priceList.add(new Price(2002L, 1002L,4399,3450));
        priceList.add(new Price(2002L, 1003L,19999,12000));

    }
}
