package org.aditya.DemoProject_1.service;

import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.exception.PrinterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Callable;

public class MyTask2 implements Callable<String> {
    Logger logger= LoggerFactory.getLogger(MyTask2.class);
    Printer pRef;
    List<Product> productList;
    public MyTask2(Printer p, List<Product>pList){
        pRef=p;
        productList=pList;
    }
    public String call() throws Exception {
        logger.info("Thread Started");
            //System.out.println(pRef.printProducts( productList));

        return pRef.printProducts( productList);
    }

}
