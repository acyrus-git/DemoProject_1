package org.aditya.DemoProject_1.service;

import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.exception.PrinterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MyTask1 implements Runnable{
    Logger logger= LoggerFactory.getLogger(MyTask1.class);
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
                logger.info("Product List is empty");
                throw new PrinterException();
            }catch (Exception e) {
                logger.info("error occured while creating thread");
                e.printStackTrace();

            }

        }
    }
}
