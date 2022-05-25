package org.aditya.Demoproject_1;

import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.exception.PrinterException;
import org.aditya.DemoProject_1.service.ThreadExecutorService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ThreadExecutorServiceTest {
    private ThreadExecutorService threadExecutorService;

    @Before
    public void setup() throws Exception{
        threadExecutorService=new ThreadExecutorService();
    }
    @Test
    public void testExecuteThread() throws Exception {
        List<Product> productList1 = Arrays.asList(new Product("Cookie1", 10.0), new Product("Cookie2", 20.0), new Product("Cookie4", 40.0), new Product("Cookie5", 50.0));
        List<Product> productList2 = Arrays.asList(new Product("Chocolate1", 10.0), new Product("Chocolate2", 20.0), new Product("Chocolate3", 30.0), new Product("Chocolate4", 40.0), new Product("Chocolate5", 50.0));

        List<Future<String>>allFutures=threadExecutorService.ExecuteThread(productList1,productList2);
        for(Future<String>future:allFutures){
            assertTrue(future.isDone());
            }

    }
    @Test(expected = Exception.class)
    public void testExecuteThreadWithEmptyList() throws Exception {
        List<Product> productList1 = Arrays.asList(new Product("Cookie1", 10.0), new Product("Cookie2", 20.0), new Product("Cookie4", 40.0), new Product("Cookie5", 50.0));
        //List<Product> productList2 = Arrays.asList(new Product("Chocolate1", 10.0), new Product("Chocolate2", 20.0), new Product("Chocolate3", 30.0), new Product("Chocolate4", 40.0), new Product("Chocolate5", 50.0));
        List<Product> productList3= new ArrayList<>();
        List<Future<String>>allFutures=threadExecutorService.ExecuteThread(productList1,productList3);
        for(Future<String>future:allFutures){
            assertTrue(future.isDone());
        }


    }
}
