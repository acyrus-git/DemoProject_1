package org.aditya.Demoproject_1;

import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.exception.PrinterException;
import org.aditya.DemoProject_1.service.Printer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrinterTest {

    private Printer printer;
    @Before
    public void setup() throws Exception{
        printer=new Printer();
    }
    @Test
    public void testPrintProducts() throws Exception {
        List<Product> productList1 = Arrays.asList(new Product("Cookie1", 10.0), new Product("Cookie2", 20.0), new Product("Cookie4", 40.0), new Product("Cookie5", 50.0));
        assertEquals("All products should be printed",printer.printProducts(productList1),"All products printed");
    }

    @Test(expected = PrinterException.class)
    public void testPrintProductsWithEmptyList() throws Exception {
        List<Product> productList1 = new ArrayList<>();
        assertEquals("All products should be printed",printer.printProducts(productList1),"All products printed");
    }


}
