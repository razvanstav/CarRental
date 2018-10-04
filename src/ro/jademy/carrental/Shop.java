package ro.jademy.carrental;

import ro.jademy.carsMakers.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Shop {
    // Q: what fields and methods should this class contain?

    private Scanner fileScan;
    private ArrayList<Salesman> salesmenList = new ArrayList<>();
    private ArrayList<Car> carsList = new ArrayList<>();

    public Shop() {

        try {
            fileScan = new Scanner(new File("src/passwords.txt"));
            while (fileScan.hasNext()) {
                String line = fileScan.nextLine();
                String[] credentials = line.split(",");
                Salesman salesman = new Salesman(credentials[2], credentials[3], credentials[0], credentials[1]);
                salesmenList.add(salesman);
            }
            fileScan = new Scanner(new File("src/cars.txt"));
            while (fileScan.hasNext()) {
                String line = fileScan.nextLine();
                String[] carP = line.split(",");
                // for(int i = 0; i < carP.length; i++)
                // System.out.println(carP[i]);
                switch (carP[0]) {
                    case "BMW": {
                        Car car = new BMW(carP[0], carP[1], Integer.parseInt(carP[2]), carP[3], Integer.parseInt(carP[4]),
                                carP[5], carP[6], new Engine(Integer.parseInt(carP[7]), carP[8]), Integer.parseInt(carP[9]), carP[10]);
                        carsList.add(car);
                        break;
                    }
                    case "Bentley": {
                        Car car = new Bentley(carP[0], carP[1], Integer.parseInt(carP[2]), carP[3], Integer.parseInt(carP[4]),
                                carP[5], carP[6], new Engine(Integer.parseInt(carP[7]), carP[8]), Integer.parseInt(carP[9]), carP[10]);
                        carsList.add(car);
                        break;
                    }
                    case "Dacia": {
                        Car car = new Dacia(carP[0], carP[1], Integer.parseInt(carP[2]), carP[3], Integer.parseInt(carP[4]),
                                carP[5], carP[6], new Engine(Integer.parseInt(carP[7]), carP[8]), Integer.parseInt(carP[9]), carP[10]);
                        carsList.add(car);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean login(String username, String password) {

        try {
            fileScan = new Scanner(new File("src/passwords.txt"));
            while (fileScan.hasNext()) {
                String line = fileScan.nextLine(); // creem string cu datele de pe linie
                String[] credentials = line.split(","); // impartim linia dupa spatii
                if (username.equalsIgnoreCase(credentials[0]) && password.equals(credentials[1])) {
                    System.out.println("Succesfuly login");
                    return true;
                }
            }
            System.out.println("User or password does not match");


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public void showMenu() {
        boolean ok = true;

        while (ok) {
            System.out.println(" -----------------------------------------------");
            System.out.println("|    Welcome to the Jademy Car Rental Service   |");
            System.out.println(" -----------------------------------------------");
            System.out.println();
            System.out.println("                    MAIN MENU                   ");
            System.out.println("1. List all cars");
            System.out.println("2. List available cars");
            System.out.println("3. List rented cars");
            System.out.println("4. Check income");
            System.out.println("5. Options");
            System.out.println("6. Rent a car");
            System.out.println("7. Request a callback");
            System.out.println("8. Logout");
            System.out.println("9. Exit");

            Scanner scan = new Scanner(System.in);
            int option = scan.nextInt();

            switch (option) {
                case 1: {
                    listAllCars();
                    break;
                }
                case 2: {
                    listAvailableCars();
                    break;
                }
                case 3: {
                    listRentedCars();
                    break;
                }
                case 4: {
                    //checkIncome();
                }
                case 5: {
                    showListMenuOptions();
                    break;
                }
                case 6: {
                    System.out.println("Enter your choice by maker : ");
                    Scanner option1 = new Scanner(System.in);
                    rentACar(option1.nextLine());
                    break;
                }
                case 7: {
                    returnACar();
                    break;
                }
                case 8: {
                    //logout
                }
                case 9: {
                    ok = false;
                    break;
                }
            }
        }
    }

    public void showListMenuOptions() {

        System.out.println("Select an action from below:");
        System.out.println("1. Filter by make");
        System.out.println("2. Filter by model");
        System.out.println("3. Filter by price");
        System.out.println("4. Filter by engine type");
        System.out.println("5. Return to menu");
        // TODO: add additional filter methods based on car specs
        Scanner scan = new Scanner(System.in);
        Integer option = scan.nextInt();
        switch (option) {
            case 1: {
                showCarsByMakerFilter();
                break;
            }
            case 2: {
                showCarsByModelFilter();
                break;
            }
            case 3: {
                showMenuFilterByPrice();
                break;
            }
            case 4: {
                showCarsByEngineType();
                break;
            }
            case 5: {
                break;
            }
        }


    }

    public void showMenuFilterByPrice() {

        System.out.println("Select an option from below");
        System.out.println("1. Descending order");
        System.out.println("2. Ascending order");
        System.out.println("3. Show under specified price");
        System.out.println("4. Show over specified price");
        System.out.println("5. Return to menu");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();

        switch (option) {
            case 1: {
                filterByDescending();
                break;
            }
            case 2: {
                filterByAscending();
                break;
            }
            case 3: {
                showCarsUnderPrice();
                break;
            }
            case 4: {
                showCarsOverPrice();
                break;
            }
            case 5: {
                break;
            }
        }

    }

    public int calculatePrice(int numberOfDays) {
        // TODO: apply a discount to the base price of a car based on the number of rental days
        // TODO: document the implemented discount algorithm

        // TODO: for a more difficult algorithm, change this method to include date intervals and take into account
        //       weekdays and national holidays in which the discount should be smaller

        // Q: what should be the return type of this method?
        return 0;
    }

    public void listAllCars() {
        showTable();
        for (Car car : carsList) {
            car.printCar();
            System.out.println();

        }
    }

    public void listAvailableCars() {
        showTable();

        for (Car car : carsList) {
            if (!car.getState().isRented()) {
                car.printCar();
                System.out.println();

            }
        }
    }

    public void listRentedCars() {
        showTable();

        for (Car car : carsList) {
            if (car.getState().isRented()) {
                car.printCar();
                System.out.println();

            }
        }
    }

    public void showCarsByMakerFilter() {
        System.out.println("Enter desired maker: ");
        Scanner scan = new Scanner(System.in);
        String filter = scan.nextLine();
        ArrayList<String> makerOptions = new ArrayList<>();
        List<Car> filteredCars = getFilterCarsByMake(filter);
        if (filteredCars.isEmpty()) {
            for (Car car: carsList) {
                makerOptions.add(car.getMake());
            }
            showOtherOptions(makerOptions);
        } else {
            printFilterCarsByMake(filter);
        }
    }

    public void showCarsByModelFilter() {
        System.out.println("Enter desired model");
        Scanner scan = new Scanner(System.in);
        String filter = scan.nextLine();
        List<Car> filteredCars = getFilterCarsByModel(filter);
        ArrayList<String> modelOptions = new ArrayList<>();
        if (filteredCars.isEmpty()) {
            for (Car car: filteredCars) {
                modelOptions.add(car.getModel());
            }
            showOtherOptions(modelOptions);
        } else {
            showTable();
            printFilterCarsByModel(filter);
        }
    }

    public void showCarsUnderPrice() {
        System.out.println("Enter maximum amount: ");
        Scanner scan = new Scanner(System.in);
        int filter = scan.nextInt();
        ArrayList<Car> filteredCars = new ArrayList<>();
        for (Car car : carsList) {
            if (car.getBasePrice() < filter) {
                filteredCars.add(car);
            }
        }

        showTable();
        for (Car car : filteredCars) {
            car.printCar();
            System.out.println();
        }
    }

    public void showCarsOverPrice() {
        System.out.println("Enter minimum amount: ");
        Scanner scan = new Scanner(System.in);
        int filter = scan.nextInt();
        ArrayList<Car> filteredCars = new ArrayList<>();
        for (Car car : carsList) {
            if (car.getBasePrice() > filter) {
                filteredCars.add(car);
            }
        }

        showTable();
        for (Car car : filteredCars) {
            car.printCar();
            System.out.println();
        }
    }

    public void filterByDescending() {
    }

    public void filterByAscending() {

    }

    public void showCarsByEngineType() {
        System.out.println("Enter engine type: ");
        Scanner scan = new Scanner(System.in);
        String filter = scan.nextLine();
        List<Car> filteredCars = getFilterCarsByEngine(filter);
        ArrayList<String> engineOptions = new ArrayList<>();
        if (filteredCars.isEmpty()) {
            for (Car car: carsList) {
                engineOptions.add(car.getEngine().getType());
            }
            showOtherOptions(engineOptions);
        } else {
            showTable();
            printFilterCarsByEngine(filter);
        }
    }

    public void rentACar(String nameOfTheCar) {
        List<Car> cars = getAvailableCarsByMake(nameOfTheCar);
        Scanner scan = new Scanner(System.in);
        if (cars.isEmpty()) {
            System.out.println("We do not have this car!");
        } else if (cars.size()==1) {
            System.out.println("How many days would you like to rent the car?");
            int numberOfDays = scan.nextInt();
            cars.get(0).rentCar(numberOfDays);
            System.out.println("Now we will need your personal data. Dont worry, we are GDPR compliant!");
            cars.get(0).createCustomer();
            if (cars.get(0).calculateMoney(numberOfDays)){
                System.out.println("Sufficient funds!");
            System.out.println("Thank you for you purchase");
            System.out.println("You successfully rented our " + cars.get(0).getMake() + " with the price of " + cars.get(0).getBasePrice() +" dollars per day until "+cars.get(0).getState().getRentedUntil().getTime() +".");
                cars.get(0).getState().setRented(true);}
                else {
                System.out.println("Take a look at the other cars!");
                    listAllCars();
            }
            } else {
            System.out.println("Which car do you want to rent?");
            printAvailableCarsByMake(nameOfTheCar);
            int answer = scan.nextInt();
            System.out.println("How many days would you like to rent the car?");
            int numberOfDays = scan.nextInt();
            cars.get(answer-1).rentCar(numberOfDays);
            System.out.println("Now we will need your personal data. Dont worry, we are GDPR compliant!");
            cars.get(answer-1).createCustomer();
            if (cars.get(answer-1).calculateMoneyWithAList(numberOfDays,answer-1)){
                System.out.println("Sufficient funds!");
            System.out.println("Thank you for you purchase");
            cars.get(answer-1).getState().setRented(true);
            System.out.println("You successfully rented our " + cars.get(answer-1).getMake() + " with the price of " + cars.get(answer-1).getBasePrice() +" dollars per day until "+cars.get(answer-1).getState().getRentedUntil().getTime() +".");

        } else {
            listAllCars();
            }
        }
        }


    public void returnACar() {
        List<Car> cars = getRentedCars();
        Scanner scan = new Scanner(System.in);
        if (cars.isEmpty()){
            System.out.println("We haven't rented any cars yet!");
        } else if (cars.size()==1){
            System.out.println("You successfully called back our " + cars.get(0).getMake()+".");
            cars.get(0).returnCar();
        } else {
            System.out.println("These are our rented cars!");
            System.out.println("Which care do you want to callback?");
            printRentedCars();
            int answer = scan.nextInt();
            cars.get(answer-1).returnCar();
            System.out.println("You successfully called back our " + cars.get(0).getMake()+".");

        }
    }
    public List<Car> getRentedCars(){
        ArrayList<Car> rentedCars = new ArrayList<>();
        for (Car car: carsList ){
            if(car.getState().isRented()){
                rentedCars.add(car);
            }
        }
        return rentedCars;
    }
    public void printRentedCars(){
        showTable();
        for (Car car : getRentedCars()) {
            car.printCar();
            System.out.println();
        }
    }



    public List<Car> getAvailableCarsByMake(String carMake) {
        ArrayList<Car> filteredCars = new ArrayList<>();
        for (Car car : carsList) {
            if (car.getMake().toLowerCase().contains(carMake.toLowerCase()) && !car.getState().isRented() && !carMake.isEmpty()) {
                filteredCars.add(car);
            }
        }
        return filteredCars;
    }
    public void printAvailableCarsByMake(String carMake){
        showTable();
        for (Car car: getAvailableCarsByMake(carMake)) {
            car.printCar();
            System.out.println();
        }
    }

    public List<Car> getFilterCarsByMake(String carMake) {
        ArrayList<Car> filteredCars = new ArrayList<>();
        for (Car car : carsList) {
            if (car.getMake().equalsIgnoreCase(carMake)) {
                filteredCars.add(car);
            }
        }
        return filteredCars;
    }
    public void printFilterCarsByMake(String carMake){
        showTable();
        for (Car car: getFilterCarsByMake(carMake)) {
            car.printCar();
            System.out.println();
        }
    }

    public List<Car> getFilterCarsByModel(String carModel) {
        ArrayList<Car> filteredCars = new ArrayList<>();
        for (Car car : carsList) {
            if (car.getMake().equalsIgnoreCase(carModel)) {
                filteredCars.add(car);
            }
        }
        return filteredCars;
    }
    public void printFilterCarsByModel(String carModel){
        showTable();
        for (Car car: getAvailableCarsByMake(carModel)) {
            car.printCar();
            System.out.println();
        }
    }

    public List<Car> getFilterCarsByEngine(String carEngine) {
        ArrayList<Car> filteredCars = new ArrayList<>();
        for (Car car : carsList) {
            if (car.getMake().equalsIgnoreCase(carEngine)) {
                filteredCars.add(car);
            }
        }
        return filteredCars;
    }
    public void printFilterCarsByEngine(String carEngine){
        showTable();
        for (Car car: getAvailableCarsByMake(carEngine)) {
            car.printCar();
            System.out.println();
        }
    }

    public void showTable() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%15s %15s %15s %15s %10s %10s %15s %15s %15s %15s %8s %15s",
                "MAKER", "MODEL", "YEAR", "CAR TYPE", "DOORS", "COLOR", "CATEGORY", "TRANSMISSION", "ENGINE CAPACITY", "ENGINE TYPE", "PRICE", "RENTED");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    public void showOtherOptions(ArrayList<String> options) {
        System.out.println("We do not have that options");
        System.out.println("We only have these options : " + options.stream().distinct().collect(Collectors.toList()));
    }

}
