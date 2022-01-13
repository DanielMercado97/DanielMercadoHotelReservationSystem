package ModelClasses;

public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer("First", "Second", "j@domain.com");
        Customer customer2 = new Customer("First","Second", "email");
        System.out.println(customer);
    }
}
