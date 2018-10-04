package ro.jademy.carrental;

public class Customer extends Person{

     private int balance;


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
}
