package org.aditya.DemoProject_1.service;

import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.exception.PrinterException;

import java.util.List;
import java.util.concurrent.Callable;

public class MyTask2 implements Callable<String> {
    Printer pRef;
    List<Product> productList;
    public MyTask2(Printer p, List<Product>pList){
        pRef=p;
        productList=pList;
    }
    public String call() throws Exception {

            //System.out.println(pRef.printProducts( productList));

        return pRef.printProducts( productList);
    }

}
