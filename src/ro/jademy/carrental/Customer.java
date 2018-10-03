package ro.jademy.carrental;

public class Customer extends Person{

     private int balance;
     private String firstName;
     private String lastName;


    public Customer(String firstName, String lastName, int balance) {
        super(firstName, lastName);
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
