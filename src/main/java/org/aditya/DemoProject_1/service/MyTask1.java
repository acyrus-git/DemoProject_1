package org.aditya.DemoProject_1.service;

import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.exception.PrinterException;

import java.util.List;

public class MyTask1 implements Runnable{
    Printer pRef;
    List<Product> productList;

    public MyTask1(Printer p, List<Product>pList){
        pRef=p;
        productList=pList;
    }
    public void run(){
        synchronized (pRef){

            try {
                pRef.printProducts( productList);

            }
            catch(PrinterException e) {
                throw new PrinterException();
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
