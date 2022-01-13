package ModelClasses;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email){

        String emailRegex = "^(.+)@(.+).com$";
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
    public void setName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer's Name= " + firstName + " " + lastName + "\nCustomer's Email: " + email;
    }

}
