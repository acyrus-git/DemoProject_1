package org.aditya.DemoProject_1.service;

import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.exception.PrinterException;

import java.util.List;

public class Printer {
    public String printProducts(List<Product> productList) throws Exception {
        int numOfProducts=productList.size();
        String s="";
        if(numOfProducts==0)
            throw new Exception();




            for (int i = 0; i < numOfProducts; i++) {
                System.out.println("The price of " + productList.get(i).getName() + " is " + productList.get(i).getPrice());


        }

        return "All products printed";
    }
}
