package org.aditya.DemoProject_1.controller;

import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<?> saveHotel(@RequestBody  Product product){


            return new ResponseEntity<Product>(productService.saveProduct(product), HttpStatus.CREATED);


    }

}
