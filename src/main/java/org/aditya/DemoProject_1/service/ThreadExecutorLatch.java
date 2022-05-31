package org.aditya.DemoProject_1.service;

import org.aditya.DemoProject_1.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadExecutorLatch {
    Logger logger= LoggerFactory.getLogger(ThreadExecutorLatch.class);
    public List<Future<String>> ExecuteThread(List<Product>productList1, List<Product>productList2) throws Exception {
        CountDownLatch latch=new CountDownLatch(2);
        ExecutorService executorService= Executors.newFixedThreadPool(5);
        logger.info("Application Started");

        //created object of resource to be used by tasks
        Printer printer=new Printer();
        /*Thread myThread1=new Thread(new MyTask1(printer,"Nihaldoc"));
        Thread myThread2=new Thread(new MyTask1(printer,"Kunaldoc"));
        Thread myThread3=new Thread(new MyTask1(printer,"sahildoc"));
        Thread myThread4=new Thread(new MyTask1(printer,"Adityadoc"));
        Thread myThread5=new Thread(new MyTask1(printer,"Vikasdoc"));

        myThread2.start();
        myThread1.start();
        myThread3.start();
        myThread4.start();
        myThread5.start();*/

        //created a list of callables which contains list of tasks to perform
        List<Callable<String>> callables=new ArrayList<>();
        callables.add(new MyTask3(printer,productList1,latch));
        callables.add(new MyTask3(printer,productList2,latch));
        //callables.add(new MyTask2(printer,"sahildoc"));
        //callables.add(new MyTask2(printer,"Adityadoc"));
        //callables.add(new MyTask2(printer,"Vikasdoc"));
        List<Future<String>> allFutures= new ArrayList<>();

        try {
            // created a list of futures that contains a list of futures returned by all tasks
            allFutures=executorService.invokeAll(callables);
            for(Future<String>future:allFutures){
                try {
                    future.get();
                }catch(ExecutionException e)
                {
                    throw new Exception();
                }
            }
            latch.await();
        }
        catch(Exception e){
            logger.error("Error occured in executor service");
            throw new Exception();
        }
        finally {
            executorService.shutdown();
        }

        logger.info("Application stopped");

        return allFutures;
    }
}
