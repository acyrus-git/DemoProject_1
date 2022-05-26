package org.aditya.DemoProject_1.service;

import org.aditya.DemoProject_1.entity.Product;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MyTask4 implements Callable {

    private CyclicBarrier cyclicBarrier;
    Printer pRef;
    List<Product> productList;

    public MyTask4(Printer p, List<Product> pList, CyclicBarrier cyclicBarrier) {
        pRef = p;
        productList = pList;
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public String call() throws Exception {
        String s = "";
        try {
            System.out.println("Started");
            s = pRef.printProducts(productList);

            Thread.sleep(1000);
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return s;
    }
}
