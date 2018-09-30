package ro.jademy.carrental;

import ro.jademy.carsMakers.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
    // Q: what fields and methods should this class contain?

    private Scanner scan = new Scanner(System.in);
    private File file = new File("passwords.txt");
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
                        Car car = new BMW(carP[0], carP[1], Integer.parseInt(carP[2]), carP[3], Integer.parseInt(carP[4]), carP[5], carP[6], new Engine(Integer.parseInt(carP[7]), carP[8]), Integer.parseInt(carP[9]), carP[10]);
                        carsList.add(car);
                        break;
                    }
                    case "Bentley": {
                        Car car = new Bentley(carP[0], carP[1], Integer.parseInt(carP[2]), carP[3], Integer.parseInt(carP[4]), carP[5], carP[6], new Engine(Integer.parseInt(carP[7]), carP[8]), Integer.parseInt(carP[9]), carP[10]);
                        carsList.add(car);
                        break;
                    }
                    case "Dacia": {
                        Car car = new Dacia(carP[0], carP[1], Integer.parseInt(carP[2]), carP[3], Integer.parseInt(carP[4]), carP[5], carP[6], new Engine(Integer.parseInt(carP[7]), carP[8]), Integer.parseInt(carP[9]), carP[10]);
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
            System.out.println("7. Logout ");
            System.out.println("8. Exit");

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
                    // logout();
                    break;
                }
                case 8: {
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
        System.out.println("4. Filter by engineType");
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
                showCarsByPriceFilter();
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
            car.prettyCarList();
            System.out.println();

        }
    }

    public void listAvailableCars() {
        showTable();

        for (Car car : carsList) {
            if (!car.getIsRented()) {
                car.prettyCarList();
                System.out.println();

            }

        }
    }

    public void listRentedCars() {
        showTable();

        for (Car car : carsList) {
            if (car.getIsRented()) {
                car.prettyCarList();
                System.out.println();

            }
        }
    }

    public void showCarsByMakerFilter() {
        System.out.println("Enter desired maker: ");
        Scanner scan = new Scanner(System.in);
        String filter = scan.nextLine();
        ArrayList<Car> filteredCars = new ArrayList<>();
        for (Car car : carsList) {
            if (car.getMake().equalsIgnoreCase(filter)) {
                filteredCars.add(car);
            }
        }
        if (filteredCars.isEmpty()) {
            System.out.println("There is no car with such options");
        } else {
            showTable();
            for (Car car : filteredCars) {
                car.prettyCarList();
                System.out.println();

            }
        }
    }

    public void showCarsByModelFilter() {
        System.out.println("Enter desired model");
        Scanner scan = new Scanner(System.in);
        String filter = scan.nextLine();
        ArrayList<Car> filteredCars = new ArrayList<>();
        for (Car car : carsList) {
            if (car.getModel().equalsIgnoreCase(filter)) {
                filteredCars.add(car);
            }
        }
        if (filteredCars.isEmpty()) {
            System.out.println("There is no car with such options");
        } else {
            showTable();
            for (Car car : filteredCars) {
                car.prettyCarList();
                System.out.println();
            }
        }
    }

    public void showCarsByPriceFilter() {
        System.out.println("Introduceti suma maxima dorita: ");
        Scanner scan = new Scanner(System.in);
        int filter = scan.nextInt();
        ArrayList<Car> filteredCars = new ArrayList<>();
        for (Car car : carsList) {
            if (car.getBasePrice() < filter) {
                filteredCars.add(car);
            }
        }
        if (filteredCars.isEmpty()) {
            System.out.println("There is no car with such options");
        } else {
            showTable();
            for (Car car : filteredCars) {
                car.prettyCarList();
                System.out.println();
            }
        }

    }

    public void showCarsByEngineType() {
        System.out.println("Enter engine type: ");

        Scanner scan = new Scanner(System.in);
        String filter = scan.nextLine();
        List<Car> filteredCars = new ArrayList<>();
        for (Car car : carsList) {
            if (car.getEngine().getType().equalsIgnoreCase(filter)) {
                filteredCars.add(car);
            }
        }

        if (filteredCars.isEmpty()) {
            System.out.println("There is no car with such options");
            // TODO adaugare variante alternative
        } else {
            showTable();
            for (Car car : filteredCars) {
                car.prettyCarList();
                System.out.println();
            }
        }
    }

    public void rentACar(String nameOfTheCar) {
        int index = availableCar(nameOfTheCar);
        if (index != -1) {
            System.out.println("Are you sure you want to rent this car?");
            Scanner scan = new Scanner(System.in);
            String answer = scan.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                carsList.get(index).setRented(true);
            } else {
                System.out.println("Ok, we will look for another one!");
            }

        } else {
            System.out.println("We do not have this car!");
        }
    }


    public int availableCar(String nameOfTheCar) {
        for (int i = 0; i < carsList.size(); i++) {
            if (carsList.get(i).getMake().toLowerCase().contains(nameOfTheCar.toLowerCase()) && !nameOfTheCar.isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    public void showTable() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%15s %15s %15s %15s %10s %10s %15s %15s %15s %15s %8s %15s",
                "MAKER", "MODEL", "YEAR", "CAR TYPE", "DOORS", "COLOR", "CATEGORY", "TRANSMISSION", "ENGINE CAPACITY", "ENGINE TYPE", "PRICE", "AVAILABILITY");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
