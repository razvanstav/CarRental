package ro.jademy.carsMakers;

public class Engine {

       private int capacity;
       private String type;

       public Engine(){

       }

       public Engine(int capacity, String type) {
              this.capacity = capacity;
              this.type = type;
       }

       @Override
       public String toString() {
              return "Engine{" +
                      "capacity=" + capacity +
                      ", type='" + type + '\'' +
                      '}';
       }

       public String getType() {
              return type;
       }
}
