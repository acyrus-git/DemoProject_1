package org.aditya.DemoProject_1.service;



import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.exception.PrinterException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadExecutorService {

    public List<Future<String>> ExecuteThread(List<Product>productList1,List<Product>productList2) throws Exception {

        ExecutorService executorService= Executors.newFixedThreadPool(5);
        System.out.println("<<Application Started>>");
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
        callables.add(new MyTask2(printer,productList1));
        callables.add(new MyTask2(printer,productList2));
        //callables.add(new MyTask2(printer,"sahildoc"));
        //callables.add(new MyTask2(printer,"Adityadoc"));
        //callables.add(new MyTask2(printer,"Vikasdoc"));
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
            throw new Exception();
        }
        finally {
            executorService.shutdown();
        }


        System.out.println("<<Application Stopped>>");
        return allFutures;
    }
}
