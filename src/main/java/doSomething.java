import Entity.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Printer{
    void printDocuments(int numOfCopies,String docName)
    {
        for(int i=1;i<=numOfCopies;i++)
        {
            System.out.println("Printing "+docName+" "+i);
        }
    }
}
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
class MyTask implements Runnable{
    Printer pRef;
    String doc;
    public MyTask(Printer p, String s){
        pRef=p;
        doc=s;
    }
    public void run(){
        synchronized (pRef){
            pRef.printDocuments(10, doc);
        }
    }
}
public class doSomething {

    private List<Product>productList1= Arrays.asList(new Product("Cookie1",10.0),new Product("Cookie2",20.0),new Product("Cookie3",30.0));
    private List<Product>productList2= Arrays.asList(new Product("Cookie1",10.0),new Product("Cookie2",20.0),new Product("Cookie3",30.0));
    public static void main(String[] args){

        ExecutorService executorService= Executors.newFixedThreadPool(5);
        System.out.println("<<Application Started>>");

       Printer printer=new Printer();
        Thread myThread1=new Thread(new MyTask(printer,"Nihaldoc"));
        Thread myThread2=new Thread(new MyTask(printer,"Kunaldoc"));
        Thread myThread3=new Thread(new MyTask(printer,"sahildoc"));
        Thread myThread4=new Thread(new MyTask(printer,"Adityadoc"));
        Thread myThread5=new Thread(new MyTask(printer,"Vikasdoc"));

        myThread2.start();
        myThread1.start();
        myThread3.start();
        myThread4.start();
        myThread5.start();

        System.out.println("<<Application Stopped>>");
    }
}
