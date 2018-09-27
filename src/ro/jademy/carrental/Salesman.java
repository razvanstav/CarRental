package ro.jademy.carrental;

public class Salesman extends Person{


   private String user;
   private String password;

   public Salesman (String firstName,String lastName,String user,String password){
    super(firstName,lastName);
    this.user = user;
    this.password = password;
   }

}
