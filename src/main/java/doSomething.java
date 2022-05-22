import Entity.Product;

public class doSomething {
    public static void main(String[] args){
        Product p=new Product();
        p.setName("Frooti");
        p.setPrice(9);
        System.out.println("The price of "+p.getName()+" is "+p.getPrice());
    }
}
