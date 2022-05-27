package org.aditya.DemoProject_1.service;



import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.exception.PrinterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadExecutorService {
    Logger logger= LoggerFactory.getLogger(ThreadExecutorService.class);
    public List<Future<String>> ExecuteThread(List<Product>productList1,List<Product>productList2) throws Exception {

        ExecutorService executorService= Executors.newFixedThreadPool(5);
        logger.info("Application Started");
        //created object of resource to be used by tasks
        Printer printer=new Printer();


        //created a list of callables which contains list of tasks to perform
        List<Callable<String>> callables=new ArrayList<>();
        callables.add(new MyTask2(printer,productList1));
        callables.add(new MyTask2(printer,productList2));

        List<Future<String>> allFutures=new ArrayList<>();

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

        }
        catch(Exception e){
            logger.error("Error occured in executor service");
            throw new Exception();
        }
        finally {
            executorService.shutdown();
        }


        logger.info("Application Stopped");
        return allFutures;
    }
}
