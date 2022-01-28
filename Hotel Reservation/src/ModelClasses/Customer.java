package ModelClasses;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email){

        String emailRegex = "^(.+)@(.+)\\.(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Error, invalid email!");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;


    }
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }


    public String getEmail() {
        return email;
    }



    @Override
    public String toString() {
        return "Customer's Name= " + firstName + " " + lastName + "\nCustomer's Email: " + email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if(!(o instanceof Customer)){
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(this.getFirstName(), customer.getFirstName())
                && Objects.equals(this.getLastName(),customer.getLastName())
                && Objects.equals(this.getEmail(), customer.getEmail());
    }
    public int hashCode() {return Objects.hash(firstName,lastName,email);}

}
