package org.aditya.DemoProject_1;


import org.aditya.DemoProject_1.entity.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

class Printer{
    String printDocuments(List<Product> productList)
    {
        int numOfProducts=productList.size();
        for(int i=0;i<numOfProducts;i++)
        {
            System.out.println("The price of "+productList.get(i).getName()+" is "+productList.get(i).getPrice());
        }
        return "All products printed";
    }
}
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
}
class MyThread2 extends Thread{
    Printer pRef;
    public MyThread2(Printer p){
        pRef=p;
    }


    public void run(){
        synchronized (pRef) {
            pRef.printDocuments(10, "Kunaldocument.pdf");
        }
    }
}
class MyThread3 extends Thread{
    Printer pRef;
    public MyThread3(Printer p){
        pRef=p;
    }


    public void run(){
        synchronized (pRef) {
            pRef.printDocuments(10, "Kunaldocument.pdf");
        }
    }
}
class MyThread4 extends Thread{
    Printer pRef;
    public MyThread4(Printer p){
        pRef=p;
    }


    public void run(){
        synchronized (pRef) {
            pRef.printDocuments(10, "Kunaldocument.pdf");
        }
    }
}
class MyThread5 extends Thread{
    Printer pRef;
    public MyThread5(Printer p){
        pRef=p;
    }


    public void run(){
        synchronized (pRef) {
            pRef.printDocuments(10, "Kunaldocument.pdf");
        }
    }
}*/
//uses runnable interface for multithreading
class MyTask1 implements Runnable{
    Printer pRef;
    List<Product>productList;

    public MyTask1(Printer p, List<Product>pList){
        pRef=p;
        productList=pList;
    }
    public void run(){
        synchronized (pRef){
            pRef.printDocuments( productList);
        }
    }
}
// uses callable interface for multithreading which returns some value or exception on completion of task
class MyTask2 implements Callable<String>{
    Printer pRef;
    List<Product>productList;
    public MyTask2(Printer p, List<Product>pList){
        pRef=p;
        productList=pList;
    }
    public String call(){

            return pRef.printDocuments( productList);

    }

}
public class DoSomething {


    public static void main(String[] args){
         List<Product>productList1= Arrays.asList(new Product("Cookie1",10.0),new Product("Cookie2",20.0),new Product("Cookie4",40.0),new Product("Cookie5",50.0));
         List<Product>productList2= Arrays.asList(new Product("Chocolate1",10.0),new Product("Chocolate2",20.0),new Product("Chocolate3",30.0),new Product("Chocolate4",40.0),new Product("Chocolate5",50.0));
        //using executorservice to create a pool of multiple threads to handle multiple tasks

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


        try {
            // created a list of futures that contains a list of futures returned by all tasks
            List<Future<String>> allFutures=executorService.invokeAll(callables);
            for(Future<String>future:allFutures){

                // surrounded in try catch block as it might return an exception too
                try {
                    //fetching the value of futures returned by various tasks
                    System.out.println(future.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

        System.out.println("<<Application Stopped>>");
    }
}
