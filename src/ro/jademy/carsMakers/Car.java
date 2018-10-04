package ro.jademy.carsMakers;


import ro.jademy.carrental.CarState;
import ro.jademy.carrental.Customer;

import java.util.*;

public abstract class Car {

    private String make;
    private String model;
    private Integer year;
    private String carType; //
    private Integer doorNumber;
    private String color;
    private String costCategory;
    private String transmissionType;
    private Engine engine;
    private Integer basePrice;
    private CarState state = new CarState();
    private List<Customer> customerList = new ArrayList<>();


    public Car(String make, String model, Integer year, String carType, Integer doorNumber, String color, String transmissionType, Engine engine, Integer basePrice, String costCategory) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.carType = carType;
        this.doorNumber = doorNumber;
        this.color = color;
        this.transmissionType = transmissionType;
        this.engine = engine;
        this.basePrice = basePrice;
        this.costCategory = costCategory;
    }

    public Car() {

    }

    public CarState getState() {
        return state;
    }

    public CarState setState() {
        this.state = state;
        return state;
    }

    public Engine getEngine() {
        return engine;
    }

    public Integer getBasePrice() {
        return basePrice;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(make, car.make) &&
                Objects.equals(model, car.model) &&
                Objects.equals(year, car.year) &&
                Objects.equals(carType, car.carType) &&
                Objects.equals(doorNumber, car.doorNumber) &&
                Objects.equals(color, car.color) &&
                Objects.equals(costCategory, car.costCategory) &&
                Objects.equals(transmissionType, car.transmissionType) &&
                Objects.equals(engine, car.engine) &&
                Objects.equals(basePrice, car.basePrice) &&
                Objects.equals(state, car.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, year, carType, doorNumber, color, costCategory, transmissionType, engine, basePrice, state);
    }

    @Override
    public String toString() {
        return
                "Car{" +
                        "make='" + make + '\'' +
                        ", model='" + model + '\'' +
                        ", year=" + year +
                        ", carType='" + carType + '\'' +
                        ", doorNumber=" + doorNumber +
                        ", color='" + color + '\'' +
                        ", costCategory='" + costCategory + '\'' +
                        ", transmissionType='" + transmissionType + '\'' +
                        ", engine=" + engine.getType() + engine.getCapacity() +
                        ", basePrice='" + basePrice + '\'' +
                        ", isRented=" + state.isRented() + state.getRentedFrom() + state.getRentedUntil() +
                        '}';
    }

    public void printCar() {
        System.out.format("%15s %15s %15d %15s %10d %10s %15s %15s %15d %15s %8d %15s", make, model, year, carType, doorNumber, color, costCategory, transmissionType, engine.getCapacity(), engine.getType(), basePrice, state.isRented());
    }

    public void rentCar(int numberOfDays) {
        Scanner scan = new Scanner(System.in);
        System.out.println("When would you like to pick up the car?");
        System.out.println("A month and a day please!");
        int month = scan.nextInt();
        int day = scan.nextInt();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2018, month - 1, day);
        Calendar endDate = startDate;
        endDate.add(Calendar.DATE, numberOfDays);
        rentCar(startDate, endDate);
    }

    public void rentCar(Calendar startDate, Calendar endDate) {
        this.state.setRented(true);
        this.state.setRentedFrom(startDate);
        this.state.setRentedUntil(endDate);
    }

    public void returnCar() {
        this.state.setRented(false);
    }

    public void createCustomer() {
        Scanner answer = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String name = answer.nextLine();
        System.out.println("Please enter you last name: ");
        String name1 = answer.nextLine();
        System.out.println("The available amount of money:");
        int money = answer.nextInt();
        setCustomer(name, name1, money);
    }

    public void setCustomer(String firstName, String lastName, int money) {
       customerList.add(new Customer(firstName, lastName, money));
    }

    public boolean calculateMoney(int days) {
        return (days * getBasePrice() <= customerList.get(0).getBalance());
    }
    public boolean calculateMoneyWithAList(int days, int counter){
        return (days * getBasePrice() <= customerList.get(counter).getBalance());
    }
}