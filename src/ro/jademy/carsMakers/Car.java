package ro.jademy.carsMakers;


import java.util.Objects;

public abstract class Car {

        public Car(String make, String model, Integer year, String carType, Integer doorNumber, String color, String transmissionType, Engine engine, String basePrice,String costCategory) {
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
            this.isRented = false;
        }

        public Car() {
        }


    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", carType='" + carType + '\'' +
                ", doorNumber=" + doorNumber +
                ", color='" + color + '\'' +
                ", costCategory='" + costCategory + '\'' +
                ", transmissionType='" + transmissionType + '\'' +
                ", engine=" + engine +
                ", basePrice='" + basePrice + '\'' +
                ", isRented=" + isRented +
                '}';
    }

    // Q: how can we better represent the car make?
    private String make;
    private String model;
    private Integer year;

    // Q: how can we better represent the car type?
    private String carType; //

    private Integer doorNumber;

    private String color;
    private String costCategory;

    // Q: how can we better represent the transmission type?
    private String transmissionType; // automatic, manual

    // Q: how can we better represent the engine?
    private Engine engine;

    // Q: how can we better represent money value?
    private String basePrice;
    private boolean isRented;

    public boolean getIsRented(){
        return this.isRented;
    }
    // Q: do we need a constructor other than the default one?
    // Q: how can we better protect the car data?
    public Engine getEngine() {
            return engine;
        }

        public Integer getDoorNumber() {
            return doorNumber;
        }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public String getBasePrice() {
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
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return isRented == car.isRented &&
                Objects.equals(make, car.make) &&
                Objects.equals(model, car.model) &&
                Objects.equals(year, car.year) &&
                Objects.equals(carType, car.carType) &&
                Objects.equals(doorNumber, car.doorNumber) &&
                Objects.equals(color, car.color) &&
                Objects.equals(costCategory, car.costCategory) &&
                Objects.equals(transmissionType, car.transmissionType) &&
                Objects.equals(engine, car.engine) &&
                Objects.equals(basePrice, car.basePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, year, carType, doorNumber, color, costCategory, transmissionType, engine, basePrice, isRented);
    }
}


