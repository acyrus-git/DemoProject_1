package org.aditya.DemoProject_1.service;

import org.aditya.DemoProject_1.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorCyclicBarrier {
    public List<Future<String>> ExecuteThread(List<Product>productList1, List<Product>productList2) throws Exception {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(2);
        ExecutorService executorService= Executors.newFixedThreadPool(5);
        System.out.println("<<Application Started>>");
        //created object of resource to be used by tasks
        Printer printer=new Printer();


        //created a list of callables which contains list of tasks to perform
        List<Callable<String>> callables=new ArrayList<>();
        callables.add(new MyTask4(printer,productList1,cyclicBarrier));
        callables.add(new MyTask4(printer,productList2,cyclicBarrier));

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

        }
        catch(Exception e){
            throw new Exception();
        }
        finally {
            executorService.shutdown();
        }


        System.out.println("<<Application Stopped>>");
        return allFutures;
    }
}
