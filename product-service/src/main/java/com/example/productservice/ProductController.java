package com.example.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    List<ProductInfo> productList = new ArrayList<ProductInfo>();

    @GetMapping("/product/detail/{productId}")
    public Product getProductDetails(@PathVariable Long productId){

        ProductInfo p = getProduct(productId);
        //get price from price-service
        Price price = restTemplate.getForObject("http://localhost:8002/price/" + productId, Price.class );
        //get invetory from invetory-service
        Inventory inventory = restTemplate.getForObject("http://localhost:8003/inventory/" + productId, Inventory.class );
        return new Product(p.ProductId,p.ProductName,p.ProductDesc,price.getDiscountPrice(),inventory.getStock());
    }

    private ProductInfo getProduct(Long productId) {
        populateProductInfo();
        for(ProductInfo pi : productList){
            if (pi.getProductId().equals(productId)){
                return pi;
            }
        }

        return null;
    }

    private void populateProductInfo() {
        productList.add(new ProductInfo(1001L,"Indian Jones", "Great Book"));
        productList.add(new ProductInfo(1002L,"Black Sofa Set", "Comfy sofas"));
        productList.add(new ProductInfo(1003L,"Toyota Aygo","nippy small runner"));

    }
}
