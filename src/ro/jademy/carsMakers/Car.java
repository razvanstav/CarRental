package ro.jademy.carsMakers;


    public abstract class Car {

        public Car(String make, String model, Integer year, String carType, Integer doorNumber, String color, String transmissionType, Engine engine, String basePrice) {
            this.make = make;
            this.model = model;
            this.year = year;
            this.carType = carType;
            this.doorNumber = doorNumber;
            this.color = color;
            this.transmissionType = transmissionType;
            this.engine = engine;
            this.basePrice = basePrice;
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
                    ", transmissionType='" + transmissionType + '\'' +
                    "," + engine.toString() +
                    ", basePrice='" + basePrice + '\'' +
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

    }
