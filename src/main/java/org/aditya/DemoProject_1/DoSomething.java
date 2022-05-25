package org.aditya.DemoProject_1;


import org.aditya.DemoProject_1.entity.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

class Printer{
    String printDocuments(int numOfCopies,String docName)
    {
        for(int i=1;i<=numOfCopies;i++)
        {
            System.out.println("Printing "+docName+" "+i);
        }
        return "All "+docName+" docs printed";
    }
}
//uses thread class using inheritance
class MyThread1 extends Thread{
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
}
//uses runnable interface for multithreading
class MyTask1 implements Runnable{
    Printer pRef;
    String doc;
    public MyTask1(Printer p, String s){
        pRef=p;
        doc=s;
    }
    public void run(){
        synchronized (pRef){
            pRef.printDocuments(10, doc);
        }
    }
}
// uses callable interface for multithreading which returns some value or exception on completion of task
class MyTask2 implements Callable<String>{
    Printer pRef;
    String doc;
    public MyTask2(Printer p, String s){
        pRef=p;
        doc=s;
    }
    public String call(){

            return pRef.printDocuments(10, doc);

    }

}
public class DoSomething {

    //private List<Product>productList1= Arrays.asList(new Product("Cookie1",10.0),new Product("Cookie2",20.0),new Product("Cookie3",30.0));
    //private List<Product>productList2= Arrays.asList(new Product("Cookie1",10.0),new Product("Cookie2",20.0),new Product("Cookie3",30.0));
    public static void main(String[] args){
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
        callables.add(new MyTask2(printer,"Nihaldoc"));
        callables.add(new MyTask2(printer,"Kunaldoc"));
        callables.add(new MyTask2(printer,"sahildoc"));
        callables.add(new MyTask2(printer,"Adityadoc"));
        callables.add(new MyTask2(printer,"Vikasdoc"));


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
