package ro.jademy.carsMakers;

import java.util.Objects;

public class Engine implements Comparable<Engine>{

       private Integer capacity;
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

       public Integer getCapacity() {
              return capacity;
       }

       @Override
       public boolean equals(Object o) {
              if (this == o) return true;
              if (!(o instanceof Engine)) return false;
              Engine engine = (Engine) o;
              return Objects.equals(type, engine.type);
       }

       @Override
       public int hashCode() {
              return Objects.hash(type);
       }

       @Override
       public int compareTo(Engine o) {
           int capacityCompare = capacity.compareTo(o.capacity);
              return capacityCompare;
       }
}
