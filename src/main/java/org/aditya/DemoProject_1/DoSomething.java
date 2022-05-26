package org.aditya.DemoProject_1;


import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.service.ExecutorCyclicBarrier;
import org.aditya.DemoProject_1.service.ThreadExecutorLatch;
import org.aditya.DemoProject_1.service.ThreadExecutorService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;


//uses thread class using inheritance
/*class MyThread1 extends Thread{
    Printer pRef;
   public MyThread1(Printer p){
        pRef=p;
    }


    public void run(){
       synchronized (pRef) {
           pRef.printDocuments(10, "Adityadocument.pdf");
       }
    }
}*/

//uses runnable interface for multithreading

// uses callable interface for multithreading which returns some value or exception on completion of task

public class DoSomething {


    public static void main(String[] args) throws Exception {
        List<Product> productList1 = Arrays.asList(new Product("Cookie1", 10.0), new Product("Cookie2", 20.0), new Product("Cookie4", 40.0), new Product("Cookie5", 50.0));
        List<Product> productList2 = Arrays.asList(new Product("Chocolate1", 10.0), new Product("Chocolate2", 20.0), new Product("Chocolate3", 30.0), new Product("Chocolate4", 40.0), new Product("Chocolate5", 50.0));
        List<Product> productList3= new ArrayList<>();
         ThreadExecutorService threadExecutorService=new ThreadExecutorService();
        ThreadExecutorLatch threadExecutorLatch=new ThreadExecutorLatch();
        ExecutorCyclicBarrier executorCyclicBarrier=new ExecutorCyclicBarrier();


        List<Future<String>>allFutures=threadExecutorService.ExecuteThread(productList1,productList2);
        //List<Future<String>>allFutures=threadExecutorLatch.ExecuteThread(productList1,productList2);
        //List<Future<String>>allFutures=executorCyclicBarrier.ExecuteThread(productList1,productList2);

    }
}
