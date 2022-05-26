package org.aditya.DemoProject_1.service;

import org.aditya.DemoProject_1.entity.Product;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class MyTask3 implements Callable {
    private CountDownLatch latch;
    Printer pRef;
    List<Product> productList;
    public MyTask3(Printer p, List<Product>pList,CountDownLatch latch){
        pRef=p;
        productList=pList;
        this.latch=latch;
    }


    @Override
    public String call() throws Exception {
        String s="";
        try {
            System.out.println("Started");
            s=pRef.printProducts( productList);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
        return s;
    }
}
