package org.aditya.DemoProject_1.controller;

import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.service.MyTask3;
import org.aditya.DemoProject_1.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {
    Logger logger= LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<?> saveHotel(@RequestBody  Product product){

            logger.info("Request recieved to add product with name "+product.getName()+" and price "+product.getPrice());
            return new ResponseEntity<Product>(productService.saveProduct(product), HttpStatus.CREATED);


    }

}
